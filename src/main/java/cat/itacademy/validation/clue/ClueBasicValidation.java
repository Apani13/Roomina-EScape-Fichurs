package cat.itacademy.validation.clue;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import cat.itacademy.model.Clue;
import cat.itacademy.validation.ValidationStrategy;

import static cat.itacademy.message.error.ClueErrorMessages.*;

public class ClueBasicValidation implements ValidationStrategy<Clue> {
    @Override
    public void validateAvailableInventory(Clue clue) throws DuplicateException, InvalidAttributeException, NullObjectException{
        if (clue == null) {
            throw new NullObjectException(CLUE_NULL_OBJECT);
        }

        if (Double.isNaN(clue.getPrice()) || clue.getPrice() <= 0) {
            throw new InvalidAttributeException(CLUE_PRICE_INVALID);
        }

        if (clue.getName() == null || clue.getName().isBlank()) {
            throw new InvalidAttributeException(CLUE_NAME_NULL_EMPTY);
        }

        if (clue.getDescription() == null || clue.getDescription().isBlank()) {
            throw new InvalidAttributeException(CLUE_DESC_NULL_EMPTY);
        }

        if (clue.getTheme() == null || clue.getTheme().isBlank()) {
            throw new InvalidAttributeException(CLUE_THEME_NULL_EMPTY);
        }
    }
}
