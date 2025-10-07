
package cat.itacademy.dto.usedInventory;

public class UsedClueDTO extends UsedEntityDTO {
    private String theme;
    private int roomId;
    private String roomName;

    public UsedClueDTO(int id, String name, String theme, int roomId, String roomName) {
        super(id, name);
        this.theme = theme;
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public String getTheme() {
        return theme;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }
}

