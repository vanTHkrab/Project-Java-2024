package Users;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
    private final ArrayList<User> users;

    public UserManager() {
        users = new ArrayList<>();
        setUsers();  // Initialize with 5 sample users
    }

    // Pre-populate the user list with 5 sample users
    private void setUsers() {
        users.add(new User("user1", "password1", "user1@exe.com"));
        users.add(new User("user2", "password2", "user2@exe.com"));
        users.add(new User("user3", "password3", "user3@exe.com"));
        users.add(new User("user4", "password4", "user4@exe.com"));
        users.add(new User("user5", "password5", "user5@exe.com"));

    }

    public boolean register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Register a new account.");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        // Check if the username already exists
        for (User user : users) {
            if (user.getUsername().equals(username) || user.getEmail().equals(email)) {
                System.out.println("Username already exists.");
                return false;
            }
        }

        User newUser = new User(username, password, email);
        users.add(newUser);
        return true;
    }

    public int login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.isMatch(username, password)) {
                return i; // Return the index of the user
            }
        }

        System.out.println("Invalid username or password.");
        return -1;
    }

    // Get a specific user by index
    public User getUser(int index) {
        return users.get(index);
    }
}
