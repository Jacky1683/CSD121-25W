package lab5;

import java.util.Scanner;
import lab5.players.HumanPlayer;
import lab5.players.Linus;
import lab5.players.Omola;
import lab5.players.Player;
import lab5.game.PlayerToken;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static Player promptForPlayer(PlayerToken token) {
        System.out.print("Enter 'H' for human player, 'L' for Linus, or 'O' for Omola: ");
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "L":
                return new Linus();
            case "O":
                return new Omola();
            case "H":
            default:
                System.out.print("Enter player name: ");
                String name = scanner.nextLine();
                return new HumanPlayer(name);
        }
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void showBoard(lab5.game.Board board) {
        System.out.println(board);
    }
}
