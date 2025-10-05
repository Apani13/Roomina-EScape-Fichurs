package cat.itacademy.validation.escapeRoom;

import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import static cat.itacademy.message.error.EscapeRoomErrorMessages.*;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.validation.ValidationStrategy;

public class EscapeRoomBasicValidation implements ValidationStrategy<EscapeRoom> {
    @Override
    public void validate(EscapeRoom escapeRoom) throws InvalidAttributeException, NullObjectException {
        if(escapeRoom == null){
            throw new NullObjectException(ESCAPEROOM_NULL_OBJECT);
        }
        if (escapeRoom.getName() == null || escapeRoom.getName().isEmpty()) {
            throw new InvalidAttributeException(ESCAPEROOM_NAME_NULL_EMPTY);
        }
    }
}
