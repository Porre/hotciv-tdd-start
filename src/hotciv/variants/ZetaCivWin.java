package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.UnitWinCount;
import hotciv.framework.WinStrategy;
import hotciv.standard.GameImpl;

public class ZetaCivWin implements WinStrategy {

    private BetaCivWin betaWin;
    private EpsilonCivWin epsilonWin;
    private GameImpl g;

    public ZetaCivWin(Game game) {
        betaWin = new BetaCivWin();
        g = (GameImpl) game;
    }

    public Player getWinner(Game game) {
        if (g.getRound() == 20) {
            epsilonWin = new EpsilonCivWin(game);
        }

        if (g.getRound() <= 20) {
            return betaWin.getWinner(game);
        } else if (g.getRound() > 20) {
            return epsilonWin.getWinner(game);
        } else {
            return null;
        }
    }
}