package assingment;

import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.repository.DAO.RoomItemDAO;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.service.RoomItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class RoomItemServiceTest {

    private final RoomItemService roomItemService = new RoomItemService();
    private final RoomItemDAO roomItemDAO = new RoomItemDAO();

    private int roomId;
    private int lanternId;
    private int chestId;

    @BeforeEach
    void setUp() {
        try (Connection conn = DatabaseConnection.getConnection()) {

            conn.createStatement().executeUpdate("DELETE FROM room_item");
            conn.createStatement().executeUpdate("DELETE FROM item");
            conn.createStatement().executeUpdate("DELETE FROM room");

            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO room(name, theme, level, price) VALUES(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, "Room 1");
                stmt.setString(2, "mystery");
                stmt.setInt(3, 2);
                stmt.setDouble(4, 20.00);
                stmt.executeUpdate();

                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    keys.next();
                    roomId = keys.getInt(1);
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO item(name, material, stock, price) VALUES(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, "Lantern");
                stmt.setString(2, "metal");
                stmt.setInt(3, 10);
                stmt.setDouble(4, 5.00);
                stmt.executeUpdate();

                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    keys.next();
                    lanternId = keys.getInt(1);
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO item(name, material, stock, price) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, "Chest");
                stmt.setString(2, "wood");
                stmt.setInt(3, 2);
                stmt.setDouble(4, 12.00);
                stmt.executeUpdate();
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    keys.next();
                    chestId = keys.getInt(1);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void whenRelatingItemThatExistToRoom_thenRelationIsMade() throws SQLException {
        roomItemService.addItemToRoom(roomId, lanternId, 3);
        assertEquals(3, roomItemDAO.getQuantity(roomId, lanternId));
    }


    @Test
    void whenAddingNonExistingItem_thenThrowsException() {
        assertThrows(InvalidAttributeException.class,
                () -> roomItemService.addItemToRoom(roomId, 999_999, 1));
    }


    @Test
    void roomCanContainMultipleItems() throws SQLException {
        roomItemService.addItemToRoom(roomId, lanternId, 1);
        roomItemService.addItemToRoom(roomId, chestId, 1);
        assertNotNull(roomItemDAO.getQuantity(roomId, lanternId));
        assertNotNull(roomItemDAO.getQuantity(roomId, chestId));
    }


    @Test
    void whenAddingItemToRoom_thenSystemReflectsItemNowBelongsToRoom() throws SQLException {
        roomItemService.addItemToRoom(roomId, lanternId, 4);
        assertEquals(4, roomItemDAO.getQuantity(roomId, lanternId));
    }


    @Test
    void whenRemovingItem_thenInventoryUpdates() throws SQLException {
        roomItemService.addItemToRoom(roomId, chestId, 5);
        roomItemService.removeItemFromRoom(roomId, chestId);
        assertNull(roomItemDAO.getQuantity(roomId, chestId));
    }

}
