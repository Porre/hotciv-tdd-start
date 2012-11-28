package hotciv.standard;

import hotciv.factories.EpsilonCivFactory;
import hotciv.framework.*;
import hotciv.variants.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestEpsilonCiv {
    private GameImpl game;
    /** Fixture for EpsilonCiv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonCivFactory(), new DieStub());
    }

    @Test
    public void redCannotMoveBlueUnit() {
        assertFalse("Red may not move a blue unit", game.moveUnit(new Position(3, 2), new Position(3, 3)));
    }

    @Test
    public void blueCannotMoveRedUnit() {
        game.endOfTurn(); // Blue's turn
        assertFalse("Blue may not move a red unit", game.moveUnit(new Position(4, 3), new Position(4, 4)));
    }

    @Test
    public void attackerMovesAfterWinning() {
        // Start producing LEGION
        game.changeProductionInCityAt(new Position(1, 1), GameConstants.LEGION);
        game.endOfTurn();
        // Move BLUE legion to (2,1)
        game.moveUnit(new Position(3, 2), new Position(2, 1));
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        // RED LEGION attacks BLUE LEGION
        game.moveUnit(new Position(1, 1), new Position(2, 1));
        Unit unit = game.getUnitAt(new Position(2, 1));
        assertEquals("It should be a red unit at (2,1)", Player.RED, unit.getOwner());
    }

    @Test
    public void attackerIsRemovedAfterADefeat() {
        // SETTLER moved to attack LEGION (it should lose)
        game.moveUnit(new Position(4, 3), new Position(3 ,2));
        Unit unit = game.getUnitAt(new Position(3,2));
        assertEquals("There should be a blue unit at (3,2)", Player.BLUE, unit.getOwner());
    }

    @Test
    public void redWinsAfter3WonAttacks() {
        /**
         * 3 fights that RED is 100% sure to win.
         */

        // Set RED to produce LEGION
        game.changeProductionInCityAt(new Position(1, 1), GameConstants.LEGION);
        game.endOfTurn(); // BLUE's turn
        game.moveUnit(new Position(3, 2), new Position(2, 1));
        game.changeProductionInCityAt(new Position(4, 1), GameConstants.LEGION);
        game.endOfTurn();

        // Wait 3 rounds
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();

        // First win for RED
        game.moveUnit(new Position(1, 1), new Position(2, 1));
        game.changeProductionInCityAt(new Position(1, 1), GameConstants.SETTLER);
        game.endOfTurn(); // BLUE's turn
        game.moveUnit(new Position(4, 1), new Position(3, 1));
        game.endOfTurn(); // RED's turn
        game.moveUnit(new Position(2, 1), new Position(1, 1));
        game.endOfTurn(); // BLUE's turn
        game.moveUnit(new Position(3, 1), new Position(2, 1));
        game.endOfTurn(); // RED's turn

        // Second win for RED
        game.moveUnit(new Position(1, 1), new Position(2, 1));
        game.endOfTurn(); // BLUE's turn
        game.moveUnit(new Position(4, 1), new Position(3, 1));
        game.endOfTurn(); // RED's turn
        game.moveUnit(new Position(2, 1), new Position(1, 1));
        game.endOfTurn(); // BLUE's turn
        game.moveUnit(new Position(3, 1), new Position(2, 1));
        game.endOfTurn(); // RED's turn

        // Third and what should be the final win before RED wins the game
        game.moveUnit(new Position(1, 1), new Position(2, 1));
        assertEquals("Red should win the game after winning 3 attacks", Player.RED, game.getWinner());
    }

    class DieStub implements Die {
        public int rollDie() {
            return 1;
        }
    }
}