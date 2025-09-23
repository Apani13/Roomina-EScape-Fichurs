package cat.itacademy.services;

import cat.itacademy.exceptions.DuplicateClueException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.Clue;

import java.util.ArrayList;
import java.util.List;

public class ClueManagement {

    private List<Clue> clues;

    public ClueManagement() {
        this.clues = new ArrayList<>();
    }

    public void addClue(Clue clue) throws DuplicateClueException, InvalidAttributeException {
        if (clue.getPrice() == Double.NaN) {
            throw new InvalidAttributeException("Price has to be a number");
        }

        if (clue.getPrice() <= 0) {
            throw new InvalidAttributeException("Price has to be greater than 0");
        }

        if (clue.getName().isEmpty() || clue.getName().equals(null)) {
            throw new InvalidAttributeException("The Name of the clue is null or empty");
        }

        if (clues.contains(clue)) {
            throw new DuplicateClueException("Ya existe una pista con este nombre...");
        }

       clues.add(clue);

        System.out.println("La pista '" + clue.getName() + "' se ha creado correctamente!");
    }

}
