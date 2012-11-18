package hotciv.framework;

public interface WinStrategy {

    /**
     * Returns the player that has won the game.
     * @param game the game object
     * @return the player that is the winner of the game.
     */
    public Player getWinner(Game game);
}
