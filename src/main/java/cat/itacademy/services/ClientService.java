package cat.itacademy.services;

import cat.itacademy.controllers.ClientDAO;
import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.Client;
import cat.itacademy.utils.ClientErrorMessages;
import cat.itacademy.utils.ClienteSuccessMessages;

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
            Client lastClient = clientDAO.getLastClient();
            System.out.println(String.format(ClienteSuccessMessages.CLIENT_CREATED, lastClient.getUserName(), lastClient.getEmail(), lastClient.getPhone(), lastClient.isAcceptsNotifications()));
        } catch (DuplicateException | InvalidAttributeException e) {
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }
    }
}
