package hotciv.variants;

import hotciv.framework.BattleStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class AlphaCivBattle implements BattleStrategy {

    public boolean getBattleResult(Game game, Position attacker, Position defender) {
        return true;
    }
}
