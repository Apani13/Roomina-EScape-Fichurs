package cat.itacademy.validation.clue;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.model.Clue;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;
import java.util.List;

public class ClueValidator {
    private List<ValidationStrategy<Clue>> strategies;

    public ClueValidator(List<ValidationStrategy<Clue>> strategies) {
        this.strategies = strategies;
    }

    public void validate(Clue clue) throws DuplicateException, SQLException {
        for (ValidationStrategy<Clue> strategy : strategies) {
            strategy.validate(clue);
        }
    }
}
