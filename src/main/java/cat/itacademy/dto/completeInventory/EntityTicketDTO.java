package cat.itacademy.dto.completeInventory;

import java.time.LocalDateTime;

public class EntityTicketDTO extends EntityDTO {

    private LocalDateTime date;

    public EntityTicketDTO(int id, LocalDateTime date, double price) {
        super(id,price);
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
