package cat.itacademy.controllers;

import cat.itacademy.models.Clue;
import cat.itacademy.repositories.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
