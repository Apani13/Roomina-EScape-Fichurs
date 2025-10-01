package cat.itacademy.service;

import cat.itacademy.repository.DAO.ClientDAO;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.Client;
import cat.itacademy.message.error.ClientErrorMessages;
import cat.itacademy.message.success.ClienteSuccessMessages;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

public class ClientService {
    private ClientDAO clientDAO;

    public ClientService() {
        clientDAO = new ClientDAO();
    }

    public void addClient(Client client) {
        try {
            if (client.getUserName() == null || client.getUserName().isEmpty()) {
                throw new InvalidAttributeException(ClientErrorMessages.CLIENT_USERNAME_NULL_EMPTY);
            }
            if (client.getEmail() == null || client.getEmail().isEmpty()) {
                throw new InvalidAttributeException(ClientErrorMessages.CLIENT_EMAIL_NULL_EMPTY);
            }
            if (client.getPhone() == null || client.getPhone().isEmpty()) {
                throw new InvalidAttributeException(ClientErrorMessages.CLIENT_PHONE_NULL_EMPTY);
            }
            if(clientDAO.existsByUserName(client.getUserName())){
                throw new DuplicateException(ClientErrorMessages.CLIENT_DUPLICATED);
            }

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
}
