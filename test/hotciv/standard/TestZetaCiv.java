package hotciv.standard;

import hotciv.factories.ZetaCivFactory;
import hotciv.framework.*;
import hotciv.variants.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class TestZetaCiv {
    private Game game;
    /** Fixture for ZetaCiv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new ZetaCivFactory(), new DieImpl());
    }

    @Test
    public void redWinsWhenAllCitiesConquered() {
        game.moveUnit(new Position(2, 0), new Position(3, 1));
        game.endOfTurn();
        game.endOfTurn(); // RED's turn
        // Conquers BLUE city, now all RED
        game.moveUnit(new Position(3, 1), new Position(4, 1));
        assertEquals("Red should win when all cities are owned by red", Player.RED, game.getWinner());
    }

    @Test
    public void blueWinsWhenAllCitiesConquered() {
        game.endOfTurn(); // BLUE's turn
        game.moveUnit(new Position(3, 2), new Position(2, 1));
        game.endOfTurn();
        game.endOfTurn(); // RED's turn
        // Conquers RED city
        game.moveUnit(new Position(2, 1), new Position(1, 1));
        assertEquals("Blue should win when all cities are owned by blue", Player.BLUE, game.getWinner());
    }

    @Test
    public void at20RoundsOrLaterOwningAllCitiesDoesNotGiveAWin() {
        game.moveUnit(new Position(2,0), new Position(3,0));

        for (int i = 0; i < 20; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        game.moveUnit(new Position(3, 0), new Position(4, 1));
        assertNull("There should be no winner yet", game.getWinner());
    }

    @Test
    public void threeAttacksWonDoesNotGiveVictoryBefore20Rounds() {
        // RED ARCHER
        game.moveUnit(new Position(2, 0), new Position(3, 1));

        // Create RED SETTLER
        game.changeProductionInCityAt(new Position(1, 1), GameConstants.SETTLER);
        game.endOfTurn();


        // BLUE LEGION attacks RED SETTLER (and wins)
        game.moveUnit(new Position(3, 2), new Position(4, 3));

        game.endOfTurn();
        game.endOfTurn();

        // BLUE moves LEGION
        game.moveUnit(new Position(4, 3), new Position(3, 2));

        game.endOfTurn();
        game.endOfTurn();

        // BLUE LEGION attacks RED ARCHER (and wins)
        game.moveUnit(new Position(3, 2), new Position(3, 1));
        game.endOfTurn();

        // Wait for RED SETTLER to be made
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();

        // RED SETTLER moves
        game.moveUnit(new Position(1, 1), new Position(2, 1));
        game.endOfTurn();

        // BLUE LEGION attacks RED SETTLER (and wins)
        game.moveUnit(new Position(3, 1), new Position(2, 1));
        assertNull("There should be no winner of the game yet", game.getWinner());
    }

    @Test
    public void threeSuccessfulAttacksYieldVictoryAfter20Rounds() {
        for (int i = 0; i < 19; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        // RED's turn
        game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
        game.moveUnit(new Position(2, 0), new Position(3, 1));
        game.moveUnit(new Position(4, 3), new Position(3, 3));
        game.getWinner(); // our assumption is that this will be called here
        game.endOfTurn();

        // BLUE LEGION attacks RED ARCHER (and wins)
        game.moveUnit(new Position(3, 2), new Position(3, 1));
        game.endOfTurn();

        // RED's turn
        game.moveUnit(new Position(3, 3), new Position(3, 2));
        game.endOfTurn();

        // BLUE LEGION attacks RED SETTLER (and wins)
        game.moveUnit(new Position(3, 1), new Position(3, 2));
        assertNull("Blue should not have won", game.getWinner());
        game.endOfTurn();

        // Move RED ARCHER
        game.moveUnit(new Position(1, 1), new Position(2, 1));
        game.endOfTurn();

        // BLUE LEGION attacks RED ARCHER (and wins)
        game.moveUnit(new Position(3, 2), new Position(2, 1));
        assertEquals("Blue should have won", Player.BLUE, game.getWinner());
    }
}