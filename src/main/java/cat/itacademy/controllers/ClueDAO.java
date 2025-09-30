package cat.itacademy.controllers;

import cat.itacademy.models.Clue;
import cat.itacademy.models.Room;
import cat.itacademy.repositories.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

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

    public Clue getLastClue() throws SQLException {
        String sql = "SELECT id, name, theme, description, price, room_id FROM clue ORDER BY id DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();){

            if(rs.next()){
                return new Clue(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("theme"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("room_id")
                );
            }
            return null;
        }
    }
    public Clue getById(int id) throws SQLException {
        String sql = "SELECT id, name, theme, description, price, room_id FROM clue WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Clue(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("theme"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("room_id")
                    );
                }
            }
        }
        return null;
    }

}
