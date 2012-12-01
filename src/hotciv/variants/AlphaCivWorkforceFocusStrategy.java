package hotciv.variants;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.WorkforceFocusStrategy;

public class AlphaCivWorkforceFocusStrategy implements WorkforceFocusStrategy {

    @Override
    public void increaseCitySize(Game game, Position position) {
    }

    @Override
    public int getIncreaseOfProduction(Game game, Position position) {
        return 6;
    }

    @Override
    public int getIncreaseOfFood(Game game, Position position) {
        return 1;
    }
}
