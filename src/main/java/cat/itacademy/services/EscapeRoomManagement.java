package cat.itacademy.services;

import cat.itacademy.exceptions.DuplicateEscapeRoomException;
import cat.itacademy.exceptions.InvalidNameException;
import cat.itacademy.models.EscapeRoom;

import java.util.ArrayList;

public class EscapeRoomManagement {
    private ArrayList<EscapeRoom> escapeRooms;

    public EscapeRoomManagement() {
        this.escapeRooms = new  ArrayList<>();
    }

    public void addEscapeRoom(EscapeRoom escapeRoom) throws InvalidNameException, DuplicateEscapeRoomException {
        if(escapeRoom.getName().isEmpty() ||escapeRoom.getName().equals("null")){
            throw new InvalidNameException("The name of the EscapeRoom is null or empty");
        }
        if(escapeRooms.contains(escapeRoom)){
            throw new DuplicateEscapeRoomException("The EscapeRoom already exists");
        }

        escapeRooms.add(escapeRoom);
        System.out.println("El escape room "+escapeRoom.getName()+" se registro correctamente");
    }
}
