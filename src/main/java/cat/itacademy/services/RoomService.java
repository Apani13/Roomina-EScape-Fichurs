
package cat.itacademy.services;

import cat.itacademy.exceptions.room.DuplicateRoomException;
import cat.itacademy.exceptions.room.InvalidRoomAtributeException;
import cat.itacademy.models.Room;
import cat.itacademy.utils.ErrorMessages;

import java.util.ArrayList;

public class RoomService {

    ArrayList<Room> rooms;

    public RoomService() {
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) throws InvalidRoomAtributeException, DuplicateRoomException {
        if (rooms.contains(room))  {
            throw new DuplicateRoomException(ErrorMessages.ROOM_DUPLICATED);
        }

        if (room.getName() == null || room.getName().isEmpty()) {
            throw new InvalidRoomAtributeException(ErrorMessages.ROOM_NAME_NULL_EMPTY);
        }

        if (room.getTheme() == null || room.getTheme().isEmpty()) {
            throw new InvalidRoomAtributeException(ErrorMessages.ROOM_THEME_NULL_EMPTY);
        }

        if (room.getLevel() == 0 || room.getLevel() < 0 ) {
            throw new InvalidRoomAtributeException(ErrorMessages.ROOM_LEVEL_INVALID);
        }

        rooms.add(room);
        System.out.println("The room " + room.getName() + " was created succesfully");
    }
}
