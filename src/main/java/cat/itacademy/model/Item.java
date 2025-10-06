package cat.itacademy.model;

public class Item {

    private int id;
    private String name;
    private String material;
    private double price;
    private int stock;

    public Item(String name, String material, int stock) {
        this.name = name;
        this.material = material;
        this.stock = stock;
        this.price = 5.0;
    }

    public Item(int id, String name, String material, int stock, double price) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.stock = stock;
        this.price = price;
    }

    public Item(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getMaterial() { return material; }
    public int getStock() { return stock; }
    public double getPrice() { return price; }

    public void setId(int id) { this.id = id; }
}


