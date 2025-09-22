package cat.itacademy.services;

import cat.itacademy.controllers.EscapeRoomDAO;
import cat.itacademy.repositories.DatabaseConnection;
import cat.itacademy.exceptions.DuplicateEscapeRoomException;
import cat.itacademy.exceptions.InvalidNameException;
import cat.itacademy.models.EscapeRoom;

import java.sql.SQLException;

public class EscapeRoomManagement {
    private EscapeRoomDAO escapeRoomDAO;

    public EscapeRoomManagement() {
        this.escapeRoomDAO = new EscapeRoomDAO();
    }

    public void addEscapeRoom(EscapeRoom escapeRoom) throws InvalidNameException, DuplicateEscapeRoomException {

        if (escapeRoom.getName() == null || escapeRoom.getName().isEmpty()) {
            throw new InvalidNameException("El nombre no puede ser vacio o nulo");
        }

        try {
            if(escapeRoomDAO.existsByName(escapeRoom.getName())){
                throw new DuplicateEscapeRoomException("El nombre del escape room ya existe");
            }
            escapeRoomDAO.insert(escapeRoom);

            System.out.println("El escape room " + escapeRoom.getName() + " se registro correctamente");
        } catch (DuplicateEscapeRoomException | InvalidNameException e) {
            // Re-lanzar las excepciones personalizadas para que los tests las capturen
            throw e;
        } catch (Exception e) {
            e.printStackTrace(); // Solo loggear errores inesperados
        }
    }

    public EscapeRoom getLastEscapeRoom() throws SQLException {
        return escapeRoomDAO.getLastEscapeRoom();
    }
}
