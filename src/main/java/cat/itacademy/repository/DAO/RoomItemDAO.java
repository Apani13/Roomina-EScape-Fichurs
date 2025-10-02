package cat.itacademy.repository.DAO;

import cat.itacademy.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomItemDAO {

    private static final String INSERT_SQL =
            "INSERT INTO room_item(room_id, item_id, quantity) VALUES (?, ?, ?)";
    private static final String DELETE_SQL =
            "DELETE FROM room_item WHERE room_id = ? AND item_id = ?";
    private static final String SELECT_QTY_SQL =
            "SELECT quantity FROM room_item WHERE room_id = ? AND item_id = ?";

    private static final String UPDATE_QTY_SQL =
            "UPDATE quantity FROM room_item WHERE room_id = ? AND item_id = ?";


    public void insertRoomItem(int roomId, int itemId, int qty) throws SQLException {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setInt(1, roomId);
            stmt.setInt(2, itemId);
            stmt.setInt(3, qty);
            stmt.executeUpdate();
        }
    }


    public void deleteRoomItem(int roomId, int itemId) throws SQLException {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, roomId);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();
        }
    }


    public int getQuantity(int roomId, int itemId) throws SQLException {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_QTY_SQL)) {
            stmt.setInt(1, roomId);
            stmt.setInt(2, itemId);

            try (ResultSet rs = stmt.executeQuery()) {
                return (rs.next()) ? rs.getInt("quantity") : null;
            }
        }

    }

    public Integer updateQuantity(int roomId, int itemId) throws SQLException {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_QTY_SQL)) {
            stmt.setInt(1, roomId);
            stmt.setInt(2, itemId);

    }


}
