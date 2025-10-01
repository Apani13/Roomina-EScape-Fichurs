package cat.itacademy.service;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.RecordNotFoundException;
import cat.itacademy.message.error.ClientErrorMessages;
import cat.itacademy.message.error.RoomErrorMessages;
import cat.itacademy.message.error.TicketErrorMessages;
import cat.itacademy.message.success.TicketSuccessMessages;
import cat.itacademy.model.Client;
import cat.itacademy.model.Room;
import cat.itacademy.model.Ticket;
import cat.itacademy.repository.DAO.TicketDAO;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

public class TicketService {
    private TicketDAO ticketDAO;
    private ClientService clientService;
    private RoomService roomService;

    public TicketService() {
        ticketDAO = new TicketDAO();
        clientService = new ClientService();
        roomService = new RoomService();
    }

    public void addTicket(Ticket ticket) throws SQLException {
        try {
            if(ticket.getClientId()==null || ticket.getClientId() <= 0) {
                throw new InvalidAttributeException(TicketErrorMessages.TICKET_CLIENT_ID_NULL_EMPTY);
            }
            if(ticket.getRoomId()==null || ticket.getRoomId() <= 0) {
                throw new InvalidAttributeException(TicketErrorMessages.TICKET_ROOM_ID_NULL_EMPTY);
            }

            if(clientService.getClientById(ticket.getClientId()) == null){
                throw new RecordNotFoundException(ClientErrorMessages.CLIENT_NOT_FOUND);
            }
            if(roomService.getRoomById(ticket.getRoomId()) == null){
                throw new RecordNotFoundException(RoomErrorMessages.ROOM_NOT_FOUND);
            }

            ticketDAO.insert(ticket);
            Ticket ticketDB = getLastTicket();
            Room room = roomService.getRoomById(ticket.getRoomId());
            System.out.println(String.format(TicketSuccessMessages.TICKET_CREATED, room.getName(), ticketDB.getTotalPrice(), ticketDB.getDateCreationFormat()));
        }catch (RecordNotFoundException | InvalidAttributeException e) {
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }
    }

    public Ticket getLastTicket() throws SQLException {
        Optional<Ticket> ticket = ticketDAO.getLastRecord();
        if (ticket.isPresent()) {
            return ticket.get();
        } else {
            return null;
        }
    }
}
