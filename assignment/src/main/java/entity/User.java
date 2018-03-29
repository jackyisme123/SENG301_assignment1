package entity;

public class User {
    private String firstName;
    private String lastName;
    private String ownerId;
    private String password;

    public User() {
        this.firstName = null;
        this.lastName = null;
        this.ownerId = null;
        this.password = null;
    }

    public User(String firstName, String lastName, String ownerId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ownerId = ownerId;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
