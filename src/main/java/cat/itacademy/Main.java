package cat.itacademy;

import cat.itacademy.model.Clue;
import cat.itacademy.model.Room;
import cat.itacademy.service.ClueService;
import cat.itacademy.service.RoomService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        RoomService roomService = new RoomService();
        ClueService clueService = new ClueService();
        Room room = new Room("room1", "Terror", 1);
        Room room1 = new Room("room2", "Diversion", 2);

        Clue clue = new Clue("pista2", "terror","iuwdiqwuduqwuqw");
        Clue clue2 = new Clue("pista4", "terror","iuwdiqwuduqwuqw");

        //roomService.addRoom(room);
        roomService.addRoom(room1);
        clueService.addClue(clue);
        clueService.addClue(clue2);

        roomService.showRooms();
        System.out.println("seleccione una sala: ");
        int roomId = sc.nextInt();
        clueService.showClue();
        System.out.println("seleccione una pista: ");
        int clueId = sc.nextInt();
        //roomService.addHintToEscapeRoom(1, 2);
        //roomService.addHintToEscapeRoom(2, 1);
        roomService.addClueToRoom(roomId, clueId);
    }
}