package hotciv.variants;

import hotciv.framework.*;

public class AlphaCivBattle implements BattleStrategy {

    public boolean getBattleResult(Game game, Position attacker, Position defender, Die die) {
        Player attackOwner = game.getUnitAt(attacker).getOwner();
        return true;
    }
}