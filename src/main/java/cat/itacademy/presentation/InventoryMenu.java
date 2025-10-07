package cat.itacademy.presentation;

import cat.itacademy.console.AvailableInventoryPrinter;
import cat.itacademy.console.CompleteInventoryPrinter;
import cat.itacademy.console.ConsoleUtils;
import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.exception.EmptyListException;
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
        this.availablePrinter = new AvailableInventoryPrinter();
        this.completePrinter = new CompleteInventoryPrinter();
        refreshInventory();
    }

    private void refreshInventory() {
        try {
            this.availableInventory = inventoryService.getAvailableInventory();
            this.completeInvetory = inventoryService.getCompleteInventory();
        } catch (SQLException e) {
            System.out.println("❌ Error al cargar el inventario: " + e.getMessage());
        } catch (EmptyListException e) {
            System.out.println("ℹ️ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }

    public void showInventoryMenu() throws SQLException {
        boolean backToMain = false;

        while (!backToMain) {
            try {
                refreshInventory();
                System.out.println("📦 GESTIÓN DE INVENTARIO");
                System.out.println("1. Mostrar Elementos Disponibles");
                System.out.println("2. Valor total del Inventario");
                System.out.println("0. Volver al Menú Principal");
                System.out.print("Selecciona una opción: ");

                int option = readOption();
                backToMain = processInventoryOption(option);

            } catch (Exception e) {
                System.out.println("❌ Error inesperado: " + e.getMessage());
                ConsoleUtils.pressEnterToContinue(sc);
            }
        }
    }

    private boolean processInventoryOption(int option) throws SQLException {
        try {
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
        } catch (EmptyListException e) {
            System.out.println("ℹ️ " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Error de base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }

        ConsoleUtils.pressEnterToContinue(sc);
        return false;
    }

    private void showAvailableElements() throws SQLException, EmptyListException {
        System.out.println("\n🆓 ELEMENTOS DISPONIBLES");
        System.out.println(availablePrinter.printAvailableInventory(availableInventory));
    }

    private void showTotalElements() throws SQLException, EmptyListException {
        System.out.println("\n📊 ELEMENTOS TOTALES CREADOS");
        System.out.println(availablePrinter.printAvailableInventoryValue(availableInventory));
    }

    private int readOption() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Debe ingresar un número válido");
            return -1;
        }
    }
}


