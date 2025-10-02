// InventoryServiceTest.java
package inventary;

import cat.itacademy.dto.availableInventory.AvailableClueDTO;
import cat.itacademy.dto.availableInventory.AvailableItemDTO;
import cat.itacademy.dto.availableInventory.AvailableRoomDTO;
import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.AllCluesDTO;
import cat.itacademy.dto.completeInventory.AllItemsDTO;
import cat.itacademy.dto.completeInventory.AllRoomsDTO;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.service.InventoryService;
import cat.itacademy.service.RoomService;
import cat.itacademy.service.ClueService;
import cat.itacademy.service.ItemService;
import cat.itacademy.model.Room;
import cat.itacademy.model.Clue;
import cat.itacademy.model.Item;
import cat.itacademy.validation.inventory.InventoryEmptyValidator;
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
    public void whenGetAvailableInventory_thenShouldReturnCorrectInformation() throws SQLException {
        Room room = new Room("Indiana Jones", "Aventura", 5);
        Clue clue = new Clue("Llave", "Aventura", "Una pista clave", 25.0);
        Item item = new Item("Palmera", "Plástico", 3);

        roomService.addRoom(room);
        clueService.addClue(clue);
        itemService.addItem(item);

        AvailableInventoryDTO inventory = inventoryService.getAvailableInventory();

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
    public void whenAvailableInventoryIsEmpty_thenShouldThrowEmptyListException() throws SQLException {
        AvailableInventoryDTO inventory = inventoryService.getAvailableInventory();
        InventoryEmptyValidator validator = new InventoryEmptyValidator();

        assertThrows(EmptyListException.class, () -> validator.validateAvailableInventory(inventory));
    }

    public void whenGetCompleteInventory_thenShouldReturnCorrectInformation() throws SQLException {
        Room room = new Room("Indiana Jones", "Aventura", 5);
        Clue clue = new Clue("Llave", "Aventura", "Una pista clave", 25.0);
        Item item = new Item("Palmera", "Plástico", 3);

        roomService.addRoom(room);
        clueService.addClue(clue);
        itemService.addItem(item);

        CompleteInventoryDTO inventory = inventoryService.getCompleteInventory();

        AllRoomsDTO roomDTO = inventory.getAllRoms().get(0);
        assertEquals("Indiana Jones", roomDTO.getName());
        assertEquals( room.getPrice(), roomDTO.getPrice());

        AllCluesDTO clueDTO = inventory.getAllClues().get(0);
        assertEquals("Llave", clueDTO.getName());
        assertEquals("Aventura", clueDTO.getPrice());

        AllItemsDTO itemDTO = inventory.getAllItems().get(0);
        assertEquals("Palmera", itemDTO.getName());
        assertEquals(3, itemDTO.getPrice());
    }

    @Test
    public void whenCompleteInventoryIsEmpty_thenShouldThrowEmptyListException() throws SQLException {
        CompleteInventoryDTO inventory = inventoryService.getCompleteInventory();
        InventoryEmptyValidator validator = new InventoryEmptyValidator();

        assertThrows(EmptyListException.class, () -> validator.validateCompleteInventory(inventory));
    }


}