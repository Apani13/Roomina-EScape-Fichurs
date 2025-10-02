package cat.itacademy.validation.item;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.model.Item;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemValidator {
    private List<ValidationStrategy<Item>> strategies;

    public ItemValidator(List<ValidationStrategy<Item>> strategies) {
        this.strategies = strategies;
    }

    public void validate(Item item) throws DuplicateException, SQLException {
        for (ValidationStrategy<Item> strategy : strategies) {
            strategy.validate(item);
        }
    }
}
