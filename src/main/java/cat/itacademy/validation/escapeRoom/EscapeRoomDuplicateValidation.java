package cat.itacademy.validation.escapeRoom;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.message.error.EscapeRoomErrorMessages;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.repository.daoImpl.EscapeRoomDaoImpl;
import cat.itacademy.validation.ValidationStrategy;

import java.sql.SQLException;

public class EscapeRoomDuplicateValidation implements ValidationStrategy<EscapeRoom> {
    private EscapeRoomDaoImpl escapeRoomDAO;

    public EscapeRoomDuplicateValidation(EscapeRoomDaoImpl escapeRoomDAO){
        this.escapeRoomDAO = escapeRoomDAO;
    }

    @Override
    public void validateAvailableInventory(EscapeRoom escapeRoom) throws DuplicateException, SQLException {
        if(escapeRoomDAO.existsByName(escapeRoom.getName())){
            throw new DuplicateException(EscapeRoomErrorMessages.ESCAPEROOM_DUPLICATED);
        }
    }
}
