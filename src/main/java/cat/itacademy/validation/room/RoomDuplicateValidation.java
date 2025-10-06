package cat.itacademy.validation.room;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.message.error.RoomErrorMessages;
import cat.itacademy.model.Room;
import cat.itacademy.repository.daoImpl.RoomDaoImpl;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;

public class RoomDuplicateValidation implements ValidationStrategy<Room> {
    private RoomDaoImpl roomDAO;

    public RoomDuplicateValidation(RoomDaoImpl roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public void validateAvailableInventory(Room room) throws DuplicateException, SQLException {
        if (roomDAO.existsByName(room.getName()))  {
            throw new DuplicateException(RoomErrorMessages.ROOM_DUPLICATED);
        }
    }
}
