package escapeRoom;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.model.Room;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.service.EscapeRoomService;
import cat.itacademy.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomServiceTest {
    
    
    private EscapeRoomService escapeRoomService;

    @BeforeEach
    void setUp() {
        escapeRoomService = new EscapeRoomService();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM room");
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM escape_room")){
            stmt.executeUpdate();
            stmt2.executeUpdate();
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

    @Test
    public void whenEscapeRoomListIsEmpty_thenEmptyListExceptionIsThrown() throws SQLException {
        assertThrows(EmptyListException.class, () -> escapeRoomService.getAllEscapeRooms());
    }

    @Test
    public void shouldUpdateEscaperoomId_whenRoomIsAssignedToEscapeRoom() throws SQLException {
        escapeRoomService.addEscapeRoom(new EscapeRoom ("ScaryRoom"));
        RoomService roomService = new RoomService();
        roomService.addRoom(new Room("Psicosis3", "Terror",  3));

        int roomId = roomService.getLastRoom().getId();
        int EscapeRoomId = escapeRoomService.getLastEscapeRoom().getId();

        escapeRoomService.addRoomToEscapeRoom(EscapeRoomId, roomId);

        assertEquals(EscapeRoomId, roomService.getRoomById(roomId).getEscapeRoomId());
    }

    @Test
    public void whenRemoveRoomFromEscapeRoom_thenRoomEscapeRoomIdIsEmpty() throws SQLException {
        RoomService roomService = new RoomService();
        escapeRoomService.addEscapeRoom(new EscapeRoom("ScaryRoom"));
        roomService.addRoom(new Room("Psicosis3", "Terror", 3));

        int roomId = roomService.getLastRoom().getId();
        int escapeRoomId = escapeRoomService.getLastEscapeRoom().getId();

        escapeRoomService.addRoomToEscapeRoom(escapeRoomId, roomId);

        Room roomBeforeOpt = roomService.getRoomById(roomId);
        assertTrue(roomBeforeOpt != null);
        assertEquals(Integer.valueOf(escapeRoomId), roomBeforeOpt.getEscapeRoomId());

        escapeRoomService.removeRoomFromEscapeRoom(roomId);

        Room roomAfterOpt = roomService.getRoomById(roomId);

        assertTrue(roomAfterOpt != null);
        assertTrue(roomAfterOpt.getEscapeRoomIdOpt().isEmpty());
    }
}

