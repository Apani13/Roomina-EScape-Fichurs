package room;

import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.EmptyListException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.Clue;
import cat.itacademy.models.Room;
import cat.itacademy.repositories.DatabaseConnection;
import cat.itacademy.services.ClueService;
import cat.itacademy.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class RoomServiceTest {

    private RoomService roomService;

    @BeforeEach
    void setUp() {
        roomService = new RoomService();
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.prepareStatement("DELETE FROM clue").executeUpdate();
            conn.prepareStatement("DELETE FROM room").executeUpdate();
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
    public void whenCreatedRoomIfAlreadyExists_thenDuplicatedRoomExceptionIsThrown()  {
        Room room1 = new Room("Chucky", "Terror", 2);
        Room room2 = new Room("Chucky", "Terror", 2);

        roomService.addRoom(room1);
        assertThrows(DuplicateException.class, () -> roomService.addRoom(room2));
    }

    @Test
    public void whenRoomListIsEmpty_thenThrowsEmptyListException() throws SQLException {
        assertThrows(EmptyListException.class, ()->roomService.getAllRooms());
    }

    @Test
    public void shouldUpdateRoomId_whenClueIsAssignedToEscapeRoom() throws SQLException {
        roomService.addRoom(new Room("room1", "terror", 1));
        ClueService clueService = new ClueService();
        clueService.addClue(new Clue("clue1", "diversion", "icabcuani", 10));

        int roomId = roomService.getLastRoom().getId();
        int clueId = clueService.getLastClue().getId();

        roomService.addClueToRoom(roomId, clueId);

        assertEquals(roomId, clueService.getClueById(clueId).getRoomId());
    }

    @Test
    public void whenRoomWithClueListIsEmpty_thenThrowsEmptyListException() throws SQLException {
        assertThrows(EmptyListException.class, ()->roomService.getRoomsWithClues());
    }

    @Test
    public void whenRoomListWithClue_thenReturnARecord(){
        roomService.addRoom(new Room("Slipknot", "music", 2));
        roomService.addRoom(new Room("saw", "terror", 3));

        ClueService clueService = new ClueService();

    }
}

