package ticket;

import cat.itacademy.model.Client;
import cat.itacademy.model.Room;
import cat.itacademy.model.Ticket;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.repository.util.DatabaseCleaner;
import cat.itacademy.service.ClientService;
import cat.itacademy.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {
    private Ticket ticket;
    private int clientId;
    private int roomId;

    @BeforeEach
    public void setUp() throws SQLException {
        DatabaseCleaner.clearAllTables();

        ClientService clientService = new ClientService();
        clientService.addClient(new Client("luri", "luri@gmail.com", "987654321"));
        clientId = clientService.getLastClient().getId();

        RoomService roomService = new RoomService();
        roomService.addRoom(new Room("room1", "intriga", 2));
        roomId = roomService.getLastRoom().getId();

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.prepareStatement("DELETE FROM client").executeUpdate();
        }
    }

    @Test
    public void createTicket_Success() {
        ticket = new Ticket(clientId, roomId);
        assertAll(
                ()->assertEquals(clientId, ticket.getClientId()),
                ()->assertEquals(roomId, ticket.getRoomId())
        );
    }
}
