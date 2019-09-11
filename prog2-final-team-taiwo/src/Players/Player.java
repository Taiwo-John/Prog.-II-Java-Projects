package Players;

import Game.Board;

public interface Player {
    public int[] getMove(Board gameBoard);
    public AIBrain learn(boolean win);
}
