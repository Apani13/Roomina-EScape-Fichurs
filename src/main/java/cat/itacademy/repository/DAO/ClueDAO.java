package cat.itacademy.repository.DAO;

import cat.itacademy.dto.AvailableClueDTO;
import cat.itacademy.model.Clue;
import cat.itacademy.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClueDAO {

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

    public Optional<Clue> getLastClue() throws SQLException {
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
                        rs.getDouble("price"),
                        rs.getInt("room_id")
                );
                return Optional.of(clue);
            }
            return Optional.empty();
        }
    }
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
                            rs.getDouble("price"),
                            rs.getObject("room_id", Integer.class)
                    );
                    return Optional.of(clue);
                }
            }
        }
        return Optional.empty();
    }

    public List<AvailableClueDTO> getAvailableCluesWithDetails() throws SQLException {
        List<AvailableClueDTO> clues = new ArrayList<>();
        String sql = "SELECT name, theme FROM clue WHERE room_id IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AvailableClueDTO clue = new AvailableClueDTO(
                        rs.getString("name"),
                        rs.getString("theme")
                );
                clues.add(clue);
            }
        }
        return clues;
    }

    public void updateRoomIdClue(int roomId, int clueId) throws SQLException {
        String sql = "UPDATE clue SET room_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roomId);
            stmt.setInt(2, clueId);
            stmt.executeUpdate();
        }
    }

}
