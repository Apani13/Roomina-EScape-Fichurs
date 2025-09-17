package escapeRoom;

import cat.itacademy.exceptions.DuplicateEscapeRoomException;
import cat.itacademy.exceptions.InvalidNameException;
import cat.itacademy.models.EscapeRoom;
import cat.itacademy.services.EscapeRoomManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomManagementTest {
    private EscapeRoomManagement escapeRoomManagement;
    @BeforeEach
    void setUp() throws InvalidNameException {
        escapeRoomManagement = new EscapeRoomManagement();

        EscapeRoom escapeRoomInitial = new EscapeRoom("EscapeRoom1");

        escapeRoomManagement.addEscapeRoom(escapeRoomInitial);
    }
    @Test
    void whenCreatingEscapeRoomWithValidData_thenConfirmationMessageIsShown() throws InvalidNameException {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EscapeRoom escapeRoom = new EscapeRoom("EscapeRoomIT");
        escapeRoomManagement.addEscapeRoom(escapeRoom);

        System.setOut(originalOut);
        assertEquals("El escape room EscapeRoomIT se registro correctamente",  outContent.toString().trim());
    }
    @Test
    void whenCreatingEscapeRoomWithNullOrEmptyName_thenInvalidNameExceptionIsThrown() throws InvalidNameException {
        EscapeRoom escapeRoom = new EscapeRoom("");

        assertThrows(InvalidNameException.class, ()->escapeRoomManagement.addEscapeRoom(escapeRoom));
    }

    @Test
    void whenCreatingDuplicateEscapeRoom_thenDuplicateEscapeRoomExceptionIsThrown() throws DuplicateEscapeRoomException {
        EscapeRoom escapeRoom = new EscapeRoom("EscapeRoom1");

        assertThrows(DuplicateEscapeRoomException.class, ()->escapeRoomManagement.addEscapeRoom(escapeRoom));
    }
}