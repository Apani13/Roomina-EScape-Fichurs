package cat.itacademy.models;

public class Clue {

    private String name;
    private String theme;
    private String description;
    private double price;

    public Clue(String name, String theme, String description, double price) {
        this.name = name;
        this.theme = theme;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getTheme() {
        return theme;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

}
