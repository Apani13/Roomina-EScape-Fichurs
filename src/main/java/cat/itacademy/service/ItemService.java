package cat.itacademy.service;

import java.util.logging.Logger;

import cat.itacademy.repository.DAO.ItemDAO;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import cat.itacademy.model.Item;
import cat.itacademy.message.error.ItemErrorMessages;
import cat.itacademy.message.success.ItemSuccessMessages;

public class ItemService {

    private ItemDAO itemDAO;

    public ItemService() {
        this.itemDAO = new ItemDAO();
    }

    public void addItem(Item item) throws InvalidAttributeException, DuplicateException, NullObjectException {

        try {
            if (item == null) {
                throw new NullObjectException(ItemErrorMessages.ITEM_NULL_OBJECT);
            }

            if (item.getName() == null || item.getName().isEmpty()) {
                throw new InvalidAttributeException(ItemErrorMessages.ITEM_NAME_NULL_EMPTY);
            }
            if (item.getMaterial() == null || item.getMaterial().isEmpty()) {
                throw new InvalidAttributeException(ItemErrorMessages.ITEM_MATERIAL_NULL_EMPTY);
            }
            if (item.getStock() <= 0) {
                throw new InvalidAttributeException(ItemErrorMessages.ITEM_QUANTITY_INVALID);
            }

            if (itemDAO.existsByName(item.getName())) {
                throw new DuplicateException(ItemErrorMessages.ITEM_DUPLICATED);
            }

            itemDAO.insert(item);
         
            System.out.println(ItemSuccessMessages.ITEM_SUCCESS);

        } catch (DuplicateException | InvalidAttributeException e) {
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }
    }
}