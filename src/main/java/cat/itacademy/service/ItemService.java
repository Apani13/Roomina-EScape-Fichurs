package cat.itacademy.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import cat.itacademy.dto.availableInventory.AvailableClueDTO;
import cat.itacademy.dto.availableInventory.AvailableItemDTO;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.repository.DAO.ItemDAO;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import cat.itacademy.model.Item;
import cat.itacademy.message.error.ItemErrorMessages;
import cat.itacademy.message.success.ItemSuccessMessages;
import cat.itacademy.validation.item.ItemBasicValidation;
import cat.itacademy.validation.item.ItemDuplicateValidation;
import cat.itacademy.validation.item.ItemValidator;

import static cat.itacademy.message.error.ItemErrorMessages.ITEM_LIST_EMPTY;

public class ItemService {

    private ItemDAO itemDAO;
    private ItemValidator itemValidator;

    public ItemService() {
        this.itemDAO = new ItemDAO();
        this.itemValidator = new ItemValidator(List.of(
                new ItemBasicValidation(),
                new ItemDuplicateValidation(itemDAO)
        ));
    }

    public void addItem(Item item) throws InvalidAttributeException, DuplicateException, NullObjectException {

        try {
            itemValidator.validate(item);

            itemDAO.insert(item);
         
            System.out.println(ItemSuccessMessages.ITEM_SUCCESS);

        } catch (DuplicateException | InvalidAttributeException e) {
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }
    }

    public List<AvailableItemDTO> getAvailableItems() throws SQLException {
    if (itemDAO.getAvailableItems().isEmpty()) {
        throw new EmptyListException(ITEM_LIST_EMPTY);
    }
    return itemDAO.getAvailableItems();
    }
}