package hotciv.framework;

public interface BattleStrategy {

    /**
     * @param game the game
     * @param attacker position of the attacking unit
     * @param defender position of the defending unit
     * @return true if attacking unit wins, false if defending unit wins
     */
    public boolean getBattleResult(Game game, Position attacker, Position defender);
}
