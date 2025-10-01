package cat.itacademy.dto;

public class AvailableRoomDTO extends AvailableElementDTO {
    private String theme;

    public AvailableRoomDTO(String name, String theme) {
        super(name);
        this.theme = theme;
    }

    public String getTheme() { return theme; }
}