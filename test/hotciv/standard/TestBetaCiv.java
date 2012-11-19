package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.AlphaCivAction;
import hotciv.variants.AlphaCivAge;
import hotciv.variants.BetaCivAge;
import hotciv.variants.BetaCivWin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBetaCiv {
    private Game game;
    /** Fixture for betaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new BetaCivAge(), new BetaCivWin(), new AlphaCivAction());
    }

    @Test
    public void from300BCTo100BC() {
        goToAge(-300);
        assertEquals("It should be 300BC", -300, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("It should be 100BC", -100, game.getAge());
    }

    @Test
    public void from100BCTo1BC() {
        goToAge(-100);
        assertEquals("It should be 100BC", -100, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("It should be 1BC", -1, game.getAge());
    }

    @Test
    public void from1BCTo1AD() {
        goToAge(-1);
        assertEquals("It should be 1BC", -1, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("It should be 1AD", 1, game.getAge());
    }

    @Test
    public void from1ADTo50AD() {
        goToAge(1);
        assertEquals("It should be 1AD", 1, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("It should be 50AD", 50, game.getAge());
    }

    @Test
    public void from1000ADTo1050AD() {
        goToAge(1000);
        assertEquals("It should be 1000AD", 1000, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("It should be 1050AD", 1050, game.getAge());
    }

    @Test
    public void from1800ADTo1825AD() {
        goToAge(1800);
        assertEquals("It should be 1800AD", 1800, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("It should be 1825AD", 1825, game.getAge());
    }

    @Test
    public void from1950ADTo1955AD() {
        goToAge(1950);
        assertEquals("It should be 1950AD", 1950, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("It should be 1955AD", 1955, game.getAge());
    }

    @Test
    public void from1989ADTo1990AD() {
        goToAge(1989);
        assertEquals("It should be 1989AD", 1989, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("It should be 1990AD", 1990, game.getAge());
    }

    @Test
    public void redWinsWhenThereAreNoMoreBlueCities() {
        game.moveUnit(new Position(2, 0), new Position(3, 0));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(3, 0), new Position(4, 1));
        assertEquals("Red player should own the city at (4,1)", Player.RED, game.getCityAt(new Position(4, 1)).getOwner());
        assertEquals("Red should be the winner", Player.RED, game.getWinner());
    }

    public void goToAge(int age) {
        while (game.getAge() != age) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }
}