import Users.UserManager;
import Games.GamesManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        GamesManager gamesManager = new GamesManager();
        boolean running = true;

        while (running) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                String input = scanner.nextLine();  // Use nextLine to handle input safely
                int choice = Integer.parseInt(input.trim());  // Trim to avoid extra spaces

                switch (choice) {
                    case 1:
                        if (userManager.login()) {
                            System.out.println("Login successful!");
                            gamesManager.play(); // Start the game selection after login
                        } else {
                            System.out.println("Login failed!");
                        }
                        break;
                    case 2:
                        if (userManager.register()) {
                            System.out.println("Registration successful!");
                        } else {
                            System.out.println("Registration failed!");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting the system.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
