package cat.itacademy.dto.availableInventory;

public class AvailableItemDTO extends AvailableEntityDTO {
    private int stock;

    public AvailableItemDTO(String name, int stock) {
        super(name);
        this.stock = stock;
    }

    public int getStock() { return stock; }
}