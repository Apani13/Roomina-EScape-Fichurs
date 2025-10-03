package cat.itacademy.model;

public class Item {

    private String name;
    private String material;
    private double price;
    private int quantity;

    public Item(String name, String material, int quantity) {
        this.name = name;
        this.material = material;
        this.quantity = quantity;
        this.price = 15.0;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}


