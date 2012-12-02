package hotciv.factories;

import hotciv.framework.*;
import hotciv.variants.*;
import java.lang.*;

public class EpsilonCivFactory implements AbstractFactory{

    private EpsilonCivWin winStrategy;

    public EpsilonCivFactory() {
        winStrategy = new EpsilonCivWin();
    }

    @Override
    public AgeStrategy getAgeStrategy() {
        return new AlphaCivAge();
    }

    @Override
    public BattleStrategy getBattleStrategy() {
        return new EpsilonCivBattle(winStrategy);
    }

    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return new AlphaCivAction();
    }

    @Override
    public WinStrategy getWinStrategy() {
        return winStrategy;
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
