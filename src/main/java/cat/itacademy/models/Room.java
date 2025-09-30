package cat.itacademy.models;

public class Room {
    private int id;
    private String name;
    private String theme;
    private int level;
    private double price;
    private int escapeRoomId;

    public Room(String name, String theme, int level) {
        this.name = name;
        this.theme = theme;
        this.level = level;
        this.price = 25.0;
    }

    public Room(int id, String name, String theme, int level,  double price, int escapeRoomId) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.level = level;
        this.price = price;
        this.escapeRoomId = escapeRoomId;
    }

    public Room(int id, String name){
        this.id = id;
        this.name = name;
    }


    public int getId() {return id;}

    public String getName() {
        return name;
    }

    public String getTheme() {
        return theme;
    }

    public int getLevel() {
        return level;
    }

    public double getPrice() {
        return price;
    }

    public int getEscapeRoomId() {
        return escapeRoomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room that = (Room) o;
        return name != null && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
