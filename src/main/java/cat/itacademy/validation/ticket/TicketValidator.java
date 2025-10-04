package cat.itacademy.validation.ticket;

import cat.itacademy.exception.EntityNotFoundException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import cat.itacademy.model.Ticket;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;
import java.util.List;

public class TicketValidator {
    private List<ValidationStrategy> strategies;

    public TicketValidator(List<ValidationStrategy> strategies) {
        this.strategies = strategies;
    }

    public void validate(Ticket ticket) throws EntityNotFoundException, InvalidAttributeException, NullObjectException, SQLException {
        for (ValidationStrategy<Ticket> strategy : strategies) {
            strategy.validateAvailableInventory(ticket);
        }
    }
}
