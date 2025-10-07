
package cat.itacademy.service;

import cat.itacademy.dto.availableInventory.AvailableRoomDTO;
import cat.itacademy.dto.completeInventory.EntityRoomDTO;
import cat.itacademy.message.success.ClueSuccessMessages;
import cat.itacademy.message.success.ItemAddedSuccessMessages;
import cat.itacademy.repository.daoImpl.ClueDaoImpl;
import cat.itacademy.model.Clue;
import cat.itacademy.model.Item;
import cat.itacademy.repository.daoImpl.ItemDaoImpl;
import cat.itacademy.repository.daoImpl.RoomDaoImpl;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import cat.itacademy.model.Room;
import cat.itacademy.message.error.RoomErrorMessages;
import cat.itacademy.message.success.RoomSuccessMessages;
import cat.itacademy.validation.room.RoomBasicValidation;
import cat.itacademy.validation.room.RoomDuplicateValidation;
import cat.itacademy.validation.room.RoomValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.Optional;

import static cat.itacademy.message.error.InventoryErrorMessages.INVENTORY_EMPTY;

public class RoomService {
    private RoomDaoImpl roomDAO;
    private ClueDaoImpl clueDao;
    private RoomValidator roomValidator;
    private ItemDaoImpl itemDao;

    public RoomService() {
        this.roomDAO =  new RoomDaoImpl();
        this.clueDao = new ClueDaoImpl();
        this.itemDao = new ItemDaoImpl();
        this.roomValidator = new RoomValidator(List.of(
                new RoomBasicValidation(),
                new RoomDuplicateValidation(roomDAO)
        ));
    }

    public void addRoom(Room room) throws InvalidAttributeException, DuplicateException, NullObjectException, SQLException {
            roomValidator.validate(room);
            roomDAO.insert(room);
            System.out.println(RoomSuccessMessages.ROOM_CREATED);
    }

    public List<EntityRoomDTO> getAllRooms() throws SQLException {
        if(roomDAO.getAllRoomsNameAndPrice().isEmpty()){
            throw new EmptyListException(RoomErrorMessages.ROOM_LIST_EMPTY);
        }
        return roomDAO.getAllRoomsNameAndPrice();
    }

    public List<AvailableRoomDTO> getAvailableRooms() throws SQLException {
        if(roomDAO.getAvailableRooms().isEmpty()) {
            throw new  EmptyListException(RoomErrorMessages.ROOM_LIST_EMPTY);
        }
        return roomDAO.getAvailableRooms();
    }

    public void addClueToRoom(int roomId, int clueId) throws SQLException {
        clueDao.updateRoomIdClue(roomId, clueId);
        System.out.println(ClueSuccessMessages.CLUE_ADDED);
    }

    private int getAvailableStock(int itemId) throws SQLException {
        Optional<Item> item = itemDao.getById(itemId);
        return item.isPresent() ? item.get().getStock() : 0;
    }

    private void updateStockAfterAssignment(int itemId, int assignedQuantity) throws SQLException {
        Optional<Item> item = itemDao.getById(itemId);
        if (item.isPresent()) {
            int currentStock = item.get().getStock();
            int newStock = currentStock - assignedQuantity;
            itemDao.updateStock(itemId, newStock);
        }
    }

    private boolean hasSufficientStock(int itemId, int requestedQuantity) throws SQLException {
        int availableStock = getAvailableStock(itemId);
        return requestedQuantity <= availableStock;
    }

    public void addItemToRoom(int roomId, int itemId, int quantity) throws SQLException, InvalidAttributeException {
        itemDao.assignItemToRoom(roomId, itemId, quantity);
        updateStockAfterAssignment(itemId, quantity);
        System.out.println(ItemAddedSuccessMessages.ITEM_ADDED_SUCCESS);
    }

    public Room getLastRoom() throws SQLException {
        Optional<Room> room = roomDAO.getLast();
        return room.orElse(null);
    }

    public Room getRoomById(int id) throws SQLException {
        Optional<Room> room = roomDAO.getById(id);

        return room.orElse(null);
    }

    public List<Room> getRoomsWithClues() throws SQLException {
        if(roomDAO.getRoomsWithClues().isEmpty()){
            throw new EmptyListException(RoomErrorMessages.ROOM_LIST_EMPTY);
        }
        return roomDAO.getRoomsWithClues();
    }

    public List<Clue> getCluesByRoomId(int roomId) throws SQLException {
        if(roomDAO.getCluesByRoomId(roomId).isEmpty()){
            throw new EmptyListException(INVENTORY_EMPTY);
        }
        return  roomDAO.getCluesByRoomId(roomId);
    }

    public List<Item> getItemByRoomId(int roomId) throws SQLException {
        if(roomDAO.getItemsByRoomId(roomId).isEmpty()) {
            throw new EmptyListException(INVENTORY_EMPTY);
        }
        return roomDAO.getItemsByRoomId(roomId);
    }

    public void removeClueFromRoom(int roomId, int clueId) throws SQLException {
        roomDAO.removeClueFromRoom(roomId, clueId);
    }

    public void removeItemFromRoom(int id , int roomId) throws SQLException {
        roomDAO.removeItemFromRoom(id, roomId);
    }
}
