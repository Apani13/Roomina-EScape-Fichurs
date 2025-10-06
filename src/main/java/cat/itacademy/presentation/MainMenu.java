package cat.itacademy.presentation;

import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.service.*;

import java.sql.SQLException;

public class MainMenu {

    private InventoryService inventoryService;

    public MainMenu() {
        this.inventoryService = new InventoryService();
    }

    public void showAvailableInventory() {
        try {
            AvailableInventoryDTO inventory = inventoryService.getAvailableInventory();
            System.out.println(inventory.toString());
        } catch (SQLException e) {
            System.out.println("Error al obtener el inventario: " + e.getMessage());
        }
    }
    
    public void showTotalInventoryPrice() throws SQLException {
        try{
            CompleteInventoryDTO inventory =  inventoryService.getCompleteInventory();
            System.out.printf(inventory.toString());
        } catch (SQLException e) {
            System.out.println("Error al obtener el inventario: " + e.getMessage());
        }
    }
}
