package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;

public class AlphaCivWin implements WinStrategy {

    public Player getWinner(Game game) {
        if (game.getAge() == -3000) {
            return Player.RED;
        } else {
            return null;
        }
    }
}
