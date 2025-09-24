package cat.itacademy.models;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecorationObject that = (DecorationObject) o;
        return name != null && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}


