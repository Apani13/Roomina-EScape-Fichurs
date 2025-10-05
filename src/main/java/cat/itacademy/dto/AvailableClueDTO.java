package cat.itacademy.dto;

public class AvailableClueDTO extends AvailableElementDTO {
    private String theme;

    public AvailableClueDTO(String name, String theme) {
        super(name);
        this.theme = theme;
    }

    public String getTheme() { return theme; }
}