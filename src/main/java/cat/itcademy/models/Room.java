package cat.itcademy.models;

public class Room {

    private String name;
    private String theme;
    private int level;
    private double price;


    public Room(String name, String theme, int level) {
        this.name = name;
        this.theme = theme;
        this.level = level;
        this.price = 25.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
