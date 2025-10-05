package cat.itacademy.validation.room;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.message.error.RoomErrorMessages;
import cat.itacademy.model.Room;
import cat.itacademy.repository.DAO.RoomDAO;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;

public class RoomDuplicateValidation implements ValidationStrategy<Room> {
    private RoomDAO roomDAO;

    public RoomDuplicateValidation(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public void validate(Room room) throws DuplicateException, SQLException {
        if (roomDAO.existsByName(room.getName()))  {
            throw new DuplicateException(RoomErrorMessages.ROOM_DUPLICATED);
        }
    }
}
