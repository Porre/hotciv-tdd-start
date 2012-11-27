package hotciv.variants;

import hotciv.framework.BattleStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.Utility;

public class EpsilonCivBattle implements BattleStrategy {

    public boolean getBattleResult(Game game, Position attacker, Position defender) {
        Unit attackUnit = game.getUnitAt(attacker);
        Unit defendUnit = game.getUnitAt(defender);

        int attackingStrength = attackUnit.getAttackingStrength();
        int defendingStrength = game.getUnitAt(defender).getDefensiveStrength();

        attackingStrength += Utility.getFriendlySupport(game, attacker, attackUnit.getOwner());
        defendingStrength += Utility.getFriendlySupport(game, defender, defendUnit.getOwner());

        attackingStrength *= Utility.getTerrainFactor(game, attacker);
        defendingStrength *= Utility.getTerrainFactor(game, defender);

        if (attackingStrength * roll() > defendingStrength * roll()) {
            return true;
        } else {
            return false;
        }
    }

    private int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}