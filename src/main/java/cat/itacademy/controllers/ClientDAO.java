package cat.itacademy.controllers;

import cat.itacademy.models.Client;
import cat.itacademy.models.EscapeRoom;
import cat.itacademy.repositories.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    public void insert(Client client) throws SQLException {
        String sql = "INSERT INTO client(user_name, email, phone, accepts_notifications) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getUserName());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getPhone());
            stmt.setBoolean(4, client.isAcceptsNotifications());
            stmt.executeUpdate();
        }
    }

    public List<EscapeRoom> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id, user_name, email, phone, accepts_notifications FROM client";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("accepts_notifications")
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
        EscapeRoom escapeRoom = null;
        String sql = "SELECT * FROM escape_room ORDER BY id DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                escapeRoom = new EscapeRoom(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        }
        return escapeRoom;
    }
}
