package cat.itacademy.dto.availableInventory;

public class AvailableClueDTO extends AvailableEntityDTO {
    private String theme;

    public AvailableClueDTO(String name, String theme) {
        super(name);
        this.theme = theme;
    }

    public String getTheme() { return theme; }
}