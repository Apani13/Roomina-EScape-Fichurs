package cat.itacademy.service;

import cat.itacademy.dto.availableInventory.AvailableClueDTO;
import cat.itacademy.dto.availableInventory.AvailableItemDTO;
import cat.itacademy.dto.availableInventory.AvailableRoomDTO;
import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.*;
import cat.itacademy.repository.daoImpl.ClueDaoImpl;
import cat.itacademy.repository.daoImpl.ItemDaoImpl;
import cat.itacademy.repository.daoImpl.RoomDaoImpl;
import cat.itacademy.repository.daoImpl.TicketDaoImpl;
import java.sql.SQLException;
import java.util.List;

public class InventoryService {

    private RoomDaoImpl roomDAO;
    private ClueDaoImpl clueDAO;
    private ItemDaoImpl itemDAO;
    private TicketDaoImpl ticketDAO;

    public InventoryService() {
        this.roomDAO = new RoomDaoImpl();
        this.clueDAO = new ClueDaoImpl();
        this.itemDAO = new ItemDaoImpl();
        this.ticketDAO = new TicketDaoImpl();
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


