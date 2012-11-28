package hotciv.variants;

import hotciv.framework.*;

public class AlphaCivBattle implements BattleStrategy {

    private int redWon;
    private int blueWon;

    public AlphaCivBattle() {
        redWon = 0;
        blueWon = 0;
    }

    public boolean getBattleResult(Game game, Position attacker, Position defender, Die die) {
        Player attackOwner = game.getUnitAt(attacker).getOwner();
        if (attackOwner.equals(Player.RED)) {
            redWon++;
        } else if (attackOwner.equals(Player.BLUE)) {
            blueWon++;
        }
        return true;
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