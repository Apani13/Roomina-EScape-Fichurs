package cat.itacademy.services;

import cat.itacademy.exceptions.*;

import cat.itacademy.models.Clue;

import java.util.ArrayList;
import java.util.List;

import static cat.itacademy.utils.ClueErrorMessages.*;
import static cat.itacademy.utils.ClueSuccessMessages.*;

public class ClueService {

    private List<Clue> clues;

    public ClueService() {
        this.clues = new ArrayList<>();
    }

    public void addClue(Clue clue) throws DuplicateException, InvalidAttributeException {

        if (clue == null) {
            throw new NullObjectException(CLUE_NULL_OBJECT);
        }

        double price = clue.getPrice();
        if (Double.isNaN(price) || price <= 0) {
            throw new InvalidAttributeException(CLUE_PRICE_INVALID);
        }

        String name = clue.getName();
        if (name == null || name.isBlank()) {
            throw new InvalidAttributeException(CLUE_NAME_NULL_EMPTY);
        }

        String description = clue.getDescription();
        if (description == null || description.isBlank()) {
            throw new InvalidAttributeException(CLUE_DESC_NULL_EMPTY);
        }

        if (clues.contains(clue)) {
            throw new DuplicateException(CLUE_DUPLICATED);
        }

        clues.add(clue);
        System.out.println(CLUE_CREATED);
    }
}
