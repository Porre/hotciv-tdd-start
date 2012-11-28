package hotciv.factories;

import hotciv.framework.*;
import hotciv.variants.*;
import java.lang.*;

public class EpsilonCivFactory implements AbstractFactory{
    @Override
    public AgeStrategy getAgeStrategy() {
        return new AlphaCivAge();
    }

    @Override
    public BattleStrategy getBattleStrategy() {
        return new EpsilonCivBattle();
    }

    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return new AlphaCivAction();
    }

    @Override
    public WinStrategy getWinStrategy() {
        return new EpsilonCivWin();
    }

    @Override
    public WorldLayoutStrategy getWorldLayoutStrategy() {
        return new AlphaCivWorldLayout();
    }
}
