package cat.itacademy.dto.availableInventory;

public class AvailableClueDTO extends AvailableEntityDTO {
    private String theme;

    public AvailableClueDTO(int id, String name, double price, String theme) {
        super( id, name, price);
        this.theme = theme;
    }

    public String getTheme() { return theme; }
}