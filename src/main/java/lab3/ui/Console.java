package lab3.ui;

import lab3.game.*;
import java.util.Scanner;

public class Console {
    // Scanner object to read user input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize the game state
        GameState gameState = new GameState();

        // Main game loop
        while (!gameState.isGameOver()) { // Continue until the game is over
            // Display the current state of the board
            displayBoard(gameState.getBoard());

            // Show the current player's turn
            System.out.println("Current Player: " + gameState.getCurrentPlayer());

            // Get the player's move
            Position move = getPlayerMove();

            // Check if the selected position is already taken
            if (gameState.getBoard().isPositionTaken(move)) {
                System.out.println("Position already taken. Try again.");
                continue; // Skip the rest of the loop and prompt for a new move
            }

            // Place the player's mark on the board
            gameState.getBoard().placeMark(move, gameState.getCurrentPlayer());

            // Check if the current player has won
            if (gameState.getBoard().isWin(gameState.getCurrentPlayer())) {
                displayBoard(gameState.getBoard()); // Display the final board
                System.out.println("Congratulations Player " + gameState.getCurrentPlayer() + " wins!");
                return; // End the game
            }

            // Check if the game is a draw
            if (gameState.getBoard().isDraw()) {
                displayBoard(gameState.getBoard()); // Display the final board
                System.out.println("Heyy it's a draw Match!!!");
                return; // End the game
            }

            // Switch to the other player's turn
            gameState.switchPlayer();
        }
    }

    // get the player's move
    public static Position getPlayerMove() {
        while (true) {
            System.out.print("Enter your move (e.g., '1 2' or 't r'): ");
            String input = scanner.nextLine().trim().toLowerCase(); // Read and normalize input
            try {
                String[] parts = input.split(" "); // Split input into row and column
                if (parts.length != 2) throw new IllegalArgumentException();
                return new Position(parseRow(parts[0]), parseColumn(parts[1])); // Parse and return the position
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again."); // Handle invalid input
            }
        }
    }

    // parse the row from user input
    private static Row parseRow(String row) {
        return switch (row) {
            case "1", "t" -> Row.TOP; // Map "1" or "t" to TOP
            case "2", "m" -> Row.MIDDLE; // Map "2" or "m" to MIDDLE
            case "3", "b" -> Row.BOTTOM; // Map "3" or "b" to BOTTOM
            default -> throw new IllegalArgumentException(); // Handle invalid row input
        };
    }

    // the column from user input
    private static Column parseColumn(String col) {
        return switch (col) {
            case "1", "l" -> Column.LEFT; // Map "1" or "l" to LEFT
            case "2", "c" -> Column.MIDDLE; // Map "2" or "c" to MIDDLE
            case "3", "r" -> Column.RIGHT; // Map "3" or "r" to RIGHT
            default -> throw new IllegalArgumentException(); // Handle invalid column input
        };
    }

    // display the current state of the board
    public static void displayBoard(Board board) {
        System.out.println(board);
    }
}