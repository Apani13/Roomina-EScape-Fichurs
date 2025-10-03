package cat.itacademy.validation.inventory;

import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.exception.*;
import cat.itacademy.validation.ValidationStrategy;

import static cat.itacademy.message.error.InventoryErrorMessages.INVENTORY_EMPTY;

public class InventoryEmptyValidator implements ValidationStrategy<AvailableInventoryDTO> {

    @Override
    public void validateAvailableInventory(AvailableInventoryDTO inventory) throws EmptyListException {
        if(inventory.inventoryIsEmpty()) {
            throw new EmptyListException(INVENTORY_EMPTY);
        }
    }

    public void validateCompleteInventory(CompleteInventoryDTO inventory) throws EmptyListException {
        if(inventory.inventoryIsEmpty()) {
            throw new EmptyListException(INVENTORY_EMPTY);
        }
    }
}
