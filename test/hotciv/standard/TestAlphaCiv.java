package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;

/** Skeleton class for AlphaCiv test cases

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
*/
public class TestAlphaCiv {
    private Game game;
    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl();
    }

    @Test
    public void shouldHaveRedCityAt1_1() {
        City c = game.getCityAt(new Position(1,1));
        assertNotNull("There should be a city at (1,1)", c);
        Player p = c.getOwner();
        assertEquals("City at (1,1) should be owned by red", Player.RED, p);
    }

    @Test
    public void shouldHaveOceanAt1_0() {
        Tile t = game.getTileAt(new Position(1,0));
        assertNotNull("There should be a tile at (1, 0)", t);
        assertEquals("The tile should be of type Ocean", "Ocean", t.getTypeString());
    }

    @Test
    public void shouldHaveHillAt0_1() {
        Tile t = game.getTileAt(new Position(0,1));
        assertNotNull("There should be a tile at (0, 1)", t);
        assertEquals("The tile should be of type Hill", "Hill", t.getTypeString());
    }

    @Test
    public void shouldHaveMountainAt2_2() {
        Tile t = game.getTileAt(new Position(2,2));
        assertNotNull("There should be a tile at (2, 2)", t);
        assertEquals("The tile should be of type Mountain", "Mountain", t.getTypeString());
    }

    @Test
    public void unitsCannotMoveOverMountains() {
        Tile t = new TileImpl(new Position(2, 2),"Mountain");
        assertNotNull("There should be at tile at (2,2)",t);
        assertEquals("The tile should be of type mountain", "Mountain", t.getTypeString());
        assertFalse("Unit cannot move over mountains",game.moveUnit(new Position(2, 3), new Position(2,2)));
    }
}