package lab4.ui;

import lab4.game.*;
import com.diogonunes.jcolor.Attribute;  // Import JColor library

import java.util.Scanner;
import static com.diogonunes.jcolor.Ansi.colorize;  // Import JColor utility

/**
 * Helper methods for doing console-based user interaction
 */
public class Console {

    /**
     * Prints a message to the console in the default color.
     * @param message The message to print
     */
    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Prints an error message in red to distinguish it from normal output.
     * @param message The error message to print
     */
    public static void printError(String message) {
        System.out.println(colorize(message, Attribute.RED_TEXT()));
    }

    /**
     * Prints an informational message in green to highlight it.
     * @param message The informational message to print
     */
    public static void printInfo(String message) {
        System.out.println(colorize(message, Attribute.GREEN_TEXT()));
    }

    /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {
        System.out.print(colorize(promptMessage, Attribute.BLUE_TEXT()));  // Prompt message in blue
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Display the given game board with some color enhancement
     * @param board A TicTacToe game board
     */
    public static void showBoard(Board board) {
        printInfo("\nCurrent Game Board:\n");
        System.out.print(board);
    }

    /**
     * Repeatedly prompt the user for a position on which to place their next token.
     * If they enter an invalid response or an already-taken position, they are re-prompted.
     * @param prompt The prompt to display to the user
     * @param board The current state of the game board
     * @return The position selected by the user
     */
    public static Position promptForPosition(String prompt, Board board) {
        var scanner = new Scanner(System.in);
        final String helpMessage = colorize("Input must be in the format 'row column', e.g., '1 2' or 't m' for the top middle cell.", Attribute.YELLOW_TEXT());

        while (true) {
            System.out.print(colorize(prompt, Attribute.BLUE_TEXT()));  // Prompt in blue
            var input = scanner.nextLine().trim();

            if (input.length() != 3) {
                printError(helpMessage);
                continue;
            }

            var parts = input.split(" ");

            if (parts.length != 2) {
                printError(helpMessage);
                continue;
            }

            // The .from methods may throw if the user entered invalid location text, so we try/catch
            try {
                var pos = new Position(Row.from(parts[0]), Col.from(parts[1]));

                if (board.isOccupiedAt(pos)) {
                    printError("Position is already taken.");
                    continue;
                }

                return pos;
            } catch (IllegalArgumentException e) {
                printError(helpMessage);
            }
        }
    }
}
