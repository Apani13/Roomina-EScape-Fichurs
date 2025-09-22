import cat.itcademy.exceptions.room.DuplicateRoomException;
import cat.itcademy.exceptions.room.InvalidRoomAtributeException;
import cat.itcademy.models.Room;
import cat.itcademy.services.RoomManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomManagementTest {

    private RoomManagement roomManagement;

    @BeforeEach
    void setUp() {
        roomManagement = new RoomManagement();
    }

    @Test
    public void whenCreatedRoomWithEmptyAtributes_thenInvalidNameExceptionIsThrown() {

        Room roomWithEmptyName = new Room("", "Terror", 2);
        Room roomWithNullTheme = new Room("Chuky", null, 2);
        Room roomWithInvalidLevel = new Room("Chuky", "Terror", 0);

        assertAll("Probando que se lanzan las excepciones correctamente:",
                () -> assertThrows(InvalidRoomAtributeException.class,
                        () -> roomManagement.addRoom(roomWithEmptyName),
                        "Debería lanzar excepción InvalidAtributeException"),
                () -> assertThrows(InvalidRoomAtributeException.class,
                        () -> roomManagement.addRoom(roomWithNullTheme),
                        "Debería lanzar excepción InvalidAtributeException"),
                () -> assertThrows(InvalidRoomAtributeException.class,
                        () -> roomManagement.addRoom(roomWithInvalidLevel),
                        "Debería lanzar excepción InvalidAtributeException")
        );
    }

    @Test
    public void whenCreatedRoomIfAlreadyExists_thenDuplicatedRoomExceptionIsThrown() {
        Room room1 = new Room("Chucky", "Terror", 2);
        Room room2 = new Room("Chucky", "Terror", 2);

        roomManagement.addRoom(room1);
        assertThrows(DuplicateRoomException.class, () -> roomManagement.addRoom(room2));
    }
}

