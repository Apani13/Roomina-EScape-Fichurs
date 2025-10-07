package cat.itacademy.dto.usedInventory;

import java.util.List;

public class UsedInventoryDTO {
    private List<UsedEscapeRoomDTO> usedEscapeRooms;
    private List<UsedRoomDTO> usedRooms;
    private List<UsedClueDTO> usedClues;
    private List<UsedItemDTO> usedItems;
    private int totalUsedItems;

    public UsedInventoryDTO(List<UsedEscapeRoomDTO> usedEscapeRooms, List<UsedRoomDTO> usedRooms,
                            List<UsedClueDTO> usedClues, List<UsedItemDTO> usedItems, int totalUsedItems) {
        this.usedEscapeRooms = usedEscapeRooms;
        this.usedRooms = usedRooms;
        this.usedClues = usedClues;
        this.usedItems = usedItems;
        this.totalUsedItems = totalUsedItems;
    }

    public List<UsedEscapeRoomDTO> getUsedEscapeRooms() { return usedEscapeRooms; }
    public List<UsedRoomDTO> getUsedRooms() { return usedRooms; }
    public List<UsedClueDTO> getUsedClues() { return usedClues; }
    public List<UsedItemDTO> getUsedItems() { return usedItems; }
    public int getTotalUsedItems() { return totalUsedItems; }

    public boolean inventoryIsEmpty() {
        return usedEscapeRooms.isEmpty() && usedRooms.isEmpty() && usedClues.isEmpty() && usedItems.isEmpty();
    }
}
