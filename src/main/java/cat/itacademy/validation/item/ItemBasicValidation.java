package cat.itacademy.validation.item;

import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import static cat.itacademy.message.error.ItemErrorMessages.*;
import cat.itacademy.model.Item;
import cat.itacademy.validation.ValidationStrategy;

public class ItemBasicValidation implements ValidationStrategy<Item> {
    @Override
    public void validate(Item item) throws InvalidAttributeException, NullObjectException{
        if (item == null) {
            throw new NullObjectException(ITEM_NULL_OBJECT);
        }
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new InvalidAttributeException(ITEM_NAME_NULL_EMPTY);
        }
        if (item.getMaterial() == null || item.getMaterial().isEmpty()) {
            throw new InvalidAttributeException(ITEM_MATERIAL_NULL_EMPTY);
        }
        if (item.getStock() <= 0) {
            throw new InvalidAttributeException(ITEM_QUANTITY_INVALID);
        }
    }
}
