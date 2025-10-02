package cat.itacademy.validation.inventory;

import cat.itacademy.dto.InventoryDTO;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;
import java.util.List;

public class InventoryValidator {
    private List<ValidationStrategy<InventoryDTO>> strategies;

    public InventoryValidator(List<ValidationStrategy<InventoryDTO>> strategies) {
        this.strategies = strategies;
    }

    public void validate(InventoryDTO inventory) throws DuplicateException, SQLException {
        for (ValidationStrategy<InventoryDTO> strategy : strategies) {
            strategy.validate(inventory);
        }
    }
}
