package hotciv.standard;

import hotciv.factories.AlphaCivFactory;
import hotciv.factories.DeltaCivFactory;
import hotciv.framework.*;
import hotciv.variants.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestDeltaCiv {
    private Game game;
    /** Fixture for deltaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new DeltaCivFactory(), new DieImpl());
    }

    @Test
    public void shouldHaveRedCityAt8_12() {
        City city = game.getCityAt(new Position(8, 12));
        assertNotNull("There should be a city at (8,12)", city);
        Player player = city.getOwner();
        assertEquals("City at (8,12) should be owned by red", Player.RED, player);
    }

    @Test
    public void shouldHaveBlueCityAt4_5() {
        City city = game.getCityAt(new Position(4, 5));
        assertNotNull("There should be a city at (4,5)", city);
        Player player = city.getOwner();
        assertEquals("City at (4,5) should be owned by blue", Player.BLUE, player);
    }

    @Test
         public void shouldHaveBlueLegionAt4_4() {
        Unit unit = game.getUnitAt(new Position(4, 4));
        String type = unit.getTypeString();
        Player owner = unit.getOwner();
        assertEquals("There should be a LEGION at (4,4)", GameConstants.LEGION, type);
        assertEquals("BLUE should have a LEGION at (4,4)", Player.BLUE, owner);
    }

    @Test
    public void shouldHaveBlueArcherAt8_3() {
        Unit unit = game.getUnitAt(new Position(3, 8));
        String type = unit.getTypeString();
        Player owner = unit.getOwner();
        assertEquals("There should be an ARCHER at (3,8)", GameConstants.ARCHER, type);
        assertEquals("BLUE should have an ARCHER at (3,8)", Player.BLUE, owner);
    }

    @Test
    public void shouldHaveBlueArcherAt5_5() {
        Unit unit = game.getUnitAt(new Position(5, 5));
        String type = unit.getTypeString();
        Player owner = unit.getOwner();
        assertEquals("There should be a SETTLER at (5,5)", GameConstants.SETTLER, type);
        assertEquals("BLUE should have a SETTLER at (5,5)", Player.BLUE, owner);
    }

    @Test
    public void citiesShouldAccumulateProduction() {
        Position redPos = new Position(8, 12);
        Position bluePos = new Position(4, 5);
        CityImpl redCity = (CityImpl) game.getCityAt(redPos);
        CityImpl blueCity = (CityImpl) game.getCityAt(bluePos);

        int accumulatedProduction = 0;

        while(game.getAge() != -3000) {
            assertEquals("Red city should accumulate production", accumulatedProduction, redCity.getProductionTotal());
            game.endOfTurn();
            accumulatedProduction += 6;
            assertEquals("Blue city should accumulate production", accumulatedProduction, blueCity.getProductionTotal());
            game.endOfTurn();
        }
    }
}
