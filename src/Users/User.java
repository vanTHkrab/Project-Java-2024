package Users;

public class User {
    private final String username;
    private final String password;
    private final String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isMatch(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

}
