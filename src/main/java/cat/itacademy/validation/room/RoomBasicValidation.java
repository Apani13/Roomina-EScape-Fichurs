package cat.itacademy.validation.room;

import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import static cat.itacademy.message.error.RoomErrorMessages.*;
import cat.itacademy.model.Room;
import cat.itacademy.validation.ValidationStrategy;

public class RoomBasicValidation implements ValidationStrategy<Room> {
    @Override
    public void validateAvailableInventory(Room room) throws InvalidAttributeException, NullObjectException {
        if (room == null) {
            throw new NullObjectException(ROOM_NULL_OBJECT);
        }
        if (room.getName() == null || room.getName().isEmpty()) {
            throw new InvalidAttributeException(ROOM_NAME_NULL_EMPTY);
        }
        if (room.getTheme() == null || room.getTheme().isEmpty()) {
            throw new InvalidAttributeException(ROOM_THEME_NULL_EMPTY);
        }

        if (room.getLevel() <= 0 ) {
            throw new InvalidAttributeException(ROOM_LEVEL_INVALID);
        }
    }
}
