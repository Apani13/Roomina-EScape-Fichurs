package cat.itacademy.presentation;

import cat.itacademy.console.ClientPrinter;
import cat.itacademy.console.ConsoleUtils;
import cat.itacademy.console.SalesPrinter;
import cat.itacademy.console.UsedInventoryPrinter;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.dto.usedInventory.UsedInventoryDTO;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.Client;
import cat.itacademy.model.Ticket;
import cat.itacademy.service.ClientService;
import cat.itacademy.service.InventoryService;
import cat.itacademy.service.TicketService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalesTicketsMenu {
    private Scanner sc;
    private TicketService ticketService;
    private ClientService clientService;
    private CompleteInventoryDTO completeInventoryDTO;
    private InventoryService inventoryService;
    private ClientPrinter clientPrinter;
    private SalesPrinter salesPrinter;
    private UsedInventoryPrinter usedInventoryPrinter;
    private UsedInventoryDTO usedInventory;


    public SalesTicketsMenu(Scanner sc) throws SQLException {
        this.sc = sc;
        this.inventoryService = new InventoryService();
        this.ticketService = new TicketService();
        this.clientService = new ClientService();
        this.clientPrinter = new ClientPrinter();
        this.salesPrinter = new SalesPrinter();
        this.usedInventoryPrinter = new UsedInventoryPrinter();
    }

    private void refreshInventory() {
        try {
            this.completeInventoryDTO = inventoryService.getCompleteInventory();
            this.usedInventory = inventoryService.getUsedInventory();
        } catch (SQLException e) {
            System.out.println("❌ Error al cargar el inventario: " + e.getMessage());
        } catch (EmptyListException e) {
            System.out.println("ℹ️ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado al cargar inventario: " + e.getMessage());
        }
    }

    public void showSalesTicketsMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            try {
                refreshInventory();
                System.out.println("🎫 GESTIÓN DE VENTAS Y TICKETS");
                System.out.println("1. Generar Ticket de Venta");
                System.out.println("2. Mostrar Total de Ingresos por Ventas");
                System.out.println("0. Volver al Menú Principal");
                System.out.print("Selecciona una opción: ");

                int option = readOption();
                backToMain = processSalesTicketsOption(option);

            } catch (Exception e) {
                System.out.println("❌ Error inesperado: " + e.getMessage());
                ConsoleUtils.pressEnterToContinue(sc);
            }
        }
    }

    private boolean processSalesTicketsOption(int option) {
        try {
            switch (option) {
                case 1:
                    generateSaleTicket();
                    break;
                case 2:
                    showTotalRevenue();
                    break;
                case 0:
                    return true;
                default:
                    System.out.println("❌ Opción no válida");
            }
        } catch (EmptyListException e) {
            System.out.println("ℹ️ " + e.getMessage());
        } catch (InvalidAttributeException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Error de base de datos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Debe ingresar un número válido");
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }

        ConsoleUtils.pressEnterToContinue(sc);
        return false;
    }
    private void generateSaleTicket() throws SQLException, EmptyListException, InvalidAttributeException {
        List<Client> clients = clientService.getAllClients();
        System.out.println("\n🎫 GENERAR TICKET DE VENTA");
        System.out.println(clientPrinter.printClientsCompact(clients));
        System.out.print("Inserte el ID del Cliente: ");
        int clientId = Integer.parseInt(sc.nextLine());

        System.out.println(usedInventoryPrinter.printUsedRooms(usedInventory));
        System.out.print("Inserte el ID de la Sala: ");
        int roomId = Integer.parseInt(sc.nextLine());

        Ticket ticket = new Ticket(clientId, roomId);
        ticketService.addTicket(ticket);
    }

    private void showTotalRevenue() throws EmptyListException {
        System.out.println("\n💰 TOTAL DE INGRESOS POR VENTAS");
        System.out.println(salesPrinter.printSalesReport(completeInventoryDTO));
    }

    private int readOption() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

