package cat.itacademy.presentation;

import cat.itacademy.console.AvailableInventoryPrinter;
import cat.itacademy.console.CompleteInventoryPrinter;
import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.service.InventoryService;

import java.sql.SQLException;
import java.util.Scanner;

public class InventoryMenu {
    private Scanner sc;
    private InventoryService inventoryService;
    private AvailableInventoryDTO availableInventory;
    private CompleteInventoryDTO completeInvetory;
    private AvailableInventoryPrinter availablePrinter;
    private CompleteInventoryPrinter completePrinter;

    public InventoryMenu(Scanner sc, InventoryService inventoryService) throws SQLException {
        this.sc = sc;
        this.inventoryService = inventoryService;
        this.availableInventory = inventoryService.getAvailableInventory();
        this.completeInvetory = inventoryService.getCompleteInventory();
        this.availablePrinter = new AvailableInventoryPrinter();
        this.completePrinter = new CompleteInventoryPrinter();
    }

    public void showInventoryMenu() throws SQLException {
        boolean backToMain = false;

        while (!backToMain) {
            ConsoleUtils.clearScreen();
            System.out.println("📦 GESTIÓN DE INVENTARIO");
            System.out.println("1. Mostrar Elementos Disponibles");
            System.out.println("2. Mostrar Elementos Totales Creados");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");

            int option = readOption();
            backToMain = processInventoryOption(option);
        }
    }

    private boolean processInventoryOption(int option) throws SQLException {
        switch (option) {
            case 1:
                showAvailableElements();
                break;
            case 2:
                showTotalElements();
                break;
            case 0:
                return true;
            default:
                System.out.println("❌ Opción no válida");
        }
        ConsoleUtils.pressEnterToContinue(sc);
        return false;
    }

    private void showAvailableElements() throws SQLException {
        System.out.println("\n🆓 ELEMENTOS DISPONIBLES");
        availablePrinter.printAvailableInventory(availableInventory);
    }

    private void showTotalElements() {
        System.out.println("\n📊 ELEMENTOS TOTALES CREADOS");
        completePrinter.printCompleteInventory(completeInvetory);
    }

    private int readOption() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}


