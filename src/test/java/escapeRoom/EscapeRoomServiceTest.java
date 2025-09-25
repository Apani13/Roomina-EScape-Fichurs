package escapeRoom;

import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.EscapeRoom;
import cat.itacademy.repositories.DatabaseConnection;
import cat.itacademy.services.EscapeRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomServiceTest {
    private EscapeRoomService escapeRoomService;

    @BeforeEach
    void setUp() {
        escapeRoomService = new EscapeRoomService();
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

        EscapeRoom escapeRoom = new EscapeRoom("EscapeRoomIT");
        escapeRoomService.addEscapeRoom(escapeRoom);

        System.setOut(originalOut);
        assertEquals("El escape room EscapeRoomIT se registro correctamente",  outContent.toString().trim());
    }

    @Test
    void whenCreatingEscapeRoomWithNullOrEmptyName_thenInvalidNameExceptionIsThrown() {
        EscapeRoom escapeRoom = new EscapeRoom("");

        assertThrows(InvalidAttributeException.class, ()-> escapeRoomService.addEscapeRoom(escapeRoom));
    }

    @Test
    void whenCreatingDuplicateEscapeRoom_thenDuplicateEscapeRoomExceptionIsThrown() {
        EscapeRoom escapeRoom = new EscapeRoom("EscapeRoom1");
        escapeRoomService.addEscapeRoom(escapeRoom);
        assertThrows(DuplicateException.class, ()-> escapeRoomService.addEscapeRoom(escapeRoom));
    }
}