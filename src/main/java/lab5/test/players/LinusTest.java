package players;

import game.Board;
import game.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinusTest {
    @Test
    void testLinusAlwaysPicksFirstAvailable() {
        Board board = new Board(" XOXO  X ");
        Linus linus = new Linus();
        Position expectedMove = new Position(1, 1); // First available top-left position
        assertEquals(expectedMove, linus.pickNextMove(board));
    }
}
