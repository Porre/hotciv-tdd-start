package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;
import hotciv.standard.GameImpl;

public class EpsilonCivWin implements WinStrategy {

    public Player getWinner(Game game) {
        GameImpl g = (GameImpl) game;
        if (g.getAttacksWon(Player.RED) >= 3) {
            return Player.RED;
        } else if (g.getAttacksWon(Player.BLUE) >= 3) {
            return Player.BLUE;
        } else {
            return null;
        }
    }
}