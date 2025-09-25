package cat.itacademy.controllers;

import cat.itacademy.models.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDAO {


    public void save(Room room) throws SQLException {
        String sql = "INSERT INTO escaperoom (name, theme, level) VALUES(?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,room.getName());
            stmt.setString(2, room.getTheme());
            stmt.setInt(3,room.getLevel());
            stmt.executeUpdate();
        }
    }
}
