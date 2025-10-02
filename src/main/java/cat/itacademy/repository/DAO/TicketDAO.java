package cat.itacademy.repository.DAO;

import cat.itacademy.model.Room;
import cat.itacademy.model.Ticket;
import cat.itacademy.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TicketDAO {
    private RoomDAO roomDAO;
    public  TicketDAO() {
        roomDAO = new RoomDAO();
    }
    public void insert(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO ticket (client_id, room_id, total_price) VALUES(?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,ticket.getClientId());
            stmt.setInt(2, ticket.getRoomId());
            stmt.setDouble(3,roomDAO.getById(ticket.getRoomId()).get().getPrice());
            stmt.executeUpdate();
        }
    }

    public Optional<Ticket> getLastRecord() throws SQLException {
        String sql = "SELECT id, client_id, date_creation, room_id, total_price FROM ticket ORDER BY id DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();){

            if(rs.next()){
                Ticket ticket = new Ticket(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getInt("room_id"),
                        rs.getTimestamp("date_creation").toLocalDateTime(),
                        rs.getDouble("total_price")
                );
                return Optional.of(ticket);
            }
            return Optional.empty();
        }
    }
}
