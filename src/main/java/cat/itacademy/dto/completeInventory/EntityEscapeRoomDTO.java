package cat.itacademy.dto.completeInventory;

public class EntityEscapeRoomDTO {
    private String name ;
    private int id;

    public EntityEscapeRoomDTO(int id, String name) {
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
