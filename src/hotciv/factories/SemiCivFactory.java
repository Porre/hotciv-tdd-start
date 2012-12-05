package hotciv.factories;

import hotciv.framework.*;
import hotciv.variants.*;

public class SemiCivFactory implements AbstractFactory {

    @Override
    public AgeStrategy getAgeStrategy() {
        return new BetaCivAge();
    }

    @Override
    public BattleStrategy getBattleStrategy() {
        return new EpsilonCivBattle();
    }

    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return new GammaCivAction();
    }

    @Override
    public WinStrategy getWinStrategy(Game game) {
        return new EpsilonCivWin(game);
    }

    @Override
    public WorkforceFocusStrategy getWorkforceFocusStrategy() {
        return new EtaCivWorkforceFocusStrategy();
    }

    @Override
    public WorldLayoutStrategy getWorldLayoutStrategy() {
        return new DeltaCivWorldLayout();
    }
}
