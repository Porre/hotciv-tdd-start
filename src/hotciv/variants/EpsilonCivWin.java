package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.UnitWinCount;
import hotciv.framework.WinStrategy;
import hotciv.standard.GameImpl;

public class EpsilonCivWin implements WinStrategy, UnitWinCount {

    private int redAttacksWon;
    private int blueAttacksWon;
    private GameImpl g;

    public EpsilonCivWin(Game game) {
        redAttacksWon = 0;
        blueAttacksWon = 0;
        g = (GameImpl) game;
        g.registerWinStrategy(this);
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

    public void increaseWins(Player player) {
        if (player.equals(Player.RED)) {
            redAttacksWon++;
        } else if (player.equals(Player.BLUE)) {
            blueAttacksWon++;
        }
    }
}