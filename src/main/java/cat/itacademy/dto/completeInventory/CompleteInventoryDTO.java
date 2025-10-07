package cat.itacademy.dto.completeInventory;

import cat.itacademy.console.CompleteInventoryPrinter;
import cat.itacademy.dto.availableInventory.AvailableEscapeRoomDTO;

import java.util.List;

public class CompleteInventoryDTO {

    private List<EntityRoomDTO> allRoms;
    private List<EntityClueDTO> allClues;
    private List<EntityItemDTO> allItems;
    private List<EntityTicketDTO> allTickets;
    private List<EntityEscapeRoomDTO> availableEscapeRooms;
    private double totalInventoryPrice;

    public CompleteInventoryDTO(List<EntityEscapeRoomDTO> availableEscapeRooms, List<EntityRoomDTO> allRoms, List<EntityClueDTO> allClues,
                                List<EntityItemDTO> allItems, List<EntityTicketDTO> allTickets, double totalInventoryPrice) {
        this.allRoms = allRoms;
        this.allClues = allClues;
        this.allItems = allItems;
        this.allTickets = allTickets;
        this.availableEscapeRooms = availableEscapeRooms;
        this.totalInventoryPrice = totalInventoryPrice;
    }

    public List<EntityEscapeRoomDTO> getAvailableEscapeRooms() {
        return availableEscapeRooms;
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
}

