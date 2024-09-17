package Games;

import Games.Hangman.HangmanGame;
import Games.TicTacToe.TicTacToeGame;
import Games.WordMatch.WordGuessingGame;
import Games.RockPaperScissors.RockGame;
import Games.Simon.SimonGame;

import java.util.ArrayList;
import java.util.Scanner;

public class GamesManager {
    private final ArrayList<Game> gameList = new ArrayList<>();

    public GamesManager() {
        setGameList();
    }
    // Add available games to the list
    private void setGameList() {
        gameList.add(new TicTacToeGame());
        gameList.add(new WordGuessingGame());
        gameList.add(new HangmanGame());
        gameList.add(new RockGame());
        gameList.add(new SimonGame());

    }

    // Display available games
    public void displayAvailableGames() {
        System.out.println("Available Games:");
        for (int i = 0; i < gameList.size(); i++) {
            System.out.println((i + 1) + ". " + gameList.get(i).getName());
        }
    }

    // Start the game selected by the user
    public void startGame(int choice) {
        if (choice > 0 && choice <= gameList.size()) {
            Game selectedGame = gameList.get(choice - 1);
            selectedGame.start();  // Call the start method for the selected game
        } else {
            System.out.println("Invalid choice. Please select a valid game.");
        }
    }

    // Main interaction for the user to select a game
    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        while (playing) {
            displayAvailableGames();
            System.out.print("Enter the number of the game you want to play (0 to quit): ");
            try {
                String input = scanner.nextLine(); // Use nextLine for consistent input handling
                int choice = Integer.parseInt(input.trim()); // Parse input safely

                if (choice == 0) {
                    System.out.println("Exiting game selection.");
                    playing = false;
                } else {
                    startGame(choice);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}



