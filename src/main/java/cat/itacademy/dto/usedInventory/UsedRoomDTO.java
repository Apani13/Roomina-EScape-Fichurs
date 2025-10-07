package cat.itacademy.dto.usedInventory;

public class UsedRoomDTO extends UsedEntityDTO {
    private String theme;
    private int escapeRoomId;
    private String escapeRoomName;

    public UsedRoomDTO(int id, String name, String theme, int escapeRoomId, String escapeRoomName) {
        super(id, name);
        this.theme = theme;
        this.escapeRoomId = escapeRoomId;
        this.escapeRoomName = escapeRoomName;
    }

    public String getTheme() {
        return theme;
    }

    public int getEscapeRoomId() {
        return escapeRoomId;
    }

    public String getEscapeRoomName() {
        return escapeRoomName;
    }
}

