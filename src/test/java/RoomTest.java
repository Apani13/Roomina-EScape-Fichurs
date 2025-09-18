import cat.itcademy.exceptions.InvalidRoomAtributeException;
import cat.itcademy.models.Room;
import cat.itcademy.services.RoomManagement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    private Room room;
    private RoomManagement roomManagement;

    @BeforeEach
    void init() {
        room = new Room("Chuky", "Terror", 2);
        roomManagement = new RoomManagement();
    }

    @Test
    public void createRoom_Succes() {
        Assertions.assertAll("Probant que els atributs de la sala son correctes: ",
                () -> assertEquals("Chuky", room.getName(), "El nom ha de ser Chuky"),
                () -> assertEquals("Terror", room.getTheme(), "La temàtica ha de ser Terror"),
                () -> assertEquals(2, room.getLevel(), "El nivell ha de ser 2"),
                () -> assertEquals(25.0, room.getPrice(), "El preu ha de ser 25.0"));
    }
}


    

