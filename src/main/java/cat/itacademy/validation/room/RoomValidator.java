package cat.itacademy.validation.room;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import cat.itacademy.model.Room;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomValidator {
    private List<ValidationStrategy<Room>> strategies;

    public RoomValidator(List<ValidationStrategy<Room>> strategies) {
        this.strategies = strategies;
    }

    public void validate(Room room) throws DuplicateException, InvalidAttributeException, NullObjectException, SQLException {
        for (ValidationStrategy<Room> strategy : strategies) {
            strategy.validate(room);
        }
    }
}
