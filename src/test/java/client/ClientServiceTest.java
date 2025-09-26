package client;

import cat.itacademy.models.EscapeRoom;
import cat.itacademy.repositories.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientServiceTest {
    private ClientService clientService;

    @BeforeEach
    void setUp(){
        clientService = new ClientService();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM escape_room")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenCreatingEscapeRoomWithValidData_thenConfirmationMessageIsShown() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Client client = new Client("luri", "luri@gmail.com", "698765432", true);
        clientService.addClient(client);

        System.setOut(originalOut);
        assertEquals("Datos del cliente: {userName: luri, email: luri@gmail.com, phone: 698765432, AcceptsNotifications: true}",  outContent.toString().trim());
    }

    @Test
    void whenCreatingEscapeRoomWithInvalidData_thenConfirmationMessageIsShown() {}
}
