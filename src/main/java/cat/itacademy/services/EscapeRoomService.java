package cat.itacademy.services;

import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.controllers.EscapeRoomDAO;
import cat.itacademy.repositories.DatabaseConnection;
import cat.itacademy.models.EscapeRoom;
import cat.itacademy.utils.EscapeRoomErrorMessages;
import cat.itacademy.utils.EscapeRoomSuccessMessages;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Logger;

public class EscapeRoomService {
    private EscapeRoomDAO escapeRoomDAO;

    public EscapeRoomService() {
        this.escapeRoomDAO = new EscapeRoomDAO();
    }

    public void addEscapeRoom(EscapeRoom escapeRoom) throws InvalidAttributeException, DuplicateException {
        try {
            if (escapeRoom.getName() == null || escapeRoom.getName().isEmpty()) {
                throw new InvalidAttributeException(EscapeRoomErrorMessages.ESCAPEROOM_NAME_NULL_EMPTY);
            }
            if(escapeRoomDAO.existsByName(escapeRoom.getName())){
                throw new DuplicateException(EscapeRoomErrorMessages.ESCAPEROOM_DUPLICATED);
            }

            escapeRoomDAO.insert(escapeRoom);

            System.out.println(String.format(EscapeRoomSuccessMessages.ESCAPEROOM_CREATED, escapeRoom.getName()));
        } catch (DuplicateException | InvalidAttributeException e) {
            // Re-lanzar las excepciones personalizadas para que los tests las capturen
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EscapeRoomService.class.getName());
            logger.severe("Error inesperado: " + e.getMessage());
        }
    }

    public EscapeRoom getLastEscapeRoom() throws SQLException {
        return escapeRoomDAO.getLastEscapeRoom();
    }
}
