package cat.itacademy.service;

import cat.itacademy.dto.availableInventory.AvailableClueDTO;
import cat.itacademy.repository.daoImpl.ClueDaoImpl;
import cat.itacademy.exception.*;

import cat.itacademy.model.Clue;
import cat.itacademy.validation.clue.ClueBasicValidation;
import cat.itacademy.validation.clue.ClueDuplicateValidation;
import cat.itacademy.validation.clue.ClueValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static cat.itacademy.message.error.ClueErrorMessages.*;
import static cat.itacademy.message.success.ClueSuccessMessages.*;

public class ClueService {

    private ClueDaoImpl clueDAO;
    private ClueValidator clueValidator;

    public ClueService() {
        this.clueDAO = new ClueDaoImpl();
        this.clueValidator = new ClueValidator(List.of(
                new ClueBasicValidation(),
                new ClueDuplicateValidation(clueDAO)
            )
        );
    }

    public void addClue(Clue clue) throws DuplicateException, InvalidAttributeException, SQLException {
        clueValidator.validate(clue);
        clueDAO.insert(clue);
        System.out.println(String.format(CLUE_CREATED, clue.getName()));
    }

    public List<Clue> getClues() throws SQLException {
        if(clueDAO.getAllNames().isEmpty()){
            throw new EmptyListException(CLUE_LIST_EMPTY);
        }
        return clueDAO.getAllNames();
    }

    public Clue getLastClue() throws SQLException {
        Optional<Clue> clue = clueDAO.getLast();
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
