package hotciv.factories;

import hotciv.framework.*;
import hotciv.variants.*;
import java.lang.*;

public class BetaCivFactory implements AbstractFactory{
    @Override
    public AgeStrategy getAgeStrategy() {
        return new AlphaCivAction();
    }

    @Override
    public BattleStrategy getBattleStrategy() {
        return new AlphaCivBattle();
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
    public WorldLayoutStrategy getWorldLayoutStrategy() {
        return new AlphaCivWorldLayout();
    }
}
