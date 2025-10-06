package cat.itacademy.dto.completeInventory;

public class EntityRoomDTO extends EntityDTO {

    private String name;

    public EntityRoomDTO(String name, double price) {
        super(price);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
