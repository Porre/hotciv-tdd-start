package hotciv.variants;

import hotciv.framework.*;

public class AlphaCivBattle implements BattleStrategy {

    private EpsilonCivWin epsilonWin;

    public AlphaCivBattle(EpsilonCivWin winStrategy) {
        epsilonWin = winStrategy;
    }

    public boolean getBattleResult(Game game, Position attacker, Position defender, Die die) {
        Player attackOwner = game.getUnitAt(attacker).getOwner();
        epsilonWin.increaseAttacksWon(attackOwner);
        return true;
    }
}