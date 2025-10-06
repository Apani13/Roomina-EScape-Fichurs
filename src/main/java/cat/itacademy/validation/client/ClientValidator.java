package cat.itacademy.validation.client;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import cat.itacademy.model.Client;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;
import java.util.List;

public class ClientValidator {
    private List<ValidationStrategy<Client>> strategies;

    public ClientValidator(List<ValidationStrategy<Client>> strategies) {
        this.strategies = strategies;
    }

    public void validate(Client client) throws DuplicateException, InvalidAttributeException, NullObjectException, SQLException {
        for (ValidationStrategy<Client> strategy : strategies) {
            strategy.validateAvailableInventory(client);
        }
    }
}
