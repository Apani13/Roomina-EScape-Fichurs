package cat.itacademy.dto.usedInventory;

public class UsedItemDTO extends UsedEntityDTO {
    private int stock;
    private int roomId;
    private String roomName;
    private int quantityUsed;

    public UsedItemDTO(int id, String name, int stock, int roomId, String roomName, int quantityUsed) {
        super(id, name);
        this.stock = stock;
        this.roomId = roomId;
        this.roomName = roomName;
        this.quantityUsed = quantityUsed;
    }

    public int getStock() { return stock; }
    public int getRoomId() { return roomId; }
    public String getRoomName() { return roomName; }
    public int getQuantityUsed() { return quantityUsed; }
}
