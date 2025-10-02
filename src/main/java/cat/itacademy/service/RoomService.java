
package cat.itacademy.service;

import cat.itacademy.dto.completeInventory.AllRoomsDTO;
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

public class RoomService {
    private RoomDAO roomDAO;
    private RoomValidator roomValidator;

    public RoomService() {
        this.roomDAO =  new RoomDAO();
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

    public List<AllRoomsDTO> getAllRooms() throws SQLException {
        if(roomDAO.getAllRoomsNameAndPrice().isEmpty()){
            throw new EmptyListException(RoomErrorMessages.ROOM_LIST_EMPTY);
        }

        return roomDAO.getAllRoomsNameAndPrice();
    }

    /*public List<AvailableRoomDTO> getAvailableRooms() throws SQLException {
        if(roomDAO.getAllNames().isEmpty()) {
            throw new  EmptyListException(RoomErrorMessages.ROOM_LIST_EMPTY);
        }

        return roomDAO.getAvailableRooms();
    }*/

    /*public void showRooms() throws SQLException {
        System.out.println(RoomUIMessages.ROOMUI_LIST_HEADER);
        for(Room room: getAllRooms()){
            System.out.println(String.format(RoomUIMessages.ROOMUI_LIST_BODY, room.getId(), room.getName()));
        }
        System.out.println(RoomUIMessages.ROOMUI_LIST_FOOTER);
    }*/

    public void addClueToRoom(int roomId, int clueId) throws SQLException {
        roomDAO.updateRoomIdClue(roomId, clueId);
    }

    public Room getLastRoom() throws SQLException {
        Optional<Room> room = roomDAO.getLastRoom();
        if(room.isPresent()){
            return room.get();
        } else {
            return null;
        }
    }

    public Room getRoomById(int id) throws SQLException {
        Optional<Room> room = roomDAO.getById(id);

        if(room.isPresent()){
            return room.get();
        } else {
            return null;
        }
    }

    public List<Room> getRoomsWithClues() throws SQLException {
        if(RoomDAO.getRoomsWithClues().isEmpty()){
            throw new EmptyListException(RoomErrorMessages.ROOM_LIST_EMPTY);
        }

        return RoomDAO.getRoomsWithClues();
    }

    public void removeClueFromRoom(int clueId) throws SQLException {
        roomDAO.removeClueFromRoom(clueId);
    }
}
