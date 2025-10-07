package cat.itacademy.service;

import cat.itacademy.dto.availableInventory.AvailableEscapeRoomDTO;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.message.success.RoomSuccessMessages;
import cat.itacademy.repository.daoImpl.EscapeRoomDaoImpl;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.message.error.EscapeRoomErrorMessages;
import cat.itacademy.message.success.EscapeRoomSuccessMessages;
import cat.itacademy.validation.escapeRoom.EscapeRoomBasicValidation;
import cat.itacademy.validation.escapeRoom.EscapeRoomDuplicateValidation;
import cat.itacademy.validation.escapeRoom.EscapeRoomValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static cat.itacademy.message.error.InventoryErrorMessages.INVENTORY_EMPTY;

public class EscapeRoomService {
    private EscapeRoomDaoImpl escapeRoomDAO;
    private EscapeRoomValidator escapeRoomValidator;

    public EscapeRoomService() {
        this.escapeRoomDAO = new EscapeRoomDaoImpl();
        this.escapeRoomValidator = new EscapeRoomValidator(List.of(
                new EscapeRoomBasicValidation(),
                new EscapeRoomDuplicateValidation(escapeRoomDAO)
        ));
    }

    public void addEscapeRoom(EscapeRoom escapeRoom) throws InvalidAttributeException, DuplicateException, SQLException {
        escapeRoomValidator.validate(escapeRoom);
            EscapeRoom escapeRoomDB = getLastEscapeRoom();
            String escapeRoomName = escapeRoomDB.getName();
            System.out.println(String.format(EscapeRoomSuccessMessages.ESCAPEROOM_CREATED, escapeRoomName));
    }

    public EscapeRoom getLastEscapeRoom() throws SQLException {
        Optional<EscapeRoom> escapeRoom = escapeRoomDAO.getLast();
        if (escapeRoom.isPresent()) {
            return escapeRoom.get();
        }else {
            return null;
        }
    }

    public List<AvailableEscapeRoomDTO> getAllEscapeRooms() throws SQLException{
        if(escapeRoomDAO.findAll().isEmpty()) {
            throw new EmptyListException(EscapeRoomErrorMessages.ESCAPEROOM_LIST_EMPTY);
        }
        return escapeRoomDAO.findAll();
    }


    public  List<EscapeRoom> getRoomsByEscapeRoom(int escapeRoomID) throws SQLException {
        if(escapeRoomDAO.getRoomsByEscapeRoomId(escapeRoomID).isEmpty()) {
            throw  new EmptyListException(INVENTORY_EMPTY);
        }
        return escapeRoomDAO.getRoomsByEscapeRoomId(escapeRoomID);
    }

    public void addRoomToEscapeRoom(int escapeRoomId, int roomId) throws SQLException {
        escapeRoomDAO.updateEscapeRoomIdRoom(escapeRoomId, roomId);
    }

    public void removeRoomFromEscapeRoom(int escapeRoomId, int roomId) throws SQLException {
        try {
            escapeRoomDAO.removeRoomFromEscapeRoom(escapeRoomId, roomId);
            System.out.println(RoomSuccessMessages.ROOM_REMOVED_FROM_ESCAPEROOM);

        } catch (SQLException e) {
            Logger logger = Logger.getLogger(RoomService.class.getName());
            logger.severe("Error al retirar sala del Escape Room: " + e.getMessage());
            throw e;
        }
    }
}
