package cat.itacademy;

import cat.itacademy.models.Clue;
import cat.itacademy.models.Room;
import cat.itacademy.services.ClueService;
import cat.itacademy.services.RoomService;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        RoomService roomService = new RoomService();
        ClueService clueService = new ClueService();
        Room room = new Room("room1", "Terror", 1);
        Room room1 = new Room("room2", "Diversion", 2);

        Clue clue = new Clue("pista2", "terror","iuwdiqwuduqwuqw", 10.3);
        Clue clue2 = new Clue("pista4", "terror","iuwdiqwuduqwuqw", 10.3);

        //roomService.addRoom(room);
        //roomService.addRoom(room1);
        //clueService.addClue(clue);
        //clueService.addClue(clue2);

        roomService.showRooms();
        clueService.showClue();

        //roomService.addHintToEscapeRoom(1, 2);
        //roomService.addHintToEscapeRoom(2, 1);

    }
}