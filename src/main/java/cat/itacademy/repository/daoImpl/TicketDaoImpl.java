package cat.itacademy.repository.daoImpl;

import cat.itacademy.dto.completeInventory.EntityTicketDTO;
import cat.itacademy.model.Ticket;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.repository.dao.TicketDao;
import cat.itacademy.service.RoomService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDaoImpl implements TicketDao {

    private RoomService roomService;
    public TicketDaoImpl() {
        roomService = new RoomService();
    }

    @Override
    public void insert(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO ticket (client_id, room_id, total_price) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticket.getClientId());
            stmt.setInt(2, ticket.getRoomId());
            stmt.setDouble(3, roomService.getRoomById(ticket.getRoomId()).getPrice());

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Ticket> getLast() throws SQLException {
        String sql = "SELECT id, client_id, date_creation, room_id, total_price FROM ticket ORDER BY id DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

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

    @Override
    public List<EntityTicketDTO> getAllTicketsNameAndPrice() throws SQLException {
        List<EntityTicketDTO> tickets = new ArrayList<>();
        String sql = "SELECT date_creation, total_price FROM ticket";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                EntityTicketDTO ticket = new EntityTicketDTO(
                        rs.getTimestamp("date_creation").toLocalDateTime(),
                        rs.getDouble("total_price")
                );
                tickets.add(ticket);
            }
        }
        return tickets;
    }
}
