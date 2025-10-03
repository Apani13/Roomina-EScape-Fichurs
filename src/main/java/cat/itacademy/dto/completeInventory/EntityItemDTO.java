package cat.itacademy.dto.completeInventory;

public class EntityItemDTO extends EntityDTO {

    private int quantity;

    public EntityItemDTO(String name, double price, int quantity) {
        super(name, price);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
