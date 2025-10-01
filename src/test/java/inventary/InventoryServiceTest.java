// InventoryServiceTest.java
package inventary;

import cat.itacademy.dto.AvailableClueDTO;
import cat.itacademy.dto.AvailableItemDTO;
import cat.itacademy.dto.AvailableRoomDTO;
import cat.itacademy.dto.InventoryDTO;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.service.InventoryService;
import cat.itacademy.service.RoomService;
import cat.itacademy.service.ClueService;
import cat.itacademy.service.ItemService;
import cat.itacademy.model.Room;
import cat.itacademy.model.Clue;
import cat.itacademy.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    private InventoryService inventoryService;
    private RoomService roomService;
    private ClueService clueService;
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        inventoryService = new InventoryService();
        roomService = new RoomService();
        clueService = new ClueService();
        itemService = new ItemService();
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.prepareStatement("DELETE FROM clue").executeUpdate();
            conn.prepareStatement("DELETE FROM item").executeUpdate();
            conn.prepareStatement("DELETE FROM room").executeUpdate();
            conn.prepareStatement("DELETE FROM escape_room").executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void whenGetDetailedInventory_thenShouldReturnNameAndThemeForRoomsAndClues() throws Exception {
        Room room = new Room("Indiana Jones", "Aventura", 5);
        Room room2 = new Room("Chucky", "Terror", 4);

        Clue clue = new Clue("Llave", "Aventura", "Una pista clave", 25.0);
        Clue clue2 = new Clue("Cuchillo con sangre", "Terror", "Una pista terrorífica", 25.0);

        Item item = new Item("Palmera", "Plástico", 3);
        Item item2 = new Item("Arbol", "Plástico", 10);

        roomService.addRoom(room);
        roomService.addRoom(room2);
        clueService.addClue(clue);
        clueService.addClue(clue2);
        itemService.addItem(item);
        itemService.addItem(item2);

        InventoryDTO inventory = inventoryService.getDetailedAvailableInventory();

        AvailableRoomDTO roomDTO = inventory.getAvailableRooms().get(0);
        assertEquals("Indiana Jones", roomDTO.getName());
        assertEquals("Aventura", roomDTO.getTheme());

        AvailableClueDTO clueDTO = inventory.getAvailableClues().get(0);
        assertEquals("Llave", clueDTO.getName());
        assertEquals("Aventura", clueDTO.getTheme());

        AvailableItemDTO itemDTO = inventory.getAvailableItems().get(0);
        assertEquals("Palmera", itemDTO.getName());
        assertEquals(3, itemDTO.getQuantity());
    }

    @Test
    public void whenDetailedInventoryIsEmpty_thenShouldReturnEmptyLists() throws Exception {
        InventoryDTO inventory = inventoryService.getDetailedAvailableInventory();

        assertTrue(inventory.getAvailableRooms().isEmpty());
        assertTrue(inventory.getAvailableClues().isEmpty());
        assertTrue(inventory.getAvailableItems().isEmpty());
    }

}