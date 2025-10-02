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

    }
}
