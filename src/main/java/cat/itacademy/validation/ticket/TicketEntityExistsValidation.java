package cat.itacademy.validation.ticket;

import cat.itacademy.exception.EntityNotFoundException;
import static cat.itacademy.message.error.ClientErrorMessages.*;
import static cat.itacademy.message.error.RoomErrorMessages.*;
import cat.itacademy.model.Ticket;
import cat.itacademy.service.ClientService;
import cat.itacademy.service.RoomService;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;

public class TicketEntityExistsValidation implements ValidationStrategy<Ticket> {
    private ClientService clientService;
    private RoomService roomService;

    public TicketEntityExistsValidation(ClientService clientService, RoomService roomService) {
        this.clientService = clientService;
        this.roomService = roomService;
    }

    @Override
    public void validate(Ticket ticket) throws EntityNotFoundException, SQLException {
        if(clientService.getClientById(ticket.getClientId()) == null){
            throw new EntityNotFoundException(CLIENT_NOT_FOUND);
        }
        if(roomService.getRoomById(ticket.getRoomId()) == null){
            throw new EntityNotFoundException(ROOM_NOT_FOUND);
        }
    }
}
