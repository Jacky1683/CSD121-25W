package lab3.game;

public class GameState {
    private final Board board;
    private Player currentPlayer;

    public GameState() {
        board = new Board();
        currentPlayer = Player.X;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    }

    public boolean isGameOver() {
        return board.isWin(Player.X) || board.isWin(Player.O) || board.isDraw();
    }
}