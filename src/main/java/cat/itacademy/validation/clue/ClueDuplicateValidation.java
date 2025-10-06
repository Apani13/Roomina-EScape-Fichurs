package cat.itacademy.validation.clue;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.model.Clue;
import cat.itacademy.repository.daoImpl.ClueDaoImpl;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;

import static cat.itacademy.message.error.ClueErrorMessages.CLUE_DUPLICATED;

public class ClueDuplicateValidation implements ValidationStrategy<Clue> {
    private ClueDaoImpl clueDAO;

    public ClueDuplicateValidation(ClueDaoImpl clueDAO) {
        this.clueDAO = clueDAO;
    }

    @Override
    public void validateAvailableInventory(Clue clue) throws DuplicateException, SQLException {
        if (clueDAO.existsByName(clue.getName())) {
            throw new DuplicateException(CLUE_DUPLICATED);
        }
    }
}
