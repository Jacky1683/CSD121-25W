package lab6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Button[][] buttons = new Button[3][3];
    private boolean playerX = true;
    private Label statusLabel = new Label("Player X's Turn");

    @Override
    public void start(Stage primaryStage) {
        // Create a grid layout for the Tic Tac Toe board
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        // Initialize buttons for the game board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button(" ");
                button.setMinSize(100, 100); // Increase button size for better visibility
                button.setStyle("-fx-font-size: 30px; -fx-alignment: center;"); // Center align text
                final int r = row, c = col;
                button.setOnAction(e -> handleMove(r, c));
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }

        // Reset button to restart the game
        Button resetButton = new Button("Reset Game");
        resetButton.setOnAction(e -> resetGame());

        // Layout containing the status label, game grid, and reset button
        VBox layout = new VBox(15, statusLabel, grid, resetButton);
        layout.setAlignment(Pos.CENTER);

        // Set up and display the primary stage
        primaryStage.setScene(new Scene(layout, 400, 500)); // Increased window size
        primaryStage.setTitle("Lab6: Tic Tac Toe");
        primaryStage.show();
    }

    // Handles a player's move when a button is clicked
    private void handleMove(int row, int col) {
        if (!buttons[row][col].getText().equals(" ")) return;

        buttons[row][col].setText(playerX ? "X" : "O");
        if (checkWin()) {
            showWinMessage(playerX ? "X" : "O");
        } else if (isBoardFull()) {
            showDrawMessage();
        } else {
            playerX = !playerX;
            statusLabel.setText("Player " + (playerX ? "X" : "O") + "'s Turn");
        }
    }

    // Checks if there is a winning condition on the board
    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals(" ")) return true;

            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().equals(" ")) return true;
        }
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals(" ")) return true;

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals(" ")) return true;

        return false;
    }

    // Checks if the board is full (draw condition)
    private boolean isBoardFull() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                if (button.getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Displays a message when a player wins
    private void showWinMessage(String winner) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations..!!" + "  Player " + winner + " Wins.");
        alert.showAndWait();
        resetGame();
    }

    // Displays a message when the game ends in a draw
    private void showDrawMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("It's a Draw Match...   Play Again...!!");
        alert.showAndWait();
        resetGame();
    }

    // Resets the game to its initial state
    private void resetGame() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setText(" ");
                button.setDisable(false);
            }
        }
        playerX = true;
        statusLabel.setText("Player X's Turn");
    }

    // Disables the board when a game has ended
    private void disableBoard() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setDisable(true);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
