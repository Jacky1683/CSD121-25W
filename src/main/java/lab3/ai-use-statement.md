# AI Use Statement


# AI Conversations

i used ChatGpt for small error , here i attached chat with AI.


package lab3.ui;

import lab3.game.*;
import java.util.Scanner;

public class Console {
private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GameState gameState = new GameState();
        while (!gameState.GameOver()
            displayBoard(gameState.getBoard());
            System.out.println("Current Player: " + gameState.getCurrentPlayer());
            Position move = getPlayerMove();
            if (gameState.getBoard().isPositionTaken(move)) {
                System.out.println("Position already taken. Try again.");
                continue;
            }
            gameState.getBoard().placeMark(move, gameState.getCurrentPlayer());
            if (gameState.getBoard().isWin(gameState.getCurrentPlayer())) {
                displayBoard(gameState.getBoard());
                System.out.println("Congratulations Player " + gameState.getCurrentPlayer() + " wins!");
                return;
            }
            if (gameState.getBoard().isDraw()) {
                displayBoard(gameState.getBoard());
                System.out.println("Heyy it's a draw Match!!!");
                return;
            }
            gameState.switchPlayer();
        }
    }

    public static Position getPlayerMove() {
        while (true) {
            System.out.print("Enter your move (e.g., '1 2' or 't r'): ");
            String input = scanner.nextLine().trim().toLowerCase();
            try {
                String[] parts = input.split(" ");
                if (parts.length != 2) throw new IllegalArgumentException();
                return new Position(parseRow(parts[0]), parseColumn(parts[1]));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static Row parseRow(String row) {
        return switch (row) {
            case "1", "t" -> Row.TOP;
            case "2", "m" -> Row.MIDDLE;
            case "3", "b" -> Row.BOTTOM;
            default -> throw new IllegalArgumentException();
        };
    }

    private static Column parseColumn(String col) {
        return switch (col) {
            case "1", "l" -> Column.LEFT;
            case "2", "c" -> Column.MIDDLE;
            case "3", "r" -> Column.RIGHT;
            default -> throw new IllegalArgumentException();
        };
    }

    public static void displayBoard(Board board) {
        System.out.println(board);
    }
}


i created tic tac toe game but i got small error so give me solution of this code.
ChatGPT said:
It looks like there's a small syntax issue in the while loop of the main method. Specifically, there is a missing closing parenthesis ) after the gameState.GameOver() method call. Additionally, make sure the GameOver method in the GameState class is properly defined (it should be isGameOver based on your earlier code).

Here's the corrected code:

java
Copy
Edit
package lab3.ui;

import lab3.game.*;
import java.util.Scanner;

public class Console {
private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GameState gameState = new GameState();
        while (!gameState.isGameOver()) { // Corrected method name and added closing parenthesis
            displayBoard(gameState.getBoard());
            System.out.println("Current Player: " + gameState.getCurrentPlayer());
            Position move = getPlayerMove();
            if (gameState.getBoard().isPositionTaken(move)) {
                System.out.println("Position already taken. Try again.");
                continue;
            }
            gameState.getBoard().placeMark(move, gameState.getCurrentPlayer());
            if (gameState.getBoard().isWin(gameState.getCurrentPlayer())) {
                displayBoard(gameState.getBoard());
                System.out.println("Congratulations Player " + gameState.getCurrentPlayer() + " wins!");
                return;
            }
            if (gameState.getBoard().isDraw()) {
                displayBoard(gameState.getBoard());
                System.out.println("Heyy it's a draw Match!!!");
                return;
            }
            gameState.switchPlayer();
        }
    }

    public static Position getPlayerMove() {
        while (true) {
            System.out.print("Enter your move (e.g., '1 2' or 't r'): ");
            String input = scanner.nextLine().trim().toLowerCase();
            try {
                String[] parts = input.split(" ");
                if (parts.length != 2) throw new IllegalArgumentException();
                return new Position(parseRow(parts[0]), parseColumn(parts[1]));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static Row parseRow(String row) {
        return switch (row) {
            case "1", "t" -> Row.TOP;
            case "2", "m" -> Row.MIDDLE;
            case "3", "b" -> Row.BOTTOM;
            default -> throw new IllegalArgumentException();
        };
    }

    private static Column parseColumn(String col) {
        return switch (col) {
            case "1", "l" -> Column.LEFT;
            case "2", "c" -> Column.MIDDLE;
            case "3", "r" -> Column.RIGHT;
            default -> throw new IllegalArgumentException();
        };
    }

    public static void displayBoard(Board board) {
        System.out.println(board);
    }
}
Summary of Fix:
Fixed Method Call: The while loop's condition was calling gameState.GameOver(), but it should be gameState.isGameOver(). This should resolve the syntax error.

Ensure Consistency: Ensure that you have the correct method names and they align with the rest of your codebase (for example, isGameOver() vs. GameOver()).

Now, the code should run correctly assuming your GameState class and other dependencies are correctly implemented. Let me know if you need further clarification!