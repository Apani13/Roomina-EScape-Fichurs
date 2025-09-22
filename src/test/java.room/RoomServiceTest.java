import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAtributeException;
import cat.itacademy.models.Room;
import cat.itacademy.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomServiceTest {

    private RoomService roomService;

    @BeforeEach
    void setUp() {
        roomService = new RoomService();
    }

    @Test
    public void whenCreatedRoomWithEmptyAtributes_thenInvalidNameExceptionIsThrown() {

        Room roomWithEmptyName = new Room("", "Terror", 2);
        Room roomWithNullTheme = new Room("Chuky", null, 2);
        Room roomWithInvalidLevel = new Room("Chuky", "Terror", 0);

        assertAll("Probando que se lanzan las excepciones correctamente:",
                () -> assertThrows(InvalidAtributeException.class,
                        () -> roomService.addRoom(roomWithEmptyName),
                        "Debería lanzar excepción InvalidAtributeException"),
                () -> assertThrows(InvalidAtributeException.class,
                        () -> roomService.addRoom(roomWithNullTheme),
                        "Debería lanzar excepción InvalidAtributeException"),
                () -> assertThrows(InvalidAtributeException.class,
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
}

