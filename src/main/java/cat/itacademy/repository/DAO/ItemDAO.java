package cat.itacademy.repository.DAO;

import cat.itacademy.dto.availableInventory.AvailableItemDTO;
import cat.itacademy.dto.completeInventory.EntityItemDTO;
import cat.itacademy.model.Item;
import cat.itacademy.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDAO {

    public void insert(Item item) throws SQLException {

        String sql = "INSERT INTO item (name, material, stock, price) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public List<AvailableItemDTO> getAvailableItems() throws SQLException {
        List<AvailableItemDTO> items = new ArrayList<>();
        String sql = "SELECT name, quantity FROM item WHERE id NOT IN (SELECT item_id FROM room_item)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AvailableItemDTO item = new AvailableItemDTO(
                        rs.getString("name"),
                        rs.getInt("quantity")
                );
                items.add(item);
            }
        }
        return items;
    }

    public int getTotalAvailableItemsCount() throws SQLException {
        String sql = "SELECT COALESCE(SUM(quantity), 0) FROM item WHERE id NOT IN (SELECT item_id FROM room_item)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    public List<EntityItemDTO> getAllItemsNameAndPrice() throws SQLException {
        List<EntityItemDTO> items = new ArrayList<>();
        String sql = "SELECT name, price, quantity FROM item";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                EntityItemDTO item = new EntityItemDTO(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                items.add(item);
            }
        }
        return items;
    }

    public double getAllPrices() throws SQLException {
        String sql = "SELECT SUM(price) AS totalPrice FROM item";
    public Optional<Item> getById(int id) throws SQLException {

        String sql = "SELECT id, name, material, stock, price FROM item WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    Item item = new Item(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("material"),
                            rs.getInt("stock"),
                            rs.getDouble("price")
                    );

                    return Optional.of(item);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Item> getLastItem() throws SQLException {
        String sql = "SELECT id, name, material, stock, price FROM item ORDER BY id DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                return rs.getDouble("totalPrice");
            }
        }
        return 0.0;
    }
            if(rs.next()){
                Item item = new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("material"),
                        rs.getInt("stock"),
                        rs.getDouble("price")
                );
                return Optional.of(item);
            }
            return Optional.empty();
        }
    }

   public void updateStock(int itemId, int stock) throws SQLException {
       String sql = "UPDATE item SET stock = ? WHERE id = ?";
       try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
           stmt.setInt(1, stock);
           stmt.setInt(2, itemId);
           stmt.executeUpdate();
       }
   }

}


