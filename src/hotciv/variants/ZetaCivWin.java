package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;
import hotciv.standard.GameImpl;

public class ZetaCivWin implements WinStrategy {

    private int redWonTillNow;
    private int blueWonTillNow;
    private BetaCivWin betaWin;

    public ZetaCivWin() {
        redWonTillNow = 0;
        blueWonTillNow = 0;
        betaWin = new BetaCivWin();
    }

    public Player getWinner(Game game) {
        GameImpl g = (GameImpl) game;

        // Gets snapshot of attacks won, only works if getWinner() is called in the 20th round
        // which we assume it will be
        if (g.getRound() == 20) {
            redWonTillNow = g.getAttacksWon(Player.RED);
            blueWonTillNow = g.getAttacksWon(Player.BLUE);
        }

        if (g.getRound() <= 20) {
            return betaWin.getWinner(game);
        } else if (redWonTillNow + 3 <= g.getAttacksWon(Player.RED) && g.getRound() > 20) {
            return Player.RED;
        } else if (blueWonTillNow + 3 <= g.getAttacksWon(Player.BLUE) && g.getRound() > 20) {
            return Player.BLUE;
        } else {
            return null;
        }
    }
}