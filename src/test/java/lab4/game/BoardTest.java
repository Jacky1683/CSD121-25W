package lab4.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testInitialBoardStatus() {
        Board board = new Board();
        assertEquals(Board.Status.InProgress, board.getStatus());
    }

    @Test
    void testPlacingTokens() {
        Board board = new Board();
        Position pos1 = new Position(Row.Top, Col.Left);
        Position pos2 = new Position(Row.Middle, Col.Middle);

        board.placeX(pos1);
        board.placeO(pos2);

        assertTrue(board.isOccupiedAt(pos1));
        assertTrue(board.isOccupiedAt(pos2));
        assertFalse(board.isOccupiedAt(new Position(Row.Bottom, Col.Right)));
    }

    @Test
    void testWinConditionRows() {
        Board board = new Board();
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeX(new Position(Row.Top, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Right));

        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @Test
    void testWinConditionColumns() {
        Board board = new Board();
        board.placeO(new Position(Row.Top, Col.Left));
        board.placeO(new Position(Row.Middle, Col.Left));
        board.placeO(new Position(Row.Bottom, Col.Left));

        assertEquals(Board.Status.OWins, board.getStatus());
    }

    @Test
    void testWinConditionDiagonal() {
        Board board = new Board();
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeX(new Position(Row.Middle, Col.Middle));
        board.placeX(new Position(Row.Bottom, Col.Right));

        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @Test
    void testDrawCondition() {
        Board board = new Board();

        // Fill board with no winner
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeO(new Position(Row.Top, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Right));

        board.placeX(new Position(Row.Middle, Col.Left));
        board.placeO(new Position(Row.Middle, Col.Middle));
        board.placeX(new Position(Row.Middle, Col.Right));

        board.placeO(new Position(Row.Bottom, Col.Left));
        board.placeX(new Position(Row.Bottom, Col.Middle));
        board.placeO(new Position(Row.Bottom, Col.Right));

        assertEquals(Board.Status.Draw, board.getStatus());
    }

    @Test
    void testBoardFull() {
        Board board = new Board();

        // Fill all positions
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeO(new Position(Row.Top, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Right));

        board.placeX(new Position(Row.Middle, Col.Left));
        board.placeO(new Position(Row.Middle, Col.Middle));
        board.placeX(new Position(Row.Middle, Col.Right));

        board.placeO(new Position(Row.Bottom, Col.Left));
        board.placeX(new Position(Row.Bottom, Col.Middle));
        board.placeO(new Position(Row.Bottom, Col.Right));

        assertTrue(board.isFull());
    }
}
