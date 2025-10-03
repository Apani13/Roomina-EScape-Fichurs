package cat.itacademy.model;

public class Clue {
    private int id;
    private String name;
    private String theme;
    private String description;
    private double price;
    private Integer roomId;

    public Clue(String name, String theme, String description) {
        this.name = name;
        this.theme = theme;
        this.description = description;
        this.price = 10.0;
    }

    public Clue(int id, String name, String theme, String description,  Integer roomId) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.description = description;
        this.price = 10.0;
        this.roomId = roomId;
    }

    public Clue(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

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

    public Integer getRoomId() { return roomId; }
}
