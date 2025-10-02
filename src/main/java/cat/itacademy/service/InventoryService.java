package cat.itacademy.service;

import cat.itacademy.dto.AvailableClueDTO;
import cat.itacademy.dto.AvailableItemDTO;
import cat.itacademy.dto.AvailableRoomDTO;
import cat.itacademy.dto.InventoryDTO;
import cat.itacademy.repository.DAO.ClueDAO;
import cat.itacademy.repository.DAO.ItemDAO;
import cat.itacademy.repository.DAO.RoomDAO;

import java.sql.SQLException;
import java.util.List;

public class InventoryService {

    private RoomDAO roomDAO;
    private ClueDAO clueDAO;
    private ItemDAO itemDAO;

    public InventoryService() {
        this.roomDAO = new RoomDAO();
        this.clueDAO = new ClueDAO();
        this.itemDAO = new ItemDAO();
    }

    public InventoryDTO getAvailableInventory() throws SQLException {
        List<AvailableRoomDTO> availableRooms = roomDAO.getAvailableRoomsWithDetails();
        List<AvailableClueDTO> availableClues = clueDAO.getAvailableCluesWithDetails();
        List<AvailableItemDTO> availableItems = itemDAO.getAvailableItemsWithDetails();
        int totalItemUnits = itemDAO.getTotalAvailableItemsCount();

        return new InventoryDTO(availableRooms, availableClues, availableItems, totalItemUnits);
    }
}


