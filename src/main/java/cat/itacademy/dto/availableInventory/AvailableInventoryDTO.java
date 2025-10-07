package cat.itacademy.dto.availableInventory;

import cat.itacademy.model.EscapeRoom;

import java.util.List;

public class AvailableInventoryDTO {
    private List<AvailableRoomDTO> availableRooms;
    private List<AvailableClueDTO> availableClues;
    private List<AvailableItemDTO> availableItems;
    private List<AvailableEscapeRoomDTO> availableEscapeRooms;
    private int totalItemUnits;

    public AvailableInventoryDTO(List<AvailableEscapeRoomDTO> availableEscapeRooms, List<AvailableRoomDTO> availableRooms, List<AvailableClueDTO> availableClues,
                                 List<AvailableItemDTO> availableItems, int totalItemUnits) {
        this.availableEscapeRooms = availableEscapeRooms;
        this.availableRooms = availableRooms;
        this.availableClues = availableClues;
        this.availableItems = availableItems;
        this.totalItemUnits = totalItemUnits;
    }

    public List<AvailableEscapeRoomDTO> getAvailableEscapeRooms() {
        return availableEscapeRooms;
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
}