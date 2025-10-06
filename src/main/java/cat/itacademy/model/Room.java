package cat.itacademy.model;

import java.util.Optional;

public class Room {
    private Integer id;
    private String name;
    private String theme;
    private int level;
    private double price;
    private Integer escapeRoomId;

    public Room(Integer id, String name, String theme, int level, double price, Integer escapeRoomId) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.level = level;
        this.price = price;
        this.escapeRoomId = escapeRoomId;
    }


    public Room(String name, String theme, int level) {
        this.name = name;
        this.theme = theme;
        this.level = level;
        this.price = 25.0;
    }

    public Room(String name, String theme) {
        this.name = name;
        this.theme = theme;
        this.price = 25.0;
    }

    public Room(int id, String name, String theme, int level, Integer escapeRoomId) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.level = level;
        this.price = 25.0;
        this.escapeRoomId = escapeRoomId;
    }

    public Room(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Room(String name) {
        this.name = name;
        this.price = 25.0;
    }


    public Integer getId() {return id;}

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

    public Integer getEscapeRoomId() {
        return escapeRoomId;
    }

    public Optional<Integer> getEscapeRoomIdOpt() {
        return Optional.ofNullable(escapeRoomId);
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
