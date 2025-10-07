package cat.itacademy.dto.completeInventory;

public class EntityRoomDTO extends EntityDTO {

    private String name;
    private String theme;

    public EntityRoomDTO(int id, String name,String theme, double price) {
        super(id,price);
        this.name = name;
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public String getTheme() {
        return theme;
    }
}
