package cat.itacademy.validation.clue;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.model.Clue;
import cat.itacademy.repository.DAO.ClueDAO;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;

import static cat.itacademy.message.error.ClueErrorMessages.CLUE_DUPLICATED;

public class ClueDuplicateValidation implements ValidationStrategy<Clue> {
    private ClueDAO clueDAO;

    public ClueDuplicateValidation(ClueDAO clueDAO) {
        this.clueDAO = clueDAO;
    }

    @Override
    public void validate(Clue clue) throws DuplicateException, SQLException {
        if (clueDAO.existsByName(clue.getName())) {
            throw new DuplicateException(CLUE_DUPLICATED);
        }
    }
}
