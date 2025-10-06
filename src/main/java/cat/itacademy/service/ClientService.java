package cat.itacademy.service;

import cat.itacademy.exception.EmptyListException;
import cat.itacademy.repository.daoImpl.ClientDaoImpl;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.Client;
import cat.itacademy.message.success.ClienteSuccessMessages;
import cat.itacademy.validation.client.ClientBasicValidation;
import cat.itacademy.validation.client.ClientDuplicateValidation;
import cat.itacademy.validation.client.ClientValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static cat.itacademy.message.error.ClientErrorMessages.CLIENT_LIST_EMPTY;

public class ClientService {
    private ClientDaoImpl clientDAO;
    private ClientValidator clientValidator;
    private final NotificationService notificationService;

    public ClientService() {
        this.clientDAO = new ClientDaoImpl();
        this.clientValidator = new ClientValidator(List.of(
                new ClientBasicValidation(),
                new ClientDuplicateValidation(clientDAO)
        ));
        this.notificationService = new NotificationService();
    }

    public void addClient(Client client) {
        try {
            clientValidator.validate(client);

            clientDAO.insert(client);

            if (client.isAcceptsNotifications()) {
                notificationService.addObserver(client);
            }

            Client lastClient = getLastClient();

            System.out.println(String.format(ClienteSuccessMessages.CLIENT_CREATED, lastClient.getUserName(), lastClient.getEmail(), lastClient.getPhone(), lastClient.isAcceptsNotifications()));
        } catch (DuplicateException | InvalidAttributeException e) {
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }
    }

    public Client getLastClient() throws SQLException {
        Optional<Client> client = clientDAO.getLast();

        if (client.isPresent()) {
            return client.get();
        }else {
            return null;
        }
    }

    public Client getClientById(int id) throws SQLException {
        Optional<Client> client = clientDAO.getById(id);

        if (client.isPresent()) {
            return client.get();
        } else {
            return null;
        }
    }

    public void loadObserversFromDB() throws SQLException {
        List<Client> clients = clientDAO.findAll();
        for (Client client : clients) {
            if (client.isAcceptsNotifications()) {
                notificationService.addObserver(client);
            }
        }
    }

    public List<Client> getAllClients() throws SQLException {
        if (clientDAO.findAll().isEmpty()) {
            throw new EmptyListException(CLIENT_LIST_EMPTY);
        }
        return clientDAO.findAll();
    }
}
