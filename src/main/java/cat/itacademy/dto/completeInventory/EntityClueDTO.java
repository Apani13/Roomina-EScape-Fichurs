package cat.itacademy.dto.completeInventory;

public class EntityClueDTO extends EntityDTO {

    private String name;

    public EntityClueDTO(String name, double price) {
        super(price);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
