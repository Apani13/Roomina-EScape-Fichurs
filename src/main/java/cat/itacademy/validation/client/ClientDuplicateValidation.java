package cat.itacademy.validation.client;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.message.error.ClientErrorMessages;
import cat.itacademy.model.Client;
import cat.itacademy.repository.daoImpl.ClientDaoImpl;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;

public class ClientDuplicateValidation implements ValidationStrategy<Client> {
    ClientDaoImpl clientDAO;
    public ClientDuplicateValidation(ClientDaoImpl clientDAO) {
        this.clientDAO = clientDAO;
    }
    @Override
    public void validateAvailableInventory(Client client) throws DuplicateException, SQLException {
         if(clientDAO.existsByName(client.getUserName())){
             throw new DuplicateException(ClientErrorMessages.CLIENT_DUPLICATED);
         }
     }
}
