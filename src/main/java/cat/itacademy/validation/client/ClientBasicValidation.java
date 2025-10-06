package cat.itacademy.validation.client;

import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import cat.itacademy.model.Client;
import cat.itacademy.validation.ValidationStrategy;

import static cat.itacademy.message.error.ClientErrorMessages.*;

public class ClientBasicValidation implements ValidationStrategy<Client> {
    @Override
    public void validateAvailableInventory(Client client) throws InvalidAttributeException, NullObjectException {
        if(client == null){
            throw new NullObjectException(CLIENT_NULL_OBJECT);
        }
        if (client.getUserName() == null || client.getUserName().isEmpty()) {
            throw new InvalidAttributeException(CLIENT_USERNAME_NULL_EMPTY);
        }
        if (client.getEmail() == null || client.getEmail().isEmpty()) {
            throw new InvalidAttributeException(CLIENT_EMAIL_NULL_EMPTY);
        }
        if (client.getPhone() == null || client.getPhone().isEmpty()) {
            throw new InvalidAttributeException(CLIENT_PHONE_NULL_EMPTY);
        }
    }
}
