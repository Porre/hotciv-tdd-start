package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.Utility;

public class EpsilonCivBattle implements BattleStrategy {

    private int redWon, blueWon;

    public EpsilonCivBattle() {
        redWon = 0;
        blueWon = 0;
    }

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
            System.out.println("TEST");
            if (attackOwner.equals(Player.RED)) {
                redWon++;
            } else if (attackOwner.equals(Player.BLUE)) {
                blueWon++;
            }
            return true;
        } else {
            return false;
        }
    }

    public int getWon(Player player) {
        if (player.equals(Player.RED)) {
            return redWon;
        } else if (player.equals(Player.BLUE)) {
            return blueWon;
        } else {
            return 0;
        }
    }
}