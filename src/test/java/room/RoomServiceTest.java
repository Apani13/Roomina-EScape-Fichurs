package room;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.Clue;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.model.Room;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.service.ClueService;
import cat.itacademy.service.EscapeRoomService;
import cat.itacademy.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class RoomServiceTest {

    private RoomService roomService;

    @BeforeEach
    void setUp() {
        roomService = new RoomService();
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.prepareStatement("DELETE FROM clue").executeUpdate();
            conn.prepareStatement("DELETE FROM room").executeUpdate();
            conn.prepareStatement("DELETE FROM escape_room").executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void whenCreatedRoomWithEmptyAtributes_thenInvalidNameExceptionIsThrown() {

        Room roomWithEmptyName = new Room("", "Terror", 2);
        Room roomWithNullTheme = new Room("Chuky", null, 2);
        Room roomWithInvalidLevel = new Room("Chuky", "Terror", 0);

        assertAll("Probando que se lanzan las excepciones correctamente:",
                () -> assertThrows(InvalidAttributeException.class,
                        () -> roomService.addRoom(roomWithEmptyName),
                        "Debería lanzar excepción InvalidAtributeException"),
                () -> assertThrows(InvalidAttributeException.class,
                        () -> roomService.addRoom(roomWithNullTheme),
                        "Debería lanzar excepción InvalidAtributeException"),
                () -> assertThrows(InvalidAttributeException.class,
                        () -> roomService.addRoom(roomWithInvalidLevel),
                        "Debería lanzar excepción InvalidAtributeException")
        );
    }

    @Test
    public void whenCreatedRoomIfAlreadyExists_thenDuplicatedRoomExceptionIsThrown() {
        Room room1 = new Room("Chucky", "Terror", 2);
        Room room2 = new Room("Chucky", "Terror", 2);

        roomService.addRoom(room1);
        assertThrows(DuplicateException.class, () -> roomService.addRoom(room2));
    }

    @Test
    public void whenRoomListIsEmpty_thenThrowsEmptyListException() throws SQLException {
        assertThrows(EmptyListException.class, () -> roomService.getAllRooms());
    }

    @Test
    public void shouldUpdateRoomId_whenClueIsAssignedToEscapeRoom() throws SQLException {
        roomService.addRoom(new Room("room1", "terror", 1));
        ClueService clueService = new ClueService();
        clueService.addClue(new Clue("clue1", "diversion", "icabcuani", 10));

        int roomId = roomService.getLastRoom().get().getId();
        int clueId = clueService.getLastClue().getId();

        roomService.addClueToRoom(roomId, clueId);

        assertEquals(roomId, clueService.getClueById(clueId).getRoomId());
    }
}

