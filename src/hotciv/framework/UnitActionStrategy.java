package hotciv.framework;

public interface UnitActionStrategy {

    /**
     * @param game the game object
     * @param position position of the unit that is to make the action
     */
    public void unitAction(Game game, Position position);
}
