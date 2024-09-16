package Games;

import Games.TicTacToe.*;
import Games.WordMatch.*;
import Games.Hangman.*;
import Games.RockPaperScissors.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GamesManager {
    private final ArrayList<String> gameList;

    public GamesManager() {
        gameList = new ArrayList<>();
        initializeGames();
    }

    // Add available games to the list
    private void initializeGames() {
        gameList.add("Tic-Tac-Toe");
        gameList.add("Word Match");
        gameList.add("Hangman");
        gameList.add("Rock Paper Scissors");
    }

    // Display available games
    public void displayAvailableGames() {
        System.out.println("Available Games:");
        for (int i = 0; i < gameList.size(); i++) {
            System.out.println((i + 1) + ". " + gameList.get(i));
        }
    }

    // Start the game selected by the user
    public void startGame(int choice) {
        if (choice > 0 && choice <= gameList.size()) {
            String selectedGame = gameList.get(choice - 1);
            System.out.println("Starting " + selectedGame + "...");

            switch (selectedGame) {
                case "Tic-Tac-Toe":
                    TicTacToeGame ticTacToe = new TicTacToeGame();
                    ticTacToe.start();
                    break;
                case "Word Match":
                    WordGuessingGame wordMatch = new WordGuessingGame();
                    wordMatch.start();
                    break;
                case "Hangman":
                    HangmanGameMain hangman = new HangmanGameMain();
                    hangman.start();
                    break;
                case "Rock Paper Scissors":
                    ROck rockPaperScissors = new ROck();
                    rockPaperScissors.start();
                    break;
                default:
                    System.out.println("Invalid game choice.");
                    break;
            }
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
