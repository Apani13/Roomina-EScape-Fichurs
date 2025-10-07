package cat.itacademy.repository.daoImpl;

import cat.itacademy.dto.availableInventory.AvailableClueDTO;
import cat.itacademy.dto.completeInventory.EntityClueDTO;
import cat.itacademy.dto.usedInventory.UsedClueDTO;
import cat.itacademy.model.Clue;
import cat.itacademy.repository.dao.ClueDao;
import cat.itacademy.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClueDaoImpl implements ClueDao {

    @Override
    public void insert(Clue clue) throws SQLException {
        String sql = "INSERT INTO clue(name, theme, description, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, clue.getName());
            stmt.setString(2, clue.getTheme());
            stmt.setString(3, clue.getDescription());
            stmt.setDouble(4, clue.getPrice());

            stmt.executeUpdate();
        }
    }

    @Override
    public boolean existsByName(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM clue WHERE name = ?";
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

    @Override
    public List<Clue> getAllNames() throws SQLException {
        List<Clue> clues = new ArrayList<>();
        String sql = "SELECT id, name FROM clue WHERE room_id IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clues.add(new Clue(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        }
        return clues;
    }

    @Override
    public Optional<Clue> getLast() throws SQLException {
        String sql = "SELECT id, name, theme, description, price, room_id FROM clue ORDER BY id DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            if(rs.next()){
                Clue clue = new Clue(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("theme"),
                        rs.getString("description"),
                        rs.getInt("room_id")
                );
                return Optional.of(clue);
            }
            return Optional.empty();
        }
    }

    @Override
    public Optional<Clue> getById(int id) throws SQLException {
        String sql = "SELECT id, name, theme, description, price, room_id FROM clue WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Clue clue = new Clue(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("theme"),
                            rs.getString("description"),
                            rs.getObject("room_id", Integer.class)
                    );
                    return Optional.of(clue);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<AvailableClueDTO> getAvailableClues() throws SQLException {
        List<AvailableClueDTO> clues = new ArrayList<>();
        String sql = "SELECT id, name, price, theme FROM clue WHERE room_id IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AvailableClueDTO clue = new AvailableClueDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("theme")
                );
                clues.add(clue);
            }
        }
        return clues;
    }

    @Override
    public List<EntityClueDTO> getAllCluesNameAndPrice() throws SQLException {
        List<EntityClueDTO> clues = new ArrayList<>();
        String sql = "SELECT id, name, theme, price FROM clue";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                EntityClueDTO clue = new EntityClueDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("theme"),
                        rs.getDouble("price")
                );
                clues.add(clue);
            }
        }
        return clues;
    }

    @Override
    public double getAllPrices() throws SQLException {
        String sql = "SELECT SUM(price) AS totalPrice FROM clue";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                return rs.getDouble("totalPrice");
            }
        }
        return 0.0;
    }

    @Override
    public void updateRoomIdClue(int roomId, int clueId) throws SQLException {
        String sql = "UPDATE clue SET room_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roomId);
            stmt.setInt(2, clueId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<UsedClueDTO> getUsedClues() throws SQLException {
        List<UsedClueDTO> usedClues = new ArrayList<>();
        String sql = "SELECT c.id, c.name, c.theme, c.room_id, r.name as room_name " +
                "FROM clue c " +
                "INNER JOIN room r ON c.room_id = r.id " +
                "WHERE c.room_id IS NOT NULL " +
                "ORDER BY c.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UsedClueDTO usedClue = new UsedClueDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("theme"),
                        rs.getInt("room_id"),
                        rs.getString("room_name")
                );
                usedClues.add(usedClue);
            }
        }
        return usedClues;
    }
}

