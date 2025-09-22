package cat.itcademy.models;

public class DecorationObject {

    private String name;
    private String material;
    private double price;
    private long quantity;

    public DecorationObject(String name, String material, long quantity) {
        this.name = name;
        this.material = material;
        this.quantity = quantity;
        this.price = 5;

    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public long getQuantity() {
        return quantity;
    }
}

