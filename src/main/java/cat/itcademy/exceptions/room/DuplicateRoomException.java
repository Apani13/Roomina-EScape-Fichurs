package cat.itcademy.exceptions.room;

public class DuplicateRoomException extends RuntimeException {
    public DuplicateRoomException(String message) {
        super(message);
    }
}
