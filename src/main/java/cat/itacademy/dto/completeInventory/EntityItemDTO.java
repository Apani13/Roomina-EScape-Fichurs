package cat.itacademy.dto.completeInventory;

public class EntityItemDTO extends EntityDTO {

    private String name;
    private int quantity;

    public EntityItemDTO( int id, String name, double price, int quantity) {
        super(id, price);
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
