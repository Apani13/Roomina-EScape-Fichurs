package cat.itacademy.service;

import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.EntityNotFoundOnDBException;
import cat.itacademy.message.success.TicketSuccessMessages;
import cat.itacademy.model.Room;
import cat.itacademy.model.Ticket;
import cat.itacademy.repository.daoImpl.TicketDaoImpl;
import cat.itacademy.validation.ticket.TicketBasicValidation;
import cat.itacademy.validation.ticket.TicketEntityExistsValidation;
import cat.itacademy.validation.ticket.TicketValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TicketService {
    private TicketDaoImpl ticketDAO;
    private ClientService clientService;
    private RoomService roomService;
    private TicketValidator ticketValidator;

    public TicketService() {
        this.ticketDAO = new TicketDaoImpl();
        this.clientService = new ClientService();
        this.roomService = new RoomService();
        this.ticketValidator = new TicketValidator(List.of(
                new TicketBasicValidation(),
                new TicketEntityExistsValidation(clientService, roomService)
        ));
    }

    public void addTicket(Ticket ticket) throws SQLException {
        try {
            ticketValidator.validate(ticket);

            ticketDAO.insert(ticket);
            Ticket ticketDB = getLastTicket();
            Room room = roomService.getRoomById(ticket.getRoomId());
            System.out.println(String.format(TicketSuccessMessages.TICKET_CREATED_MESSAGE, room.getName(), ticketDB.getTotalPrice(), ticketDB.getDateCreationFormat()));
        }catch (EntityNotFoundOnDBException | InvalidAttributeException e) {
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }
    }

    public Ticket getLastTicket() throws SQLException {
        Optional<Ticket> ticket = ticketDAO.getLast();

        if (ticket.isPresent()) {
            return ticket.get();
        } else {
            return null;
        }
    }
}
