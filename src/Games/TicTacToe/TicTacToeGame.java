package Games.TicTacToe;

import java.util.Scanner;

public class TicTacToeGame {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        boolean play = true;

        // Welcome message
        System.out.println("Welcome to Tic-Tac-Toe!");
        game.printBoard();

        while (play) {
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
    }
}
