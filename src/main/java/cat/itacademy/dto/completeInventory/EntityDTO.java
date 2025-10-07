package cat.itacademy.dto.completeInventory;

public class EntityDTO {
    private int id;
    private double price;

    public EntityDTO(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public double getPrice() {return price;}

    public int getId() {
        return id;
    }
}
