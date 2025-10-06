package cat.itacademy.service;

import cat.itacademy.exception.InsufficientStockException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.RoomItem;
import cat.itacademy.repository.DAO.ItemDAO;
import cat.itacademy.repository.DAO.RoomItemDAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import static cat.itacademy.message.success.ItemAddedSuccessMessages.ITEM_ADDED_SUCCESS;

public class RoomItemService {

    private final ItemService itemService;
    private final RoomService roomService;
    private final RoomItemDAO roomItemDAO;
    private final ItemDAO itemDAO;

    public RoomItemService() {
        this.itemService = new ItemService();
        this.roomService = new RoomService();
        this.roomItemDAO = new RoomItemDAO();
        this.itemDAO = new ItemDAO();
    }

    public void addItemToRoom(RoomItem roomItem) {

        Integer itemId = roomItem.getItemId();
        Integer roomId = roomItem.getRoomId();

        try {
            if (roomItem.getQuantity() <= 0) {
                throw new InvalidAttributeException("Quantity must be > 0");
            }

            if (roomId == null || roomId <= 0) {
                throw new InvalidAttributeException("introduzca una sala válida.");
            }

            if (itemId == null || itemId <= 0) {
                throw new InvalidAttributeException("introduzca una objeto de decoración válido.");
            }

            if (roomService.getRoomById(roomId) == null) {
                throw new InvalidAttributeException("La sala no existe...");
            }

            if (itemService.getItemById(itemId) == null) {
                throw new InvalidAttributeException("El objeto de decoración no existe...");
            }

            if (roomItem.getQuantity() > itemService.getItemById(itemId).getStock()) {

                throw new InsufficientStockException("No hay suficientes "
                                                    + itemService.getItemById(itemId).getName()
                                                    + " disponlibles, quedan "
                                                    + itemService.getItemById(itemId).getStock()
                                                    );
            }

            roomItemDAO.insertRoomItem(roomItem);
            reduceItemStock(itemId, roomItem.getQuantity());


            System.out.println(String.format(ITEM_ADDED_SUCCESS,
                    itemService.getItemById(itemId).getName()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeItemFromRoom(int roomId, int itemId) throws SQLException{
        int quantity = roomItemDAO.getQuantity(roomId, itemId);

        roomItemDAO.deleteRoomItem(roomId, itemId);

        increaseItemStock(itemId, quantity);

    }

    public void reduceItemStock(int itemId, int numItemsAssigned) throws SQLException {

        int updatedStock = (itemService.getItemById(itemId).getStock()) - numItemsAssigned;

        itemDAO.updateStock(itemId, updatedStock);

    }

    public void increaseItemStock(int itemId, int numItemsAssigned) throws SQLException {

        int updatedStock = (itemService.getItemById(itemId).getStock()) + numItemsAssigned;

        itemDAO.updateStock(itemId, updatedStock);

    }

    public List<RoomItem> getAllItemsFromRoom(int roomId) throws SQLException {
        return roomItemDAO.getAllByRoomId(roomId);
    }

    /*public RoomItem getRoomItemById(int roomId, int itemId) {

        roomItemDAO.

    }*/


}
