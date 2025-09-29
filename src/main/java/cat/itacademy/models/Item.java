package cat.itacademy.models;

public class Item {

    private String name;
    private String material;
    private double price;
    private long quantity;

    public Item(String name, String material, long quantity) {
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

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return name != null && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}


