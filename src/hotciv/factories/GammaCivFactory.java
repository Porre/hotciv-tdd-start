package hotciv.factories;

import hotciv.framework.*;
import hotciv.variants.*;
import java.lang.*;

public class GammaCivFactory implements AbstractFactory{
    @Override
    public AgeStrategy getAgeStrategy() {
        return new AlphaCivAge();
    }

    @Override
    public BattleStrategy getBattleStrategy() {
        return new AlphaCivBattle();
    }

    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return new GammaCivAction();
    }

    @Override
    public WinStrategy getWinStrategy(Game game) {
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
