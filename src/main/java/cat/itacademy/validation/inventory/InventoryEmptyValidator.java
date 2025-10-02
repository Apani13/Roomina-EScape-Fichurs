package cat.itacademy.validation.inventory;

import cat.itacademy.dto.InventoryDTO;
import cat.itacademy.exception.*;
import cat.itacademy.validation.ValidationStrategy;

import static cat.itacademy.message.error.InventoryErrorMessages.INVENTORY_EMPTY;

public class InventoryEmptyValidator implements ValidationStrategy<InventoryDTO> {

    @Override
    public void validate(InventoryDTO inventory) throws EmptyListException {
        if(inventory.inventoryIsEmpty()) {
            throw new EmptyListException(INVENTORY_EMPTY);
        }

    }





}
