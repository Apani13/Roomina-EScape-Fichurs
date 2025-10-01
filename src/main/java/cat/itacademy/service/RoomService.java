
package cat.itacademy.service;

import cat.itacademy.repository.DAO.RoomDAO;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import cat.itacademy.model.Room;
import cat.itacademy.message.error.RoomErrorMessages;
import cat.itacademy.message.success.RoomSuccessMessages;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.Optional;


public class RoomService {
    private RoomDAO roomDAO;


    public RoomService() {
        this.roomDAO =  new RoomDAO();
    }

    public void addRoom(Room room) throws InvalidAttributeException, DuplicateException, NullObjectException {
        try{
            if (room == null) {
                throw new NullObjectException(RoomErrorMessages.ROOM_NULL_OBJECT);
            }

            if (room.getName() == null || room.getName().isEmpty()) {
            throw new InvalidAttributeException(RoomErrorMessages.ROOM_NAME_NULL_EMPTY);
            }

            if (room.getTheme() == null || room.getTheme().isEmpty()) {
            throw new InvalidAttributeException(RoomErrorMessages.ROOM_THEME_NULL_EMPTY);
            }

            if (room.getLevel() <= 0 ) {
            throw new InvalidAttributeException(RoomErrorMessages.ROOM_LEVEL_INVALID);
             }

            if (roomDAO.existsByName(room.getName()))  {
            throw new DuplicateException(RoomErrorMessages.ROOM_DUPLICATED);
             }

            roomDAO.insert(room);
            System.out.println(RoomSuccessMessages.ROOM_CREATED);

        } catch (DuplicateException | InvalidAttributeException e) {
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }
    }

    public List<Room> getAllRooms() throws SQLException {
        if(roomDAO.getAllNames().isEmpty()){
            throw new EmptyListException(RoomErrorMessages.ROOM_LIST_EMPTY);
        }
        return roomDAO.getAllNames();
    }

    public List<Room> getAvailableRooms() throws SQLException {
        if(roomDAO.getAllNames().isEmpty()) {
            throw new  EmptyListException(RoomErrorMessages.ROOM_LIST_EMPTY);
        }
        return roomDAO.getAvailableRooms();
    }

    public void showRooms() throws SQLException {
        System.out.println("---------Lista de salas----------");
        for(Room room: getAllRooms()){
            System.out.println("Cod: " + room.getId() + " Nombre: " + room.getName());
        }
    }

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
