package cat.itacademy.dto.completeInventory;

import java.util.List;

public class CompleteInventoryDTO {

    private List<AllRoomsDTO> allRoms;
    private List<AllCluesDTO> allClues;
    private List<AllItemsDTO> allItems;
    private double totalInventoryPrice;

    public CompleteInventoryDTO(List<AllRoomsDTO> allRoms, List<AllCluesDTO> allClues,
                                List<AllItemsDTO> allItems, double totalInventoryPrice) {
        this.allRoms = allRoms;
        this.allClues = allClues;
        this.allItems = allItems;
        this.totalInventoryPrice = totalInventoryPrice;
    }

    public List<AllRoomsDTO> getAllRoms() {
        return allRoms;
    }

    public List<AllCluesDTO> getAllClues() {
        return allClues;
    }

    public List<AllItemsDTO> getAllItems() {
        return allItems;
    }

    public double getTotalInventoryPrice() {
        return totalInventoryPrice;
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
        for (AllRoomsDTO room : allRoms) {
            sb.append("• ").append(room.getName()).append(" | Precio: ").append(room.getPrice()).append("\n");
        }

        sb.append("\n--- OBJECTES (").append(allItems.size()).append(") ---\n");
        for (AllItemsDTO item : allItems) {
            sb.append("• ").append(item.getName()).append(" | Precio: ").append(item.getPrice()).append("\n");
        }

        sb.append("\n--- CLUES (").append(allClues.size()).append(") ---\n");
        for (AllCluesDTO clues : allClues) {
            sb.append("• ").append(clues.getName()).append(" | Precio: ").append(clues.getPrice()).append("\n");
        }

        sb.append("\n--- VALOR TOTAL DEL INVENTARIO: ").append(getTotalInventoryPrice()).append(" €.");

        return sb.toString();
    }
}

