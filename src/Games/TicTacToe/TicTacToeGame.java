package Games.TicTacToe;
import Games.Game;
import java.util.Scanner;

public class TicTacToeGame implements Game {
    public String getName() {
        return "Tic-Tac-Toe";
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        boolean play = true;
        boolean againstBot = false;  // Flag to indicate if playing against the bot

        // Welcome message and game mode selection
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Do you want to play against the bot? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("y")) {
            againstBot = true;
        }

        game.printBoard();

        while (play) {
            if (game.getCurrentPlayer() == 'X' || !againstBot) {
                // Player's turn
                System.out.println("Player " + game.getCurrentPlayer() + "'s turn. Enter row and column (0-2): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                // Check if the position is valid
                if (game.placeMark(row, col)) {
                    game.printBoard();

                    // Check for win or draw
                    if (game.checkForWin()) {
                        System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                        play = false;
                    } else if (game.isBoardFull()) {
                        System.out.println("The game is a tie!");
                        play = false;
                    } else {
                        // Switch player
                        game.changePlayer();
                    }
                } else {
                    System.out.println("This position is already filled. Try again.");
                }
            }

            // Bot's turn if playing against the bot
            if (againstBot && game.getCurrentPlayer() == 'O' && play) {
                game.botMove();
                game.printBoard();

                // Check for win or draw
                if (game.checkForWin()) {
                    System.out.println("Bot wins!");
                    play = false;
                } else if (game.isBoardFull()) {
                    System.out.println("The game is a tie!");
                    play = false;
                } else {
                    // Switch player back to X
                    game.changePlayer();
                }
            }
        }
    }
}
