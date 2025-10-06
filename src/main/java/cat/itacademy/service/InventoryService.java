package cat.itacademy.service;

import cat.itacademy.dto.availableInventory.AvailableClueDTO;
import cat.itacademy.dto.availableInventory.AvailableItemDTO;
import cat.itacademy.dto.availableInventory.AvailableRoomDTO;
import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.*;
import cat.itacademy.repository.DAO.ClueDAO;
import cat.itacademy.repository.DAO.ItemDAO;
import cat.itacademy.repository.DAO.RoomDAO;
import cat.itacademy.repository.DAO.TicketDAO;
import java.sql.SQLException;
import java.util.List;

public class InventoryService {

    private RoomDAO roomDAO;
    private ClueDAO clueDAO;
    private ItemDAO itemDAO;
    private TicketDAO ticketDAO;

    public InventoryService() {
        this.roomDAO = new RoomDAO();
        this.clueDAO = new ClueDAO();
        this.itemDAO = new ItemDAO();
        this.ticketDAO = new TicketDAO();
    }

    public AvailableInventoryDTO getAvailableInventory() throws SQLException {
        List<AvailableRoomDTO> availableRooms = roomDAO.getAvailableRooms();
        List<AvailableClueDTO> availableClues = clueDAO.getAvailableClues();
        List<AvailableItemDTO> availableItems = itemDAO.getAvailableItems();
        int totalItemUnits = itemDAO.getTotalAvailableItemsCount();

        return new AvailableInventoryDTO(availableRooms, availableClues, availableItems, totalItemUnits);
    }

    public CompleteInventoryDTO getCompleteInventory() throws SQLException {
        List<EntityRoomDTO> allRoms = roomDAO.getAllRoomsNameAndPrice();
        List<EntityClueDTO> allClues = clueDAO.getAllCluesNameAndPrice();
        List<EntityItemDTO> allItems = itemDAO.getAllItemsNameAndPrice();
        List<EntityTicketDTO> allTickets = ticketDAO.getAllTicketsNameAndPrice();
        double totalPrice = roomDAO.getAllPrices() + itemDAO.getAllPrices() + clueDAO.getAllPrices();

        return new CompleteInventoryDTO(allRoms,allClues,allItems, allTickets, totalPrice);
    }
}


