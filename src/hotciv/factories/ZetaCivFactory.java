package hotciv.factories;

import hotciv.framework.*;
import hotciv.variants.*;
import java.lang.*;

public class ZetaCivFactory implements AbstractFactory{

    private EpsilonCivWin winStrategy;

    public ZetaCivFactory() {
        winStrategy = new EpsilonCivWin();
    }

    @Override
    public AgeStrategy getAgeStrategy() {
        return new AlphaCivAge();
    }

    @Override
    public BattleStrategy getBattleStrategy() {
        return new AlphaCivBattle(winStrategy);
    }

    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return new AlphaCivAction();
    }

    @Override
    public WinStrategy getWinStrategy() {
        return new ZetaCivWin(winStrategy);
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
