package cat.itacademy.service;

import cat.itacademy.dto.availableInventory.AvailableClueDTO;
import cat.itacademy.message.error.RoomErrorMessages;
import cat.itacademy.repository.DAO.ClueDAO;
import cat.itacademy.exception.*;

import cat.itacademy.model.Clue;
import cat.itacademy.validation.clue.ClueBasicValidation;
import cat.itacademy.validation.clue.ClueDuplicateValidation;
import cat.itacademy.validation.clue.ClueValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static cat.itacademy.message.error.ClueErrorMessages.*;
import static cat.itacademy.message.ui.ClueUIMessages.*;
import static cat.itacademy.message.success.ClueSuccessMessages.*;
import static cat.itacademy.message.error.DBErrorMessages.ERROR_DB_UNEXPECTED_PROBLEM;

public class ClueService {

    private ClueDAO clueDAO;
    private ClueValidator clueValidator;

    public ClueService() {
        this.clueDAO = new ClueDAO();
        this.clueValidator = new ClueValidator(List.of(
                new ClueBasicValidation(),
                new ClueDuplicateValidation(clueDAO)
            )
        );
    }

    public void addClue(Clue clue) throws DuplicateException, InvalidAttributeException {

        try {
            clueValidator.validate(clue);

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
        Optional<Clue> clue = clueDAO.getLastClue();
        if(clue.isPresent()){
            return clue.get();
        }else {
            return null;
        }
    }

    public List<AvailableClueDTO> getAvailableCLues() throws SQLException {
        if (clueDAO.getAvailableClues().isEmpty()) {
            throw new EmptyListException(CLUE_LIST_EMPTY);
        }
        return clueDAO.getAvailableClues();
    }



    public Clue getClueById(int id) throws SQLException {
        Optional<Clue> clue = clueDAO.getById(id);

        if(clue.isPresent()){
            return clue.get();
        }else {
            return null;
        }
    }
}
