package cat.itacademy.service;

import cat.itacademy.exception.EmptyListException;
import cat.itacademy.repository.DAO.ClientDAO;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.Client;
import cat.itacademy.message.error.ClientErrorMessages;
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
    private ClientDAO clientDAO;
    private ClientValidator clientValidator;

    public ClientService() {
        this.clientDAO = new ClientDAO();
        this.clientValidator = new ClientValidator(List.of(
                new ClientBasicValidation(),
                new ClientDuplicateValidation(clientDAO)
        ));
    }

    public void addClient(Client client) {
        try {
            clientValidator.validate(client);

            clientDAO.insert(client);

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
        Optional<Client> client = clientDAO.getLastClient();

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

    public List<Client> getAllClients() throws SQLException {
        if (clientDAO.findAll().isEmpty()) {
            throw new EmptyListException(CLIENT_LIST_EMPTY);
        }
        return clientDAO.findAll();
    }
}
