package cat.itacademy.services;

import cat.itacademy.controllers.ClueDAO;
import cat.itacademy.controllers.EscapeRoomDAO;
import cat.itacademy.exceptions.*;

import cat.itacademy.models.Clue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static cat.itacademy.utils.ClueErrorMessages.*;
import static cat.itacademy.utils.ClueSuccessMessages.*;

public class ClueService {

    private ClueDAO clueDAO;

    public ClueService() {
        this.clueDAO = new ClueDAO();
    }

    public void addClue(Clue clue) throws DuplicateException, InvalidAttributeException {

        try {

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

            if (clueDAO.existsByName(clue.getName())) {
                throw new DuplicateException(CLUE_DUPLICATED);
            }

            clueDAO.insert(clue);

            System.out.println(String.format(CLUE_CREATED, clue.getName()));
        } catch (DuplicateException | InvalidAttributeException e) {
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }


    }
}
