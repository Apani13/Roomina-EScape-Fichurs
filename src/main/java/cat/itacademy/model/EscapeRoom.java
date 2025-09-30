package cat.itacademy.model;

public class EscapeRoom {

    private int id;
    private String name;

    public EscapeRoom(String name) {
        this.name = name;
    }

    public EscapeRoom(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() {
        return name;
    }
}
