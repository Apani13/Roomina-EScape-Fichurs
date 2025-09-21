package cat.itacademy.services;

import cat.itacademy.exceptions.DuplicateClueException;
import cat.itacademy.models.Clue;

import java.util.ArrayList;
import java.util.List;

public class ClueManagement {

    private List<Clue> clues;

    public ClueManagement() {
        this.clues = new ArrayList<>();
    }

    public void addClue(Clue clue) throws DuplicateClueException {
        if (clues.contains(clue)) {
            throw new DuplicateClueException("The clue already exists");
        }

       clues.add(clue);

        System.out.println("The clue '" + clue.getName() + "' has been successfully added!");
    }
}
