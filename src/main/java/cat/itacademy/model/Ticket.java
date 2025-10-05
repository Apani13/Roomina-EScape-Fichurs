package cat.itacademy.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private Integer clientId;
    private Integer roomId;
    private LocalDateTime dateCreation;
    private double totalPrice;

    public Ticket(Integer clientId, Integer roomId) {
        this.clientId = clientId;
        this.roomId = roomId;
    }

    public Ticket(int id, Integer clientId, Integer roomId, LocalDateTime dateCreation, double totalPrice) {
        this.id = id;
        this.clientId = clientId;
        this.roomId = roomId;
        this.dateCreation = dateCreation;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public LocalDate getDateCreationFormat() {
        return this.dateCreation.toLocalDate();
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
