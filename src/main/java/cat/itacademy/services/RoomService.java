
package cat.itacademy.services;

import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.Room;
import cat.itacademy.utils.RoomErrorMessages;
import cat.itacademy.utils.RoomSuccessMessages;

import java.util.ArrayList;

public class RoomService {

    ArrayList<Room> rooms;

    public RoomService() {
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) throws InvalidAttributeException, DuplicateException {
        if (rooms.contains(room))  {
            throw new DuplicateException(RoomErrorMessages.ROOM_DUPLICATED);
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

        rooms.add(room);
        System.out.println(RoomSuccessMessages.ROOM_CREATED);
    }
}
