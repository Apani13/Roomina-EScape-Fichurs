
package cat.itacademy.service;

import cat.itacademy.dto.availableInventory.AvailableRoomDTO;
import cat.itacademy.dto.completeInventory.EntityRoomDTO;
import cat.itacademy.message.ui.RoomUIMessages;
import cat.itacademy.repository.DAO.ClueDAO;
import cat.itacademy.message.ui.RoomUIMessages;
import cat.itacademy.model.Clue;
import cat.itacademy.model.Item;
import cat.itacademy.repository.DAO.RoomDAO;
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
    private RoomDAO roomDAO;
    private ClueDAO clueDao;
    private RoomValidator roomValidator;

    public RoomService() {
        this.roomDAO =  new RoomDAO();
        this.clueDao = new ClueDAO();
        this.roomValidator = new RoomValidator(List.of(
                new RoomBasicValidation(),
                new RoomDuplicateValidation(roomDAO)
        ));
    }

    public void addRoom(Room room) throws InvalidAttributeException, DuplicateException, NullObjectException {
        try{
            roomValidator.validate(room);

            roomDAO.insert(room);

            System.out.println(RoomSuccessMessages.ROOM_CREATED);

        } catch (DuplicateException | InvalidAttributeException e) {
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }
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
    }

    public Room getLastRoom() throws SQLException {
        Optional<Room> room = roomDAO.getLastRoom();
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

    public void removeClueFromRoom(int clueId) throws SQLException {
        roomDAO.removeClueFromRoom(clueId);
    }

    public void removeItemFromRoom(int roomId) throws SQLException {
        roomDAO.removeItemFromRoom(roomId);
    }
}
