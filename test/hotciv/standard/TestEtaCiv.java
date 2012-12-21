package hotciv.standard;

import hotciv.factories.BetaCivFactory;
import hotciv.factories.EtaCivFactory;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestEtaCiv {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new EtaCivFactory(), new DieImpl());
    }

    @Test
    public void cityShouldBeAbleToChangeWorkforceFocus() {
        CityImpl city = (CityImpl) game.getCityAt(new Position(1, 1));
        assertEquals("Should be production focus", GameConstants.productionFocus, city.getWorkforceFocus());
        city.setWorkforceFocus(GameConstants.foodFocus);
        assertEquals("Should be food focus", GameConstants.foodFocus, city.getWorkforceFocus());
    }

    @Test
    public void citiesStartWith0Food() {
        CityImpl city = (CityImpl) game.getCityAt(new Position(1, 1));
        assertEquals("Should start with 0 food", 0, city.getFoodTotal());
        CityImpl city2 = (CityImpl) game.getCityAt(new Position(4, 1));
        assertEquals("Should start with 0 food", 0, city2.getFoodTotal());
    }

    @Test
    public void citiesStartWith0Production() {
        CityImpl city = (CityImpl) game.getCityAt(new Position(1, 1));
        assertEquals("Should start with 0 food", 0, city.getProductionTotal());
        CityImpl city2 = (CityImpl) game.getCityAt(new Position(4, 1));
        assertEquals("Should start with 0 food", 0, city2.getProductionTotal());
    }

    @Test
    public void citiesGet1FoodAnd1ProductionWithSize1() {
        CityImpl city = (CityImpl) game.getCityAt(new Position(1, 1));
        wait(1);
        assertEquals("Cities should have 1 food after 1 round", 1, city.getFoodTotal());
        assertEquals("Cities should have 1 production after 1 round", 1, city.getProductionTotal());
        wait(2);
        assertEquals("Cities should have 3 food after 3 rounds", 3, city.getFoodTotal());
        assertEquals("Cities should have 3 production after 3 rounds", 3, city.getProductionTotal());
        wait(1);
        assertEquals("Cities should have 4 food after 4 rounds", 4, city.getFoodTotal());
        assertEquals("Cities should have 4 production after 4 rounds", 4, city.getProductionTotal());
    }

    @Test
    public void cityIncreasesPopulationFrom1To2At8FoodTotalAndLosesAllFood() {
        CityImpl city = (CityImpl) game.getCityAt(new Position(1, 1));
        wait(7);
        assertEquals("City should have population 1", 1, city.getSize());
        wait(1);
        assertEquals("City should have population 2", 2, city.getSize());
        assertEquals("City should have 0 food", 0, city.getFoodTotal());
    }

    @Test
    public void cityGets4FoodWithSize2FoodFocusAndPlainsAdjacent() {
        /* 1 food + 3 food = 4 food */
        CityImpl city = (CityImpl) game.getCityAt(new Position(1, 1));
        city.setWorkforceFocus(GameConstants.foodFocus);
        wait(8);
        assertEquals("City should have population 2", 2, city.getSize());
        assertEquals("City should have 0 food", 0, city.getFoodTotal());
        wait(1);
        assertEquals("City should have 4 food", 4, city.getFoodTotal());
    }


    @Test
    public void cityGets3ProductionWithSize2ProductionFocusAndHillsAdjacent() {
        /* 1 production + 2 production = 3 */
        CityImpl city = (CityImpl) game.getCityAt(new Position(1, 1));
        city.setWorkforceFocus(GameConstants.productionFocus);
        city.setProduction(GameConstants.LEGION);
        wait(8);
        assertEquals("City should have population 2", 2, city.getSize());
        assertEquals("City should have 8 production", 8, city.getProductionTotal());
        wait(1);
        assertEquals("City should have 11 production", 11, city.getProductionTotal());
    }

    @Test
    public void cityCannotHaveGreatPopulationThan9() {
        /* There are only plains around city at (4,1) = loads of food */
        CityImpl city = (CityImpl) game.getCityAt(new Position(4, 1));
        city.setWorkforceFocus(GameConstants.foodFocus);
        wait(22);
        assertEquals("Should have 8 population", 8, city.getSize());
        wait(1);
        assertEquals("Should have 9 population", 9, city.getSize());
        wait(10);
        assertEquals("Should still have 9 population", 9, city.getSize());
    }

    private void wait(int time) {
        for (int i = 0; i < time; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }
}
