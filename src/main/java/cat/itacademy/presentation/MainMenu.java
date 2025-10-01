package cat.itacademy.presentation;

import cat.itacademy.dto.InventoryDTO;
import cat.itacademy.service.*;

import java.sql.SQLException;

public class MainMenu {

    private InventoryService inventoryService;


    public MainMenu() {
        this.inventoryService = new InventoryService();
    }

    public void mostrarInventarioDetallado() {
        try {
            InventoryDTO inventario = inventoryService.getDetailedAvailableInventory();
            System.out.println(inventario.toString());
        } catch (SQLException e) {
            System.out.println("Error al obtener el inventario: " + e.getMessage());
        }
    }

}
