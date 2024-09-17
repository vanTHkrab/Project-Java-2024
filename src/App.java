import Games.Simon.SimonGame;
import Users.UserManager;
import Games.GamesManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager userManager = new UserManager();
        GamesManager gamesManager = new GamesManager();
        boolean isLoop = true;
        playSound();

        while (isLoop) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    int index = userManager.login();
                    if (index != -1) {
                        System.out.println("Login successful!\nWelcome to the Game Console.");
                        System.out.println("Hello, " + userManager.getUser(index).getUsername());
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
                    isLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        sc.close();
    }

    public static void playSound() {
        try {
            URL soundURL = SimonGame.class.getResource("/assets/sound/wax-background.wav"); // Load the sound file
            assert soundURL != null; // Check if the sound file exists
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundURL); // Create an audio input stream
            Clip clip = AudioSystem.getClip(); // Create a clip
            clip.open(audioInput); // Open the clip
            clip.start(); // Start the clip
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the clip
        } catch (Exception e) {
            System.out.println("Error playing sound.");
            e.printStackTrace();
        }
    }
}
