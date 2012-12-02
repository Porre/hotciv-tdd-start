package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;
import hotciv.standard.GameImpl;

public class ZetaCivWin implements WinStrategy {

    private BetaCivWin betaWin;
    private EpsilonCivWin epsilonWin;

    public ZetaCivWin(EpsilonCivWin winStrategy) {
        betaWin = new BetaCivWin();
        epsilonWin = winStrategy;
    }

    public Player getWinner(Game game) {
        GameImpl g = (GameImpl) game;

        if (g.getRound() <= 20) {
            return betaWin.getWinner(game);
        } else if (g.getRound() > 20) {
            return epsilonWin.getWinner(game);
        } else {
            return null;
        }
    }
}