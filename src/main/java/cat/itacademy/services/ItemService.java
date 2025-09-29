package cat.itacademy.services;

import java.util.logging.Logger;

import cat.itacademy.controllers.ItemDAO;
import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.Item;
import cat.itacademy.utils.ItemErrorMessages;
import cat.itacademy.utils.ItemSuccessMessages;

public class ItemService {

    private ItemDAO itemDAO;

    public ItemService() {
        this.itemDAO = new ItemDAO();
    }

    public void addItemt(Item item) throws InvalidAttributeException, DuplicateException, NullPointerException {

        try {

            if (item == null) {
                throw new NullPointerException(ItemErrorMessages.ITEM_NULL_OBJECT);
            }

            if (item.getName() == null || item.getName().isEmpty()) {
                throw new InvalidAttributeException(ItemErrorMessages.ITEM_NAME_NULL_EMPTY);
            }
            if (item.getMaterial() == null || item.getMaterial().isEmpty()) {
                throw new InvalidAttributeException(ItemErrorMessages.ITEM_MATERIAL_NULL_EMPTY);
            }
            if (item.getQuantity() <= 0) {
                throw new InvalidAttributeException(ItemErrorMessages.ITEM_QUANTITY_INVALID);
            }

            if (itemDAO.existsByName(item.getName())) {
                throw new DuplicateException(ItemErrorMessages.ITEM_DUPLICATED);
            }

            itemDAO.insert(item);
            System.out.println(ItemSuccessMessages.OBJECT_SUCCES);

        } catch (DuplicateException | InvalidAttributeException e) {
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }
    }
}