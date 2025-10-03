package cat.itacademy.model;

import cat.itacademy.observer.Observer;

import java.util.Objects;

public class Client implements Observer {
    private int id;
    private String userName;
    private String email;
    private String phone;
    private boolean acceptsNotifications;

    public Client(String userName, String email, String phone) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.acceptsNotifications = true;
    }

    public Client(String userName) {
        this.userName = userName;
        this.email = "";
        this.phone = "";
        this.acceptsNotifications = false;
    }

    public Client(int id, String userName, String email, String phone, boolean acceptsNotifications) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.acceptsNotifications = acceptsNotifications;
    }

    @Override
    public void update(String message) {
        if (acceptsNotifications) {
            System.out.println("📩 Notificación para " + getUserName() + ": " + message);
        }
    }

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isAcceptsNotifications() {
        return acceptsNotifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(int id) {
        this.id = id;
    }
}
