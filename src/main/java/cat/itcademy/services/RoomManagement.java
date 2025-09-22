
package cat.itcademy.services;

import cat.itcademy.exceptions.room.DuplicateRoomException;
import cat.itcademy.exceptions.room.InvalidRoomAtributeException;
import cat.itcademy.models.Room;

import java.util.ArrayList;

public class RoomManagement {

    ArrayList<Room> rooms;

    public RoomManagement () {
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) throws InvalidRoomAtributeException, DuplicateRoomException {
        if (rooms.contains(room))  {
            throw new DuplicateRoomException("La sala ha ya existe.");
        }

        if (room.getName() == null || room.getName().isEmpty()) {
            throw new InvalidRoomAtributeException("El nombre de la sala está vacío o es nulo");
        }

        if (room.getTheme() == null || room.getTheme().isEmpty()) {
            throw new InvalidRoomAtributeException("El tema de la sala está vacío o es nulo");
        }

        if (room.getLevel() == 0 || room.getLevel() < 0 ) {
            throw new InvalidRoomAtributeException("La sala tiene un nivel invalido.");
        }
        rooms.add(room);
        System.out.println("The room " + room.getName() + " was created succesfully");
    }
}
