
package cat.itacademy.services;

import cat.itacademy.controllers.RoomDAO;
import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.EmptyListException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.exceptions.NullObjectException;
import cat.itacademy.models.Room;
import cat.itacademy.utils.RoomErrorMessages;
import cat.itacademy.utils.RoomSuccessMessages;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


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
        return roomDAO.getLastRoom();
    }
}
