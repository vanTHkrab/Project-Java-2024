package Users;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
    private ArrayList<User> users;

    public UserManager() {
        users = new ArrayList<>();
        initializeSampleUsers();  // Initialize with 5 sample users
    }

    // Pre-populate the user list with 5 sample users
    private void initializeSampleUsers() {
        users.add(new User("user1", "password1", "user1@example.com", "111-1111"));
        users.add(new User("user2", "password2", "user2@example.com", "222-2222"));
        users.add(new User("user3", "password3", "user3@example.com", "333-3333"));
        users.add(new User("user4", "password4", "user4@example.com", "444-4444"));
        users.add(new User("user5", "password5", "user5@example.com", "555-5555"));
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
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        // Check if the username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return false;
            }
        }

        User newUser = new User(username, password, email, phone);
        users.add(newUser);
        return true;
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        System.out.println("Invalid username or password.");
        return false;
    }

    // Display all users for testing/debugging purposes
    public void displayUsers() {
        System.out.println("List of Users:");
        for (User user : users) {
            System.out.println(user.getUsername() + " - " + user.getEmail());
        }
    }
}
