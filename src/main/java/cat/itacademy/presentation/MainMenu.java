package cat.itacademy.presentation;

import cat.itacademy.console.AvailableInventoryPrinter;
import cat.itacademy.console.CompleteInventoryPrinter;
import cat.itacademy.console.SalesPrinter;
import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    private InventoryService inventoryService;
    private Scanner sc;
    private InventoryMenu inventoryMenu;
    private NotificationMenu notificationMenu;
    private EscapeRoomMenu escapeRoomMenu;
    private SalesTicketsMenu salesTicketsMenu;
    private boolean exit = false;

    public MainMenu() throws SQLException {
        this.inventoryService = new InventoryService();
        this.sc = new Scanner(System.in);
        this.inventoryMenu = new InventoryMenu(sc, inventoryService);
        this.notificationMenu = new NotificationMenu(sc);
        this.salesTicketsMenu = new SalesTicketsMenu(sc);
        this.escapeRoomMenu = new EscapeRoomMenu(sc);
    }

    public void start() throws SQLException {
        System.out.println("🎮 BIENVENIDO/A AL SISTEMA DE ESCAPE ROOMS VIRTUALES 🎮");

        while (!exit) {
            showMainMenu();
            int option = readOption();
            processOption(option);
        }
        sc.close();
    }

    private void showMainMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. 🎪 Gestión de Escape Rooms");
        System.out.println("2. 📦 Gestión de Inventario");
        System.out.println("3. 🎫 Gestión de Ventas y Tickets");
        System.out.println("4. 🔔 Gestión de Notificaciones");
        System.out.println("0. 🚪 Salir");
        System.out.print("Selecciona una opción: ");
    }

    private int readOption() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void processOption(int option) throws SQLException {
        switch (option) {
            case 1 -> escapeRoomMenu.showEscapeRoomMenu();
            case 2 -> inventoryMenu.showInventoryMenu();
            case 3 -> salesTicketsMenu.showSalesTicketsMenu();
            case 4 -> notificationMenu.showNotificationsMenu();
            case 0 -> {
                exit = true;
                System.out.println("👋 ¡Hasta pronto!");
            }
            default -> System.out.println("❌ Opción no válida");
        }
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
