package cat.itacademy.dto.completeInventory;

public class CompleteElementDTO {
    private String name;
    private double price;

    public CompleteElementDTO(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {return name;}
    public double getPrice() {return price;}
}
