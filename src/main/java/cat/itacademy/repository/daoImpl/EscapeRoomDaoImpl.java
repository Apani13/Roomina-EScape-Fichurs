package cat.itacademy.repository.daoImpl;

import cat.itacademy.model.EscapeRoom;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.repository.dao.EscapeRoomDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EscapeRoomDaoImpl implements EscapeRoomDao {

    @Override
    public void insert(EscapeRoom escapeRoom) throws SQLException {
        String sql = "INSERT INTO escape_room(name) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, escapeRoom.getName());

            stmt.executeUpdate();
        }
    }

    @Override
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
        String sql = "UPDATE escape_room SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, escapeRoom.getName());
            stmt.setInt(2, escapeRoom.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM escape_room WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
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

    @Override
    public Optional<EscapeRoom> getLast() throws SQLException {
        String sql = "SELECT id, name FROM escape_room ORDER BY id DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            if(rs.next()){
                EscapeRoom escapeRoom = new EscapeRoom(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                return Optional.of(escapeRoom);
            }
            return Optional.empty();
        }
    }

    @Override
    public Optional<EscapeRoom> getById(int id) throws SQLException {
        String sql = "SELECT id, name FROM escape_room WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    EscapeRoom escapeRoom = new EscapeRoom(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                    return Optional.of(escapeRoom);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<EscapeRoom> getRoomsByEscapeRoomId(int escapeRoomID) throws SQLException {
        List<EscapeRoom> escapeRooms = new ArrayList<>();
        String sql = "SELECT r.id, r.name FROM room r WHERE r.escape_room_id = ?";
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


    @Override
    public void updateEscapeRoomIdRoom(int escapeRoomId, int roomId) throws SQLException{
        String sql = "UPDATE room SET escape_room_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, escapeRoomId);
            stmt.setInt(2, roomId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeRoomFromEscapeRoom(int roomId) throws SQLException {
        String sql = "UPDATE room SET escape_room_id = NULL WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roomId);
            stmt.executeUpdate();
        }
    }
}
