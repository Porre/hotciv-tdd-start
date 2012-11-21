package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;

import static org.junit.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: Klaus Fyhn Jacobsen
 * Date: 19-11-12
 * Time: 18:33
 * To change this template use File | Settings | File Templates.
 */
public class TestGammaCiv {
    private Game game;
    /** Fixture for GammaCiv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaCivAge(), new AlphaCivWin(), new GammaCivAction(), new AlphaCivWorldLayout());
    }

    @Test
    public void settlerAction() {
        Position settlerPos = new Position(4,3);
        Unit before = game.getUnitAt(settlerPos);
        assertEquals("There should be a settler at (4,3)", GameConstants.SETTLER, before.getTypeString());

        game.performUnitActionAt(settlerPos);
        Unit after = game.getUnitAt(settlerPos);
        assertNull("Settler should be deleted", after);

        City c = game.getCityAt(settlerPos);
        assertNotNull("There should be a city at (4,3)", c);
        assertEquals("City should be owned by settler owner",before.getOwner(),c.getOwner());
        assertEquals("New city should have population one", 1, c.getSize());
    }

    @Test
    public void archerAction() {
        Position archerPos = new Position(2,0);
        Unit archer = game.getUnitAt(archerPos);
        int basicDefence = archer.getDefensiveStrength();
        assertEquals("There should be a archer at (2,0)", GameConstants.ARCHER, archer.getTypeString());

        game.performUnitActionAt(archerPos);
        assertNotNull("There should be a archer at (2,0)", archer);
        assertEquals("Archer should double defensive skill", 2 * basicDefence, archer.getDefensiveStrength());

        assertFalse("Archer should not be able to move when fortified", game.moveUnit(archerPos, new Position(2,1)));

        game.performUnitActionAt(archerPos);
        assertEquals("Archer should lose bonus when not fortified", basicDefence, archer.getDefensiveStrength());
        assertTrue("Archer should again be able to move", game.moveUnit(archerPos, new Position(2,1)));

    }
}
