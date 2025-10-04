package cat.itacademy.presentation;

import cat.itacademy.console.AvailableInventoryPrinter;
import cat.itacademy.console.CompleteInventoryPrinter;
import cat.itacademy.console.SalesPrinter;
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
            AvailableInventoryPrinter printer = new AvailableInventoryPrinter();
            System.out.println(printer.printAvailableInventory(inventory));
        } catch (SQLException e) {
            System.out.println("Error al obtener el inventario: " + e.getMessage());
        }
    }
    
    public void showTotalInventoryPrice() throws SQLException {
        try{
            CompleteInventoryDTO inventory =  inventoryService.getCompleteInventory();
            CompleteInventoryPrinter printer = new CompleteInventoryPrinter();
            System.out.printf(printer.printCompleteInventory(inventory));
        } catch (SQLException e) {
            System.out.println("Error al obtener el inventario: " + e.getMessage());
        }
    }

    public void showTotalValueOfSales() throws SQLException {
        try{
            SalesPrinter printer = new SalesPrinter();
            CompleteInventoryDTO inventory =  inventoryService.getCompleteInventory();
            System.out.printf(printer.printSalesReport(inventory));
        } catch (SQLException e) {
            System.out.println("Error al obtener el inventario: " + e.getMessage());
        }
    }
}
