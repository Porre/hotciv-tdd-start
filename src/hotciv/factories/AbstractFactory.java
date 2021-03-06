package hotciv.factories;

import hotciv.framework.*;

public interface AbstractFactory {
    public AgeStrategy getAgeStrategy();
    public BattleStrategy getBattleStrategy();
    public UnitActionStrategy getUnitActionStrategy();
    public WinStrategy getWinStrategy(Game game);
    public WorkforceFocusStrategy getWorkforceFocusStrategy();
    public WorldLayoutStrategy getWorldLayoutStrategy();
}
