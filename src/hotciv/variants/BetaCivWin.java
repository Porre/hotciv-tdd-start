package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;
import hotciv.standard.GameImpl;

public class BetaCivWin implements WinStrategy {

    public Player getWinner(Game game)  {
        GameImpl g = (GameImpl) game;

        if (g.getCityPositionsOwnedByPlayer(Player.RED).isEmpty()) {
            return Player.BLUE;
        } else if (g.getCityPositionsOwnedByPlayer(Player.BLUE).isEmpty()) {
            return Player.RED;
        } else {
            return null;
        }
    }
}