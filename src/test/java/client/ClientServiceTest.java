package client;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.Client;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {
    private ClientService clientService;

    @BeforeEach
    void setUp(){
        clientService = new ClientService();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM client")) {
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
    void whenCreatingClientWithNullOrEmptyAttribute_thenInvalidAttributeExceptionIsThrown() {
        EscapeRoom escapeRoom = new EscapeRoom("");
        assertAll("Validate attributes",
                ()->assertThrows(InvalidAttributeException.class, ()-> clientService.addClient(new Client("", "luri@gmail.com", "654321789", true))),
                ()->assertThrows(InvalidAttributeException.class, ()-> clientService.addClient(new Client("luri1", "", "654321783", true))),
                ()->assertThrows(InvalidAttributeException.class, ()-> clientService.addClient(new Client("luri2", "luri2@gmail.com", "", true)))
        );
    }

    @Test
    void whenCreatingDuplicateClient_thenDuplicateExceptionIsThrown() {
        Client client = new Client("luri", "luri@gmail.com", "698765432", true);

        clientService.addClient(client);
        assertAll("duplicate record check",
                ()->assertThrows(DuplicateException.class, ()-> clientService.addClient(new Client("luri", "luri@gmail.com", "654321789", true)))
        );
    }
}
