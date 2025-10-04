package cat.itacademy.model;

public class Client {
    private int id;
    private String userName;
    private String email;
    private String phone;
    private boolean acceptsNotifications;

    public Client(String userName, String email, String phone, boolean acceptsNotifications) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.acceptsNotifications = acceptsNotifications;
    }
    public Client(int id, String userName, String email, String phone, boolean acceptsNotifications) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.acceptsNotifications = acceptsNotifications;
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
}
