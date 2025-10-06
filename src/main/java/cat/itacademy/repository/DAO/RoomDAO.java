package cat.itacademy.repository.DAO;
import cat.itacademy.dto.availableInventory.AvailableRoomDTO;
import cat.itacademy.dto.completeInventory.EntityRoomDTO;
import cat.itacademy.model.Room;
import cat.itacademy.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDAO {

    public void insert(Room room) throws SQLException {
        String sql = "INSERT INTO room (name, theme, level, price) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, room.getName());
            stmt.setString(2, room.getTheme());
            stmt.setInt(3, room.getLevel());
            stmt.setDouble(4, room.getPrice());

            stmt.executeUpdate();
        }
    }

    public boolean existsByName(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM room WHERE name = ?";
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

    public List<Room> getAllNames() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT id, name FROM room";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        }
        return rooms;
    }

    public Optional<Room> getLastRoom() throws SQLException {
        String sql = "SELECT id, name, theme, level, price, escape_room_id FROM room ORDER BY id DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if(rs.next()){
                int escapeRoomId = rs.getInt("escape_room_id");
                boolean wasNull = rs.wasNull();

                 Room room = new Room(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("theme"),
                        rs.getInt("level"),
                        wasNull ? null : escapeRoomId
                );
                return Optional.of(room);
            }
            return Optional.empty();
        }
    }


    public Optional<Room>  getById(int id) throws SQLException {

        String sql = "SELECT id, name, theme,level, price, escape_room_id FROM room WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int escapeRoomId = rs.getInt("escape_room_id");
                    boolean wasNull = rs.wasNull();

                    Room room = new Room(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("theme"),
                            rs.getInt("level"),

                            rs.getInt("price"),
                            wasNull ? null : escapeRoomId
                    );

                    return Optional.of(room);
                }
            }
        }
        return Optional.empty();
    }





    public boolean existsById(int id) throws SQLException {

        String sql = "SELECT 1 FROM room WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); }
        }
    }


    public static List<Room> getRoomsWithClues()throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT DISTINCT r.id, r.name FROM room r JOIN clue c ON r.id = c.room_id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    rooms.add(new Room(
                            rs.getInt("id"),
                            rs.getString("name")
                    ));
                }
            }
        }
        return rooms;
    }

    public void removeClueFromRoom(int clueId)throws SQLException {
        String sql = "UPDATE clue SET room_id = NULL WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clueId);

            stmt.executeUpdate();
        }
    }

    public List<AvailableRoomDTO> getAvailableRooms() throws SQLException {
        List<AvailableRoomDTO> rooms = new ArrayList<>();
        String sql = "SELECT name, theme FROM room WHERE escape_room_id IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AvailableRoomDTO room = new AvailableRoomDTO(
                        rs.getString("name"),
                        rs.getString("theme")
                );
                rooms.add(room);
            }
        }
        return rooms;
    }

    public List<EntityRoomDTO> getAllRoomsNameAndPrice() throws SQLException {
        List<EntityRoomDTO> rooms = new ArrayList<>();
        String sql = "SELECT name, price FROM room";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EntityRoomDTO room = new EntityRoomDTO(
                        rs.getString("name"),
                        rs.getDouble("price")
                );
                rooms.add(room);
            }
        }
        return rooms;
    }

    public double getAllPrices() throws SQLException {
        String sql = "SELECT SUM(price) AS totalPrice FROM room";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
               return rs.getDouble("totalPrice");
            }
        }
        return 0.0;
    }
}
