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
    public void shouldHaveBlueCityAt4_1() {
        City c = game.getCityAt(new Position(4,1));
        assertNotNull("There should be a city at (4,1)", c);
        Player p = c.getOwner();
        assertEquals("City at (4,1) should be owned by blue", Player.BLUE, p);
    }

    @Test
    public void shouldHaveOceanAt1_0() {
        Tile t = game.getTileAt(new Position(1,0));
        assertNotNull("There should be a tile at (1, 0)", t);
        assertEquals("The tile should be of type OCEAN", GameConstants.OCEANS, t.getTypeString());
    }

    @Test
    public void shouldHaveHillAt0_1() {
        Tile t = game.getTileAt(new Position(0,1));
        assertNotNull("There should be a tile at (0, 1)", t);
        assertEquals("The tile should be of type HILLS", GameConstants.HILLS, t.getTypeString());
    }

    @Test
    public void shouldHaveMountainAt2_2() {
        Tile t = game.getTileAt(new Position(2,2));
        assertNotNull("There should be a tile at (2, 2)", t);
        assertEquals("The tile should be of type MOUNTAINS", GameConstants.MOUNTAINS, t.getTypeString());
    }

    @Test
    public void unitsCannotMoveOverMountains() {
        assertFalse("Unit cannot move over mountains", game.moveUnit(new Position(2, 3), new Position(2,2)));
    }

    @Test
    public void unitsCannotMoveOverOcean() {
        assertFalse("Unit cannot move over ocean", game.moveUnit(new Position(0, 0), new Position(1,0)));
    }

    @Test
    public void unitsCanMoveFromPlainsToPlains() {
        assertTrue("Unit can move from PLAINS to PLAINS", game.moveUnit(new Position(10, 10), new Position(10,11)));
    }

    @Test
    public void redIsFirst() {
        assertEquals("Red should start first", Player.RED, game.getPlayerInTurn());
    }

    @Test
    public void blueTurnAfterRed() {
        Player player = game.getPlayerInTurn();
        if (player.equals(Player.RED)) {
            game.endOfTurn();
            assertEquals("It should be blue player's turn after red", Player.BLUE, game.getPlayerInTurn());
        }
    }

    @Test
    public void gameStartsAt4000BC() {
        assertEquals("Starting age should be 4000 BC", -4000, game.getAge());
    }

    @Test
    public void aRoundAdvancesGameBy100Years() {
        int currentAge = game.getAge();
        game.endOfTurn();
        assertEquals("A round should advance game age by 100 years", currentAge + 100, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Two more rounds should advance game age by 200 years", currentAge + 300, game.getAge());
    }

    @Test
    public void redWinsIn3000BC() {
        while(game.getAge() != -3000) {
            game.endOfTurn();
        }
        assertEquals("Red should win game in year 3000 BC", Player.RED, game.getWinner());
    }
}