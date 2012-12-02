package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;
import hotciv.standard.GameImpl;

public class EpsilonCivWin implements WinStrategy {

    private int redAttacksWon;
    private int blueAttacksWon;

    public EpsilonCivWin() {
        redAttacksWon = 0;
        blueAttacksWon = 0;
    }

    public Player getWinner(Game game) {
        if (redAttacksWon >= 3) {
            return Player.RED;
        } else if (blueAttacksWon >= 3) {
            return Player.BLUE;
        } else {
            return null;
        }
    }

    public void increaseAttacksWon(Player player) {
        if (player.equals(Player.RED)) {
            redAttacksWon++;
        } else if (player.equals(Player.BLUE)) {
            blueAttacksWon++;
        }
    }
}