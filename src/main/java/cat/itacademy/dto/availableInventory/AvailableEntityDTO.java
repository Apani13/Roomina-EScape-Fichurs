package cat.itacademy.dto.availableInventory;

public class AvailableEntityDTO {
    private int id;
    private String name;
    private double price;

    public AvailableEntityDTO(int id, String name, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String getName() { return name; }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}

