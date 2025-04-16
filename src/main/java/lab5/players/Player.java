package players;

import game.Board;
import game.Position;

public abstract class Player {
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Abstract method to be implemented by subclasses
    public abstract Position pickNextMove(Board board);
}
