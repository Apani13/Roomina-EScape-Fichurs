package escapeRoom;

import cat.itacademy.model.EscapeRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomTest {
    private EscapeRoom escapeRoom;
    @BeforeEach
    void init() {
        escapeRoom = new EscapeRoom("EscapeRoomITA");
    }
    @Test
    void whenCreatingEscapeRoom_thenItIsSavedSuccessfully(){
        assertEquals("EscapeRoomITA", escapeRoom.getName());
    }
}
