package cat.itacademy.controllers;

import cat.itacademy.models.EscapeRoom;
import cat.itacademy.models.Room;
import cat.itacademy.repositories.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscapeRoomDAO {
    public void insert(EscapeRoom escapeRoom) throws SQLException {
        String sql = "INSERT INTO escape_room(name) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, escapeRoom.getName());
            stmt.executeUpdate();
        }
    }

    public List<EscapeRoom> findAll() throws SQLException {
        List<EscapeRoom> escapeRooms = new ArrayList<>();
        String sql = "SELECT id, name FROM escape_room";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                escapeRooms.add(new EscapeRoom(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        }
        return escapeRooms;
    }

    public void update(EscapeRoom escapeRoom) throws SQLException {
        String sql = "UPDATE escape_room SET name=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, escapeRoom.getName());
            stmt.setInt(2, escapeRoom.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM escape_room WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean existsByName(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM escape_room WHERE name = ?";
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

    public EscapeRoom getLastEscapeRoom() throws SQLException {
        String sql = "SELECT id, name FROM escape_room ORDER BY id DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();){

            if(rs.next()){
                return new EscapeRoom(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
            return null;
        }
    }

    public EscapeRoom getById(int id) throws SQLException {
        String sql = "SELECT id, name FROM escaperoom WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new EscapeRoom(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }
        }
        return null;
    }

    public void updateEscapeRoomIdRoom(int escapeRoomId, int roomId) throws SQLException{
        String sql = "UPDATE room SET escape_room_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, escapeRoomId);
            stmt.setInt(2, roomId);
            stmt.executeUpdate();
        }
    
    }
}
