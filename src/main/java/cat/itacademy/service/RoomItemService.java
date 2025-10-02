package cat.itacademy.service;

import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.repository.DAO.ItemDAO;
import cat.itacademy.repository.DAO.RoomDAO;
import cat.itacademy.repository.DAO.RoomItemDAO;

import java.sql.SQLException;

import static cat.itacademy.message.success.ItemAddedSuccessMessages.ITEM_ADDED_SUCCESS;

public class RoomItemService {

    private final RoomItemDAO roomItemDAO = new RoomItemDAO();
    private final RoomDAO roomDAO = new RoomDAO();
    private final ItemDAO itemDAO = new ItemDAO();

    public void addItemToRoom(int roomId, int itemId, int stock) {

        try {
            if (stock <= 0) {
                throw new InvalidAttributeException("Quantity must be > 0");
            }
            if (!roomDAO.existsById(roomId)) {
                throw new InvalidAttributeException("Room not found");
            }
            if (!itemDAO.existsById(itemId)) {
                throw new InvalidAttributeException("Item not found");
            }

            roomItemDAO.insertRoomItem(roomId, itemId, stock);
            System.out.println(String.format(ITEM_ADDED_SUCCESS, itemDAO.getById(itemId).getName()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeItemFromRoom(int roomId, int itemId) {

        try {

            roomItemDAO.deleteRoomItem(roomId, itemId);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public void updateItemStock(int roomId, int itemId) {

        try {

            removeItemFromRoom(roomId, itemId);

        } catch (SQLException e) {

            throw  new RuntimeException(e);

        }
    }


}
