package cat.itacademy.presentation;

import cat.itacademy.console.ConsoleUtils;
import cat.itacademy.console.SalesPrinter;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.model.Ticket;
import cat.itacademy.service.ClientService;
import cat.itacademy.service.InventoryService;
import cat.itacademy.service.TicketService;

import java.sql.SQLException;
import java.util.Scanner;

public class SalesTicketsMenu {
    private Scanner sc;
    private TicketService ticketService;
    private ClientService clientService;
    private CompleteInventoryDTO completeInventoryDTO;
    private InventoryService inventoryService;
    private SalesPrinter salesPrinter;

    public SalesTicketsMenu(Scanner sc) throws SQLException {
        this.sc = sc;
        this.inventoryService = new InventoryService();
        this.ticketService = new TicketService();
        this.clientService = new ClientService();
        this.salesPrinter = new SalesPrinter();
        this.completeInventoryDTO = inventoryService.getCompleteInventory();
    }

    public void showSalesTicketsMenu() throws SQLException {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("🎫 GESTIÓN DE VENTAS Y TICKETS");
            System.out.println("1. Generar Ticket de Venta");
            System.out.println("2. Mostrar Total de Ingresos por Ventas");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");

            int option = readOption();
            backToMain = processSalesTicketsOption(option);
        }
    }

    private boolean processSalesTicketsOption(int option) throws SQLException {
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
        ConsoleUtils.pressEnterToContinue(sc);
        return false;
    }

    private void generateSaleTicket() throws SQLException {
            System.out.println("\n🎫 GENERAR TICKET DE VENTA");
            System.out.print("Inserte el ID del Cliente: ");
            System.out.println(clientService.getAllClients());
            int clientId = Integer.parseInt(sc.nextLine());

            System.out.print("Inserte el ID de la Sala: ");
            System.out.println(completeInventoryDTO.getAllRoms());
            ;
            int roomId = Integer.parseInt(sc.nextLine());
            Ticket ticket = new Ticket(clientId, roomId);

            ticketService.addTicket(ticket);
    }

    private void showTotalRevenue() {
        System.out.println("\n💰 TOTAL DE INGRESOS POR VENTAS");
        salesPrinter.printSalesReport(completeInventoryDTO);
    }

    private int readOption() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

