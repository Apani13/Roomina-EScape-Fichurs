package cat.itacademy.validation.ticket;

import cat.itacademy.exception.EntityNotFoundOnDBException;
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
    public void validateAvailableInventory(Ticket ticket) throws EntityNotFoundOnDBException, SQLException {
        if(clientService.getClientById(ticket.getClientId()) == null){
            throw new EntityNotFoundOnDBException(CLIENT_NOT_FOUND);
        }
        if(roomService.getRoomById(ticket.getRoomId()) == null){
            throw new EntityNotFoundOnDBException(ROOM_NOT_FOUND);
        }
    }
}
