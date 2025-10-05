package cat.itacademy.validation.ticket;

import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import static cat.itacademy.message.error.TicketErrorMessages.*;
import cat.itacademy.model.Ticket;
import cat.itacademy.validation.ValidationStrategy;

public class TicketBasicValidation implements ValidationStrategy<Ticket> {
    @Override
    public void validate(Ticket ticket) throws InvalidAttributeException, NullObjectException  {
        if(ticket == null){
            throw new NullObjectException(TICKET_NULL_OBJECT);
        }
        if(ticket.getClientId() == null || ticket.getClientId() <= 0) {
            throw new InvalidAttributeException(TICKET_CLIENT_ID_NULL_EMPTY);
        }
        if(ticket.getRoomId() == null || ticket.getRoomId() <= 0) {
            throw new InvalidAttributeException(TICKET_ROOM_ID_NULL_EMPTY);
        }
    }
}
