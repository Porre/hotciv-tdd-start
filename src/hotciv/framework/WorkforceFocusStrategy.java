package hotciv.framework;

public interface WorkforceFocusStrategy {

    /**
     * Increases city population by 1
     * @param game the game object
     * @param position position of the city
     */
    public void increaseCitySize(Game game, Position position);

    /**
     * Gets the amount to increase production by pr. round
     * @param game the game object
     * @param position position of the city
     * @return amount to increase production by
     */
    public int getIncreaseOfProduction(Game game, Position position);

    /**
     * Gets the amount to increase food by pr. round
     * @param game the game object
     * @param position position of the city
     * @return amount to increase food by
     */
    public int getIncreaseOfFood(Game game, Position position);
}
