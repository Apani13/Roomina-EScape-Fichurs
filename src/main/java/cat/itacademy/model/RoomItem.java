package cat.itacademy.model;

public class RoomItem {

    private Integer roomId;
    private Integer itemId;
    private int quantity;

    public RoomItem(Integer roomId, Integer itemId, int quantity) {
        this.roomId = roomId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Integer getRoomId() {
        return roomId;
    }
    public Integer getItemId() {
        return itemId;
    }
    public int getQuantity() {
        return quantity;
    }
}
