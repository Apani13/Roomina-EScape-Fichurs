package cat.itacademy.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import cat.itacademy.model.Room;
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


    public Item getItemById(int id) throws SQLException {

       Optional<Item> item = itemDAO.getById(id);

       if (item.isPresent()) {

           return item.get();
       } else {
           return null;
       }
    }

    public Item getLastItem() throws SQLException {
        Optional<Item> item = itemDAO.getLastItem();
        if(item.isPresent()){
            return item.get();
        } else {
            return null;
        }
    }

}