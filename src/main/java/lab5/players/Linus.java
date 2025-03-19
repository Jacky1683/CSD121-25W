package players;

import game.Board;
import game.Position;
import java.util.List;

public class Linus extends Player {
    public Linus() {
        super("Linus");
    }

    @Override
    public Position pickNextMove(Board board) {
        List<Position> emptyCells = board.getEmptyCells();
        return emptyCells.isEmpty() ? null : emptyCells.get(0); // Picks the first available cell
    }
}
