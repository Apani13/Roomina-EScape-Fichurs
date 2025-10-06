package cat.itacademy.validation.inventory;

import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;
import java.util.List;

public class InventoryValidator {
    private List<ValidationStrategy<AvailableInventoryDTO>> strategies;

    public InventoryValidator(List<ValidationStrategy<AvailableInventoryDTO>> strategies) {
        this.strategies = strategies;
    }

    public void validate(AvailableInventoryDTO inventory) throws DuplicateException, SQLException {
        for (ValidationStrategy<AvailableInventoryDTO> strategy : strategies) {
            strategy.validateAvailableInventory(inventory);
        }
    }
}
