package cat.itacademy.repository.daoImpl;
import cat.itacademy.dto.availableInventory.AvailableRoomDTO;
import cat.itacademy.dto.completeInventory.EntityRoomDTO;
import cat.itacademy.dto.usedInventory.UsedRoomDTO;
import cat.itacademy.model.Clue;
import cat.itacademy.model.Item;
import cat.itacademy.model.Room;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.repository.dao.RoomDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl implements RoomDao {

    @Override
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
    

    @Override
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

    @Override
    public Optional<Room> getLast() throws SQLException {
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

    @Override
    public Optional<Room> getById(int id) throws SQLException {
        String sql = "SELECT id, name FROM escape_room WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Room room = new Room(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                    return Optional.of(room);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Room> getRoomsWithClues()throws SQLException {
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


    public List<Room> getRoomsWithItems()throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT DISTINCT r.id, r.name FROM room r JOIN item i ON r.id = i.room_id";
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

    @Override
    public void removeClueFromRoom(int roomId, int clueId) throws SQLException {
        String sql = "UPDATE clue SET room_id = NULL WHERE id = ? AND room_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clueId);
            stmt.setInt(2, roomId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeItemFromRoom(int roomId, int itemId) throws SQLException {
        String sql = "DELETE FROM room_item WHERE room_id = ? AND item_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roomId);
            stmt.setInt(2, itemId);

            stmt.executeUpdate();
        }
    }

    @Override
    public void increaseItemStock(int itemId, int quantity) throws SQLException {
        String sql = "UPDATE item SET stock = stock + ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, itemId);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<AvailableRoomDTO> getAvailableRooms() throws SQLException {
        List<AvailableRoomDTO> rooms = new ArrayList<>();
        String sql = "SELECT id, name, price, theme FROM room WHERE escape_room_id IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AvailableRoomDTO room = new AvailableRoomDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("theme")
                );
                rooms.add(room);
            }
        }
        return rooms;
    }

    @Override
    public List<EntityRoomDTO> getAllRoomsNameAndPrice() throws SQLException {
        List<EntityRoomDTO> rooms = new ArrayList<>();
        String sql = "SELECT id, name, theme, price FROM room";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EntityRoomDTO room = new EntityRoomDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("theme"),
                        rs.getDouble("price")
                );
                rooms.add(room);
            }
        }
        return rooms;
    }

    @Override
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

    @Override
    public List<Clue> getCluesByRoomId(int roomId) throws SQLException {
        List<Clue> clues = new ArrayList<>();
        String sql = "SELECT c.id, c.name " +
                "FROM clue c WHERE c.room_id = ?";
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
    public List<Item> getItemsByRoomId(int itemId) throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT i.id, i.name " +
                "FROM item i WHERE i.room_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                items.add(new Item(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        }
        return items;
    }

    @Override
    public List<UsedRoomDTO> getUsedRooms() throws SQLException {
        List<UsedRoomDTO> usedRooms = new ArrayList<>();
        String sql = "SELECT r.id, r.name, r.theme, er.id as escape_room_id, er.name as escape_room_name " +
                "FROM room r " +
                "INNER JOIN escape_room er ON r.escape_room_id = er.id " +  // Cambiado: JOIN directo
                "WHERE r.escape_room_id IS NOT NULL " +  // Añadido: solo salas asignadas
                "ORDER BY r.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UsedRoomDTO usedRoom = new UsedRoomDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("theme"),
                        rs.getInt("escape_room_id"),
                        rs.getString("escape_room_name")
                );
                usedRooms.add(usedRoom);
            }
        }
        return usedRooms;
    }
}
