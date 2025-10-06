package cat.itacademy.repository.dao;

import cat.itacademy.dto.completeInventory.EntityTicketDTO;
import cat.itacademy.model.Ticket;
import cat.itacademy.repository.crud.Create;
import cat.itacademy.repository.crud.read.GetLast;

import java.sql.SQLException;
import java.util.List;

public interface TicketDao extends Create<Ticket>, GetLast<Ticket> {
    List<EntityTicketDTO> getAllTicketsNameAndPrice() throws SQLException;
}
