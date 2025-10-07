package cat.itacademy.dto.availableInventory;

public class AvailableItemDTO extends AvailableEntityDTO {
    private int stock;

    public AvailableItemDTO(int id, String name, double price, int stock) {
        super(id, name, price);
        this.stock = stock;
    }

    public int getStock() { return stock; }
}