package players;

import game.Board;
import game.Position;
import game.PlayerToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OmolaTest {

    @Test
    void testOmolaWinsIfPossible() {
        Board board = new Board("XXXO____O"); // Omola (X) can win by playing at (2,0)
        Omola omola = new Omola();
        Position move = omola.pickNextMove(board);
        assertEquals(new Position(2, 0), move);
    }

    @Test
    void testOmolaBlocksIfOpponentWinsNextTurn() {
        Board board = new Board("XOXO_OX__"); // Opponent (O) can win at (1,2), so Omola should block
        Omola omola = new Omola();
        Position move = omola.pickNextMove(board);
        assertEquals(new Position(1, 2), move);
    }

    @Test
    void testOmolaPicksFirstAvailableIfNoImmediateThreat() {
        Board board = new Board("XOXOXO___"); // No winning/blocking move, Omola should pick (2,0)
        Omola omola = new Omola();
        Position move = omola.pickNextMove(board);
        assertEquals(new Position(2, 0), move);
    }
}
