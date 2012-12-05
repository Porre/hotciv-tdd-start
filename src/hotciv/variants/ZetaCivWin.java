package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.UnitWinCount;
import hotciv.framework.WinStrategy;
import hotciv.standard.GameImpl;

public class ZetaCivWin implements WinStrategy, UnitWinCount {

    private BetaCivWin betaWin;
    private EpsilonCivWin epsilonWin;
    private GameImpl g;

    public ZetaCivWin(Game game) {
        betaWin = new BetaCivWin();
        epsilonWin = new EpsilonCivWin(game);
        g = (GameImpl) game;
        g.registerWinStrategy(this);
    }

    public Player getWinner(Game game) {
        if (g.getRound() <= 20) {
            return betaWin.getWinner(game);
        } else if (g.getRound() > 20) {
            return epsilonWin.getWinner(game);
        } else {
            return null;
        }
    }

    public void increaseWins(Player player) {
        if (g.getRound() > 20) {
            epsilonWin.increaseWins(player);
        }
    }
}