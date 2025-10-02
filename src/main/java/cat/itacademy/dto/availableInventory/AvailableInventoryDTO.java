package cat.itacademy.dto.availableInventory;

import java.util.List;

public class AvailableInventoryDTO {
    private List<AvailableRoomDTO> availableRooms;
    private List<AvailableClueDTO> availableClues;
    private List<AvailableItemDTO> availableItems;
    private int totalItemUnits;

    public AvailableInventoryDTO(List<AvailableRoomDTO> availableRooms, List<AvailableClueDTO> availableClues,
                                 List<AvailableItemDTO> availableItems, int totalItemUnits) {
        this.availableRooms = availableRooms;
        this.availableClues = availableClues;
        this.availableItems = availableItems;
        this.totalItemUnits = totalItemUnits;
    }

    public List<AvailableRoomDTO> getAvailableRooms() { return availableRooms; }
    public List<AvailableClueDTO> getAvailableClues() { return availableClues; }
    public List<AvailableItemDTO> getAvailableItems() { return availableItems; }

    public int getTotalItemsUnits() {
        return totalItemUnits;
    }

    public boolean inventoryIsEmpty() {
        if (getAvailableClues().isEmpty() && getAvailableItems().isEmpty() && getAvailableRooms().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTARI ACTUALITZAT ===\n");
        sb.append("Quantitats disponibles de cada element:\n\n");

        sb.append("--- SALES DISPONIBLES (").append(availableRooms.size()).append(") ---\n");
        for (AvailableRoomDTO room : availableRooms) {
            sb.append("• ").append(room.getName()).append(" | Temàtica: ").append(room.getTheme()).append("\n");
        }

        sb.append("\n--- PISTES DISPONIBLES (").append(availableClues.size()).append(") ---\n");
        for (AvailableClueDTO clue : availableClues) {
            sb.append("• ").append(clue.getName()).append(" | Temàtica: ").append(clue.getTheme()).append("\n");
        }

        sb.append("\n--- OBJECTES DISPONIBLES (").append(getTotalItemsUnits()).append(" unitats) ---\n");
        for (AvailableItemDTO item : availableItems) {
            sb.append("• ").append(item.getName())
                    .append(" | Quantitat: ").append(item.getQuantity()).append("\n");
        }
        return sb.toString();
    }
}