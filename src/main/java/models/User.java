package models;

public class User {
    private final String gmail;
    private final String username;
    private final String password;

    public User(String gmail, String username, String password) {
        this.gmail = gmail;
        this.username = username;
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
