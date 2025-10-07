package cat.itacademy.dto.availableInventory;

public class AvailableEscapeRoomDTO {
    private int id;
    private String name;

    public AvailableEscapeRoomDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
