package escapeRoom;

import cat.itacademy.repositories.DatabaseConnection;
import cat.itacademy.exceptions.DuplicateEscapeRoomException;
import cat.itacademy.exceptions.InvalidNameException;
import cat.itacademy.models.EscapeRoom;
import cat.itacademy.services.EscapeRoomManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomManagementTest {
    private EscapeRoomManagement escapeRoomManagement;
    @BeforeEach
    void setUp() throws InvalidNameException {
        escapeRoomManagement = new EscapeRoomManagement();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM escaperoom")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void whenCreatingEscapeRoomWithValidData_thenConfirmationMessageIsShown(){
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EscapeRoom escapeRoom = new EscapeRoom("EscapeRoomIT");
        escapeRoomManagement.addEscapeRoom(escapeRoom);
        System.setOut(originalOut);

        assertEquals("El escape room EscapeRoomIT se registro correctamente",  outContent.toString().trim());
    }
    @Test
    void whenCreatingEscapeRoomWithNullOrEmptyName_thenInvalidNameExceptionIsThrown(){
        EscapeRoom escapeRoom = new EscapeRoom("");

        assertThrows(InvalidNameException.class, ()->escapeRoomManagement.addEscapeRoom(escapeRoom));
    }

    @Test
    void whenCreatingDuplicateEscapeRoom_thenDuplicateEscapeRoomExceptionIsThrown(){
        EscapeRoom escapeRoom = new EscapeRoom("EscapeRoom1");


        EscapeRoom escapeRoomInitial = new EscapeRoom("EscapeRoom1");
        escapeRoomManagement.addEscapeRoom(escapeRoomInitial);

        assertThrows(DuplicateEscapeRoomException.class, ()->escapeRoomManagement.addEscapeRoom(escapeRoom));
    }

    @Test
    void prueba() throws SQLException, InvalidNameException, DuplicateEscapeRoomException {
        EscapeRoom escapeRoom = new EscapeRoom("EscapeRoom10");
        escapeRoomManagement.addEscapeRoom(escapeRoom);

        EscapeRoom last = escapeRoomManagement.getLastEscapeRoom();
        System.out.println(last.toString());
        assertNotNull(last);
        assertEquals("EscapeRoom10", last.getName());
    }

}