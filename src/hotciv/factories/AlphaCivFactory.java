package hotciv.factories;

import hotciv.framework.*;
import hotciv.variants.*;

public class AlphaCivFactory implements AbstractFactory {

    @Override
    public AgeStrategy getAgeStrategy() {
        return new AlphaCivAge();
    }

    @Override
    public BattleStrategy getBattleStrategy() {
        return new AlphaCivBattle(new EpsilonCivWin());
    }

    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return new AlphaCivAction();
    }

    @Override
    public WinStrategy getWinStrategy() {
        return new AlphaCivWin();
    }

    @Override
    public WorkforceFocusStrategy getWorkforceFocusStrategy() {
        return new AlphaCivWorkforceFocusStrategy();
    }

    @Override
    public WorldLayoutStrategy getWorldLayoutStrategy() {
        return new AlphaCivWorldLayout();
    }
}
