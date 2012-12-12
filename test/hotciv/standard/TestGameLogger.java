package hotciv.standard;

import hotciv.factories.SemiCivFactory;
import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestGameLogger {

    private Game game;

    @Before
    public void setUp() {
        game = new GameLogger(new GameImpl(new SemiCivFactory(), new DieImpl()));
    }

    @Test
    public void shouldHaveRedCityAt8_12() {
        City city = game.getCityAt(new Position(8, 12));
        assertNotNull("There should be a city at (8,12)", city);
        Player player = city.getOwner();
        assertEquals("City at (8,12) should be owned by red", Player.RED, player);
    }

    @Test
    public void movingUnitsShouldProvideTranscriptionOfMoves() {
        game.endOfTurn();
        game.moveUnit(new Position(4, 4), new Position(5 ,4));
        game.moveUnit(new Position(3, 8), new Position(2, 8));
        game.endOfTurn();
    }
}
