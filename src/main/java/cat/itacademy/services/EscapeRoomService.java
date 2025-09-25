package cat.itacademy.services;

import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.EscapeRoom;
import cat.itacademy.utils.EscapeRoomErrorMessages;
import cat.itacademy.utils.EscapeRoomSuccessMessages;

import java.util.ArrayList;

public class EscapeRoomService {
    private ArrayList<EscapeRoom> escapeRooms;

    public EscapeRoomService() {
        this.escapeRooms = new  ArrayList<>();
    }

    public void addEscapeRoom(EscapeRoom escapeRoom) throws InvalidAttributeException, DuplicateException {
        if(escapeRoom.getName().isEmpty() ||escapeRoom.getName().equals("null")){
            throw new InvalidAttributeException(EscapeRoomErrorMessages.ESCAPEROOM_NAME_NULL_EMPTY);
        }
        if(escapeRooms.contains(escapeRoom)){
            throw new DuplicateException(EscapeRoomErrorMessages.ESCAPEROOM_DUPLICATED);
        }

        escapeRooms.add(escapeRoom);
        System.out.println(String.format(EscapeRoomSuccessMessages.ESCAPEROOM_CREATED, escapeRoom.getName()));
    }
}
