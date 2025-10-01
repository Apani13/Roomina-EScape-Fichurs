package cat.itacademy.model;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private int clientId;
    private int roomId;
    private LocalDateTime dateCreation;
    private double totalPrice;

    public Ticket(int clientId, int roomId, double price) {
        this.clientId = clientId;
        this.roomId = roomId;
        this.totalPrice = price;
    }

    public Ticket(int id, int clientId, int roomId, LocalDateTime dateCreation, double totalPrice) {
        this.id = id;
        this.clientId = clientId;
        this.roomId = roomId;
        this.dateCreation = dateCreation;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public int getRoomId() {
        return roomId;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
