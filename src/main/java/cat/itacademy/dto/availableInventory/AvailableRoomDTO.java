package cat.itacademy.dto.availableInventory;

public class AvailableRoomDTO extends AvailableEntityDTO {
    private String theme;

    public AvailableRoomDTO(int id, String name, double price, String theme) {
        super(id, name, price);
        this.theme = theme;
    }

    public String getTheme() { return theme; }
}