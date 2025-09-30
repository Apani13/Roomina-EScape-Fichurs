package cat.itacademy.services;

import cat.itacademy.controllers.ClueDAO;
import cat.itacademy.exceptions.*;

import cat.itacademy.models.Clue;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import static cat.itacademy.utils.ClueErrorMessages.*;
import static cat.itacademy.utils.ClueUIMessages.*;
import static cat.itacademy.utils.ClueSuccessMessages.*;
import static cat.itacademy.utils.DBErrorMessages.ERROR_DB_UNEXPECTED_PROBLEM;

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
            logger.severe(String.format(ERROR_DB_UNEXPECTED_PROBLEM, e.getMessage()));
        }
    }

    public List<Clue> getClues() throws SQLException {
        if(clueDAO.getAllNames().isEmpty()){
            throw new EmptyListException(CLUE_LIST_EMPTY);
        }
        return clueDAO.getAllNames();
    }
    public void showClue() throws SQLException {
        if(clueDAO.getAllNames().isEmpty()){
            throw new EmptyListException(CLUE_LIST_EMPTY);
        }
        System.out.println(CLUEUI_LIST_HEADER);
        for(Clue clue: clueDAO.getAllNames()){
            System.out.println(String.format(CLUEUI_LIST_BODY, clue.getId(), clue.getName()));
        }
        System.out.println(CLUEUI_LIST_FOOTER);
    }
    public Clue getLastClue() throws SQLException {
        return clueDAO.getLastClue();
    }
    public Clue getClueById(int id) throws SQLException {
        return clueDAO.getById(id);
    }
}
