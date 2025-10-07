package cat.itacademy.dto.usedInventory;

public class UsedEscapeRoomDTO extends UsedEntityDTO {
    private int totalRooms;
    private int totalTicketsSold;

    public UsedEscapeRoomDTO(int id, String name, int totalRooms, int totalTicketsSold) {
        super(id, name);
        this.totalRooms = totalRooms;
        this.totalTicketsSold = totalTicketsSold;
    }

    public int getTotalRooms() { return totalRooms; }
    public int getTotalTicketsSold() { return totalTicketsSold; }
}
