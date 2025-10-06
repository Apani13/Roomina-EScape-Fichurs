package cat.itacademy.validation.item;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.message.error.ItemErrorMessages;
import cat.itacademy.model.Item;
import cat.itacademy.repository.daoImpl.ItemDaoImpl;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;

public class ItemDuplicateValidation implements ValidationStrategy<Item> {
    private ItemDaoImpl itemDAO;

    public ItemDuplicateValidation(ItemDaoImpl itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public void validateAvailableInventory(Item item) throws DuplicateException, SQLException {
        if (itemDAO.existsByName(item.getName())) {
            throw new DuplicateException(ItemErrorMessages.ITEM_DUPLICATED);
        }
    }
}
