package ticket;

import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.RecordNotFoundException;
import cat.itacademy.model.Client;
import cat.itacademy.model.Room;
import cat.itacademy.model.Ticket;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.service.ClientService;
import cat.itacademy.service.RoomService;
import cat.itacademy.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceTest {
    private TicketService ticketService;
    private ClientService clientService;
    RoomService roomService;
    @BeforeEach
    public void setUp() throws SQLException {
        ticketService = new TicketService();
        clientService = new ClientService();
        roomService = new RoomService();

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.prepareStatement("DELETE FROM clue").executeUpdate();
            conn.prepareStatement("DELETE FROM ticket").executeUpdate();
            conn.prepareStatement("DELETE FROM client").executeUpdate();
            conn.prepareStatement("DELETE FROM room").executeUpdate();
        }
    }

    @Test
    public void whenCreatedTicketWithEmptyAttributes_thenInvalidAttributeExceptionIsThrown(){
        assertAll(
                ()-> assertThrows(InvalidAttributeException.class, ()->ticketService.addTicket(new Ticket(null,1))),
                ()-> assertThrows(InvalidAttributeException.class, ()->ticketService.addTicket(new Ticket(0,1))),
                ()-> assertThrows(InvalidAttributeException.class, ()->ticketService.addTicket(new Ticket(1,0))),
                ()-> assertThrows(InvalidAttributeException.class, ()->ticketService.addTicket(new Ticket(1,null)))
        );
    }

    @Test
    public void whenCreatedTicketWithFalseAttributes_thenRecordNotFoundExceptionIsThrown(){
        clientService.addClient(new Client("luri", "luri@gmail.com", "98765432", true));

        assertAll(
                ()->assertThrows(RecordNotFoundException.class, ()->ticketService.addTicket(new Ticket(100,1))),
                ()->assertThrows(RecordNotFoundException.class, ()->ticketService.addTicket(new Ticket(clientService.getLastClient().getId(),100)))
        );
    }

    @Test
    public void shouldFillDateAndPrice_whenTicketIsCreated() throws SQLException {

        clientService.addClient(new Client("luri", "luri@gmail.com", "98765432", true));
        roomService.addRoom(new Room("room1", "terror", 2));
        
        Client client = clientService.getLastClient();
        Room room = roomService.getLastRoom();

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ticketService.addTicket(new Ticket(client.getId(), room.getId()));
        Ticket ticketDB = ticketService.getLastTicket();
        System.setOut(originalOut);
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDate date = dateTime.toLocalDate();
        assertAll(
                ()-> assertEquals(room.getPrice(), ticketDB.getTotalPrice()),
                ()-> assertEquals(room.getId(), ticketDB.getRoomId()),
                ()-> assertEquals(client.getId(), ticketDB.getClientId()),
                ()-> assertNotNull(ticketDB.getDateCreation()),
                ()-> assertEquals("Ticket{Sala: room1, Precio: 25.0, Fecha: "+ date +"}",  outContent.toString().trim())
        );
    }
}
