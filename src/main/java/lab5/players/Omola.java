package players;

import game.Board;
import game.Position;
import game.PlayerToken;

import java.util.List;

public class Omola extends Player {

    public Omola() {
        super("Omola"); // Set the name for Omola AI
    }

    @Override
    public Position pickNextMove(Board board) {
        List<Position> emptyCells = board.getEmptyCells();
        PlayerToken myToken = board.getNextTurnToken();
        PlayerToken opponentToken = myToken.opponent();

        // Step 1: Check if Omola can win in one move
        for (Position pos : emptyCells) {
            Board copy = new Board(board);
            copy.play(pos, myToken);
            if (copy.getStatus().isWin()) {
                return pos; // Play winning move
            }
        }

        // Step 2: Check if the opponent can win next turn and block them
        for (Position pos : emptyCells) {
            Board copy = new Board(board);
            copy.play(pos, opponentToken);
            if (copy.getStatus().isWin()) {
                return pos; // Block opponent's winning move
            }
        }

        // Step 3: Otherwise, pick the first available position
        return emptyCells.get(0);
    }
}
