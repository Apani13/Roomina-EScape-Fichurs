package cat.itacademy.console;

import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.dto.completeInventory.EntityTicketDTO;

public class SalesPrinter {

    public  String printSalesReport(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INFORME DE VENTAS ===\n");
        sb.append("Tickets vendidos e ingresos generados:\n");

        sb.append("\n--- TICKETS VENDIDOS (").append(inventory.getAllTickets().size()).append(") ---\n");

        if (inventory.getAllTickets().isEmpty()) {
            sb.append("• No hay tickets vendidos\n");
        } else {
            for (EntityTicketDTO ticket : inventory.getAllTickets()) {
                        sb.append(" | Fecha: ").append(ticket.getDate())
                        .append(" | Precio: ").append(ticket.getPrice()).append("€\n");
            }
        }

        sb.append("\n=== RESUMEN DE VENTAS ===\n");
        sb.append("• Total tickets vendidos: ").append(inventory.getAllTickets().size()).append("\n");
        sb.append("• Ingresos totales: ").append(inventory.getTotalValueOfSales()).append(" €\n");

        if (!inventory.getAllTickets().isEmpty()) {
            double promedioVenta = inventory.getTotalValueOfSales() / inventory.getAllTickets().size();
            sb.append("• Ticket promedio: ").append(String.format("%.2f", promedioVenta)).append(" €\n");
        }

        return sb.toString();
    }
}
