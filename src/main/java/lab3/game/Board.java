package lab3.game;

public class Board {
    // 2D array to represent the TicTacToe grid
    private final char[][] grid;


    public Board() {
        grid = new char[3][3]; // Create a 3x3 grid
        initializeBoard();
    }


    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' '; // Set each cell to an empty space
            }
        }
    }


    public void placeMark(Position pos, Player player) {
        int row = pos.row().ordinal(); // Get the row index from the Position
        int col = pos.column().ordinal();
        grid[row][col] = player == Player.X ? 'X' : 'O';
    }

    // if a position is already taken
    public boolean isPositionTaken(Position pos) {
        int row = pos.row().ordinal(); // Get the row index from the Position
        int col = pos.column().ordinal();
        return grid[row][col] != ' ';
    }

    // check if the current player has won...
    public boolean isWin(Player player) {
        char mark = player == Player.X ? 'X' : 'O'; // Get the player's mark ('X' or 'O')

        // Check rows for a win
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == mark && grid[i][1] == mark && grid[i][2] == mark) return true; // Check all cells in a row
        }

        // Check columns for a win
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] == mark && grid[1][i] == mark && grid[2][i] == mark) return true; // Check all cells in a column
        }

        // Check diagonals for a win
        if (grid[0][0] == mark && grid[1][1] == mark && grid[2][2] == mark) return true; // Check the main diagonal
        if (grid[0][2] == mark && grid[1][1] == mark && grid[2][0] == mark) return true; // Check the anti-diagonal

        return false; // No win found....
    }

    // check if the game is a draw
    public boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') return false; // If any cell is empty, the game is not a draw
            }
        }
        return true;
    }

    // Override toString() to display the board in a user-friendly format
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(grid[i][j]);
                if (j < 2) sb.append("|"); // Add a vertical separator between columns
            }
            if (i < 2) sb.append("\n-----\n"); // Add a horizontal separator between rows
        }
        return sb.toString();
    }
}