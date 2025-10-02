package cat.itacademy.validation.escapeRoom;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;
import java.util.List;

public class EscapeRoomValidator {
    private List<ValidationStrategy<EscapeRoom>> strategies;

    public EscapeRoomValidator(List<ValidationStrategy<EscapeRoom>> strategies) {
        this.strategies = strategies;
    }

    public void validate(EscapeRoom escapeRoom) throws DuplicateException, SQLException {
        for (ValidationStrategy<EscapeRoom> strategy : strategies) {
            strategy.validate(escapeRoom);
        }
    }
}
