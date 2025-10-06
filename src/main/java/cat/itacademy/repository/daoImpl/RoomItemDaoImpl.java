package cat.itacademy.repository.daoImpl;

import cat.itacademy.model.RoomItem;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.repository.dao.RoomItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomItemDaoImpl implements RoomItemDao {

    private static final String INSERT_SQL =
            "INSERT INTO room_item(room_id, item_id, quantity) VALUES (?, ?, ?)";
    private static final String DELETE_SQL =
            "DELETE FROM room_item WHERE room_id = ? AND item_id = ?";
    private static final String SELECT_QTY_SQL =
            "SELECT quantity FROM room_item WHERE room_id = ? AND item_id = ?";

    private static final String UPDATE_QTY_SQL =
            "UPDATE quantity FROM room_item WHERE room_id = ? AND item_id = ?";

    @Override
    public void insert(RoomItem roomItem) throws SQLException {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setInt(1, roomItem.getRoomId());
            stmt.setInt(2, roomItem.getItemId());
            stmt.setInt(3, roomItem.getQuantity());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteRoomItem(int roomId, int itemId) throws SQLException {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, roomId);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();
        }
    }

    @Override
    public Integer getQuantity(int roomId, int itemId) throws SQLException {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_QTY_SQL)) {
            stmt.setInt(1, roomId);
            stmt.setInt(2, itemId);

            try (ResultSet rs = stmt.executeQuery()) {
                return (rs.next()) ? rs.getInt("quantity") : null;
            }
        }

    }

    public void updateQuantity(int roomId, int itemId) throws SQLException {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_QTY_SQL)) {
            stmt.setInt(1, roomId);
            stmt.setInt(2, itemId);

        }

    }

    @Override
    public List<RoomItem> getAllByRoomId(int roomId) throws SQLException {
        List<RoomItem> roomItems = new ArrayList<>();

        String sql = "SELECT * FROM room_item WHERE room_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ) {
            stmt.setInt(1, roomId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RoomItem roomitem = new RoomItem(
                        rs.getInt("room_id"),
                        rs.getInt("item_id"),
                        rs.getInt("quantity")
                );
                roomItems.add(roomitem);
            }
        }
        return roomItems;
    }
}
