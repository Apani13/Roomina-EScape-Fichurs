package cat.itacademy.repository.daoImpl;

import cat.itacademy.model.Client;
import cat.itacademy.repository.dao.ClientDao;
import cat.itacademy.repository.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao {

    @Override
    public void insert(Client client) throws SQLException {
        String sql = "INSERT INTO client(user_name, email, phone, accepts_notifications) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, client.getUserName());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getPhone());
            stmt.setBoolean(4, client.isAcceptsNotifications());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    client.setId(rs.getInt(1)); // ⚡ ahora el objeto original queda sincronizado con la BD
                }
            }
        }
    }

    @Override
    public List<Client> findAll() throws SQLException {
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
        return clients;
    }


    public void update(Client client) throws SQLException {
        String sql = "UPDATE escape_room SET user_name = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getUserName());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getPhone());
            stmt.setInt(4, client.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean existsByName(String userName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM client WHERE user_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    @Override
    public Optional<Client> getLast() throws SQLException {

        String sql = "SELECT * FROM client ORDER BY id DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                Boolean acceptsNotifications = (Boolean) rs.getObject("accepts_notifications");
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        acceptsNotifications != null ? acceptsNotifications : false
                );
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> getById(int id) throws SQLException {
        String sql = "SELECT * FROM client WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Boolean acceptsNotifications = (Boolean) rs.getObject("accepts_notifications");
                    Client client = new Client(
                            rs.getInt("id"),
                            rs.getString("user_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            acceptsNotifications != null ? acceptsNotifications : false
                    );
                    return Optional.of(client);
                }
            }
        }
        return Optional.empty();
    }
}
