package cat.itacademy.dto.completeInventory;

import cat.itacademy.model.Ticket;

import java.util.List;

public class CompleteInventoryDTO {

    private List<EntityRoomDTO> allRoms;
    private List<EntityClueDTO> allClues;
    private List<EntityItemDTO> allItems;
    private List<EntityTicketDTO> allTickets;
    private double totalInventoryPrice;

    public CompleteInventoryDTO(List<EntityRoomDTO> allRoms, List<EntityClueDTO> allClues,
                                List<EntityItemDTO> allItems, List<EntityTicketDTO> allTickets,  double totalInventoryPrice) {
        this.allRoms = allRoms;
        this.allClues = allClues;
        this.allItems = allItems;
        this.allTickets = allTickets;
        this.totalInventoryPrice = totalInventoryPrice;
    }

    public List<EntityRoomDTO> getAllRoms() {
        return allRoms;
    }

    public List<EntityClueDTO> getAllClues() {
        return allClues;
    }

    public List<EntityItemDTO> getAllItems() {
        return allItems;
    }

    public List<EntityTicketDTO> getAllTickets() {
        return allTickets;
    }

    public double getTotalInventoryPrice() {
        return totalInventoryPrice;
    }

    public double getTotalValueOfSales(){
        double totalValue = 0;

        for (EntityTicketDTO ticket: allTickets) {
            totalValue += ticket.getPrice();
        }
        return totalValue;
    }

    public boolean inventoryIsEmpty() {
        if (getAllRoms().isEmpty() && getAllClues().isEmpty() && getAllItems().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTARIO ACTUALITZADO ===\n");
        sb.append("Precio de cada elemento: \n");

        sb.append("\n--- SALAS (").append(allRoms.size()).append(") ---\n");
        for (EntityRoomDTO room : allRoms) {
            sb.append("• ").append(room.getName()).append(" | Precio: ").append(room.getPrice()).append("\n");
        }

        sb.append("\n--- OBJECTES (").append(allItems.size()).append(") ---\n");
        for (EntityItemDTO item : allItems) {
            sb.append("• ").append(item.getName()).append(" | Precio: ").append(item.getPrice() * item.getQuantity()).append("\n");
        }

        sb.append("\n--- CLUES (").append(allClues.size()).append(") ---\n");
        for (EntityClueDTO clues : allClues) {
            sb.append("• ").append(clues.getName()).append(" | Precio: ").append(clues.getPrice()).append("\n");
        }

        sb.append("\n--- VALOR TOTAL DEL INVENTARIO: ").append(getTotalInventoryPrice()).append(" €.");

        return sb.toString();
    }
}

