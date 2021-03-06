package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.Utility;

public class EpsilonCivBattle implements BattleStrategy {

    public boolean getBattleResult(Game game, Position attacker, Position defender, Die die) {
        Unit attackUnit = game.getUnitAt(attacker);
        Unit defendUnit = game.getUnitAt(defender);

        Player attackOwner = attackUnit.getOwner();

        int attackingStrength = attackUnit.getAttackingStrength();
        int defendingStrength = game.getUnitAt(defender).getDefensiveStrength();

        attackingStrength += Utility.getFriendlySupport(game, attacker, attackUnit.getOwner());
        defendingStrength += Utility.getFriendlySupport(game, defender, defendUnit.getOwner());

        attackingStrength *= Utility.getTerrainFactor(game, attacker);
        defendingStrength *= Utility.getTerrainFactor(game, defender);

        if (attackingStrength * die.rollDie() > defendingStrength * die.rollDie()) {
            if (attackOwner.equals(Player.RED)) {

            } else if (attackOwner.equals(Player.BLUE)) {

            }
            return true;
        } else {
            return false;
        }
    }
}