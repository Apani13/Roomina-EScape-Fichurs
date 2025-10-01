package cat.itacademy.dto;

public class AvailableItemDTO extends AvailableElementDTO {
    private int quantity;

    public AvailableItemDTO(String name, int quantity) {
        super(name);
        this.quantity = quantity;
    }

    public int getQuantity() { return quantity; }
}