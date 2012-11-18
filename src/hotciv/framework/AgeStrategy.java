package hotciv.framework;

public interface AgeStrategy {

    /**
     * Gets the current age that the game is in.
     * @return an integer specifying the current age of the game.
     */
    public int getAge();

    /**
     * Progresses the game age.
     */
    public void progressAge();
}