package assingment;

import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.Item;
import cat.itacademy.model.Room;
import cat.itacademy.model.RoomItem;
import cat.itacademy.repository.DAO.RoomItemDAO;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.service.ItemService;
import cat.itacademy.service.RoomItemService;
import cat.itacademy.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class RoomItemServiceTest {

    private RoomItemService roomItemService;
    private RoomService roomService;
    private ItemService itemService;
    private RoomItemDAO roomItemDAO;
    private int roomId;
    private int itemId;

    @BeforeEach
    void setUp() throws SQLException {

        roomItemService = new RoomItemService();
        roomService = new RoomService();
        itemService = new ItemService();
        roomItemDAO = new RoomItemDAO();

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.prepareStatement("DELETE FROM room_item").executeUpdate();
            conn.prepareStatement("DELETE FROM room").executeUpdate();
            conn.prepareStatement("DELETE FROM item").executeUpdate();
        }

    }

    private RoomItem newRoomItem(Integer roomId, Integer itemId, int qty) {
        return new RoomItem(roomId, itemId, qty);
    }

    private Room newRoom(String name, String theme, int level) {
        return new Room(name, theme, level);
    }

    private Item newItem(String name, String material, int stock) {
        return new Item(name, material, stock);
    }


    @Test
    void whenRelatingItemThatExistToRoom_thenRelationIsMade() throws SQLException {

        itemService.addItem(
               newItem("Candelabro",
                       "metal",
                       30)
        );

        roomService.addRoom(
                newRoom("Chuky",
                        "Terror",
                        2)
        );

        roomId = roomService.getLastRoom().getId();
        itemId = itemService.getLastItem().getId();

        roomItemService.addItemToRoom(
                newRoomItem(roomId,
                        itemId,
                        10)
        );

        assertEquals(10, roomItemDAO.getQuantity(roomId,itemId));
        assertEquals(20, itemService.getItemById(itemId).getStock());
    }


    @Test
    void whenAddingNonExistingItem_thenThrowsException() throws SQLException {

        roomService.addRoom(
                newRoom("Saw III",
                        "Felicidad",
                        5)
        );

        roomId = roomService.getLastRoom().getId();
        itemId = 5000;

        assertThrows(InvalidAttributeException.class,
                () -> roomItemService.addItemToRoom(
                        newRoomItem(roomId,
                                itemId,
                                6)
                        )
        );
    }


    @Test
    void whenAddingItemsToRoom_roomCanContainMultipleItems() throws SQLException {

        roomService.addRoom(
                newRoom("Fiesta En la playa",
                        "Misterio",
                        5)
        );

        itemService.addItem(
                newItem("Chancleta",
                        "Plastico",
                        42)
        );

        roomId = roomService.getLastRoom().getId();
        itemId = itemService.getLastItem().getId();

        roomItemService.addItemToRoom(
                newRoomItem(roomId,
                        itemId,
                        10)
        );

        itemService.addItem(
                newItem("Arbol",
                        "madera",
                        7)
        );

        itemId = itemService.getLastItem().getId();

        roomItemService.addItemToRoom(
                newRoomItem(roomId,
                        itemId,
                        3)
        );

        assertEquals(2, roomItemService.getAllItemsFromRoom(roomId).size());

    }


    @Test
    void whenRemovingItem_thenInventoryUpdates() throws SQLException {
        itemService.addItem(
                newItem("Candelabro",
                        "metal",
                        30)
        );

        roomService.addRoom(
                newRoom("Chuky",
                        "Terror",
                        2)
        );

        roomId = roomService.getLastRoom().getId();
        itemId = itemService.getLastItem().getId();

        roomItemService.addItemToRoom(
                newRoomItem(roomId,
                        itemId,
                        10)
        );

        roomItemService.removeItemFromRoom(roomId, itemId);

        assertEquals(30, itemService.getItemById(itemId).getStock());
    }

}
