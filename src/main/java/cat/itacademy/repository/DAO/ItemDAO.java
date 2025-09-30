package cat.itacademy.repository.DAO;

import cat.itacademy.model.Item;
import cat.itacademy.repository.DatabaseConnection;

import java.sql.*;

public class ItemDAO {

    public void insert(Item item) throws SQLException {
        String sql = "INSERT INTO item (name, material, stock, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getMaterial());
            stmt.setInt(3, item.getStock());
            stmt.setDouble(4, item.getPrice());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    item.setId(keys.getInt(1));
                }
            }
        }
    }

    public boolean existsByName(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM item WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {

                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public Item getById(int id) throws SQLException {
        String sql = "SELECT id, name, material, stock, price FROM item WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Item(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("material"),
                            rs.getInt("stock"),
                            rs.getDouble("price")
                    );
                }
            }
        }
        return null;
    }

    public boolean existsById(int id) throws SQLException {
        String sql = "SELECT 1 FROM item WHERE id = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

}
