package cat.itacademy.service;

import cat.itacademy.dto.availableInventory.AvailableClueDTO;
import cat.itacademy.dto.availableInventory.AvailableItemDTO;
import cat.itacademy.dto.availableInventory.AvailableRoomDTO;
import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.AllCluesDTO;
import cat.itacademy.dto.completeInventory.AllItemsDTO;
import cat.itacademy.dto.completeInventory.AllRoomsDTO;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
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

    public AvailableInventoryDTO getAvailableInventory() throws SQLException {
        List<AvailableRoomDTO> availableRooms = roomDAO.getAvailableRooms();
        List<AvailableClueDTO> availableClues = clueDAO.getAvailableClues();
        List<AvailableItemDTO> availableItems = itemDAO.getAvailableItems();
        int totalItemUnits = itemDAO.getTotalAvailableItemsCount();

        return new AvailableInventoryDTO(availableRooms, availableClues, availableItems, totalItemUnits);
    }

    public CompleteInventoryDTO getCompleteInventory() throws SQLException {
        List<AllRoomsDTO> allRoms = roomDAO.getAllRoomsNameAndPrice();
        List<AllCluesDTO> allClues = clueDAO.getAllCluesNameAndPrice();
        List<AllItemsDTO> allItems = itemDAO.getAllItemsNameAndPrice();
        double totalPrice = roomDAO.getAllPrices() + itemDAO.getAllPrices() + clueDAO.getAllPrices();

        return new CompleteInventoryDTO(allRoms,allClues,allItems, totalPrice);
    }
}


