package players;

import game.Board;
import game.Position;
import ui.Console;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public Position pickNextMove(Board board) {
        return Console.promptForPosition(board);
    }
}
