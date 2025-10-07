package cat.itacademy.service;

import cat.itacademy.console.CompleteInventoryPrinter;
import cat.itacademy.dto.availableInventory.*;
import cat.itacademy.dto.completeInventory.*;
import cat.itacademy.dto.usedInventory.*;
import cat.itacademy.repository.dao.EscapeRoomDao;
import cat.itacademy.repository.daoImpl.*;

import java.sql.SQLException;
import java.util.List;

public class InventoryService {

    private RoomDaoImpl roomDAO;
    private ClueDaoImpl clueDAO;
    private ItemDaoImpl itemDAO;
    private TicketDaoImpl ticketDAO;
    private EscapeRoomDaoImpl escapeRoomDAO;

    public InventoryService() {
        this.roomDAO = new RoomDaoImpl();
        this.clueDAO = new ClueDaoImpl();
        this.itemDAO = new ItemDaoImpl();
        this.ticketDAO = new TicketDaoImpl();
        this.escapeRoomDAO = new EscapeRoomDaoImpl();
    }

    public AvailableInventoryDTO getAvailableInventory() throws SQLException {
        List<AvailableRoomDTO> availableRooms = roomDAO.getAvailableRooms();
        List<AvailableClueDTO> availableClues = clueDAO.getAvailableClues();
        List<AvailableItemDTO> availableItems = itemDAO.getAvailableItems();
        List<AvailableEscapeRoomDTO> availableEscapeRooms = escapeRoomDAO.findAll();
        int totalItemUnits = itemDAO.getTotalAvailableItemsCount();

        return new AvailableInventoryDTO(availableEscapeRooms, availableRooms, availableClues, availableItems, totalItemUnits);
    }

    public CompleteInventoryDTO getCompleteInventory() throws SQLException {
        List<EntityRoomDTO> allRoms = roomDAO.getAllRoomsNameAndPrice();
        List<EntityClueDTO> allClues = clueDAO.getAllCluesNameAndPrice();
        List<EntityItemDTO> allItems = itemDAO.getAllItemsNameAndPrice();
        List<EntityEscapeRoomDTO> availableEscapeRooms = escapeRoomDAO.findAllComplete();
        List<EntityTicketDTO> allTickets = ticketDAO.getAllTicketsNameAndPrice();
        double totalPrice = roomDAO.getAllPrices() + itemDAO.getAllPrices() + clueDAO.getAllPrices();

        return new CompleteInventoryDTO(availableEscapeRooms, allRoms,allClues,allItems, allTickets, totalPrice);
    }

    public UsedInventoryDTO getUsedInventory() throws SQLException {
        List<UsedEscapeRoomDTO> usedEscapeRooms = escapeRoomDAO.getUsedEscapeRooms();
        List<UsedRoomDTO> usedRooms = roomDAO.getUsedRooms();
        List<UsedClueDTO> usedClues = clueDAO.getUsedClues();
        List<UsedItemDTO> usedItems = itemDAO.getUsedItems();
        int totalUsedItems = itemDAO.getTotalUsedItemsCount();

        return new UsedInventoryDTO(usedEscapeRooms, usedRooms, usedClues, usedItems, totalUsedItems);
    }
}


