package hotciv.standard;

import hotciv.factories.AlphaCivFactory;
import hotciv.factories.SemiCivFactory;
import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;

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
public class TestGameObserver {
    private Game game;
    private Position position;

    @Before
    public void setUp() {
        game = new GameImpl(new SemiCivFactory(), new DieImpl());
        position = new Position(1, 1);
    }

    @Test
    public void shouldNotifyObserver() {
        GameObserver gameObserver = new ObserverStub();

        game.addObserver(gameObserver);

        game.setTileFocus(position);

        assertTrue("Observer should be notified", ((ObserverStub) gameObserver).isNotified());
    }

    @Test
    public void multibleObserversShouldBeNotified() {
        GameObserver gameObserver1 = new ObserverStub();
        GameObserver gameObserver2 = new ObserverStub();
        GameObserver gameObserver3 = new ObserverStub();

        game.addObserver(gameObserver1);
        game.addObserver(gameObserver2);
        game.addObserver(gameObserver3);

        game.setTileFocus(position);

        assertTrue("Observer 1 should be notified", ((ObserverStub) gameObserver1).isNotified());
        assertTrue("Observer 2 should be notified", ((ObserverStub) gameObserver2).isNotified());
        assertTrue("Observer 3 should be notified", ((ObserverStub) gameObserver3).isNotified());
    }

    @Test
    public void observersShouldKnowWhenTurnEnds() {
        GameObserver gameObserver = new ObserverStub();

        game.addObserver(gameObserver);

        game.endOfTurn();

        ObserverStub observerStub = (ObserverStub) gameObserver;

        assertTrue("Observer should be notified", observerStub.isNotified());
        assertEquals("Age from observer should be the one of game", game.getAge(), observerStub.getAge());
        assertEquals("Player should be the one from game", game.getPlayerInTurn(), observerStub.getCurrentPlayer());

        observerStub.resetNotified();
        game.endOfTurn();

        assertTrue("Observer should be notified", observerStub.isNotified());
        assertEquals("Age from observer should be the one of game", game.getAge(), observerStub.getAge());
        assertEquals("Player should be the one from game", game.getPlayerInTurn(), observerStub.getCurrentPlayer());
    }

    @Test
    public void observersShouldKnowWhenWorldChanges() {
        GameObserver gameObserver = new ObserverStub();
        ObserverStub observerStub = (ObserverStub) gameObserver;
        Position blueArcherPosition = new Position(3, 8);
        Position newBlueArcherPosition = new Position(3, 9);

        game.addObserver(gameObserver);

        // Making it blues turn so we can move blue units
        game.endOfTurn();
        assertTrue("Observer should be notified", observerStub.isNotified());
        assertEquals("Player should be blue", Player.BLUE, observerStub.getCurrentPlayer());

        observerStub.resetNotified();
        game.moveUnit(blueArcherPosition, newBlueArcherPosition);

        assertTrue("Observer should be notified of change in world change at archer position", observerStub.isNotified());

        observerStub.resetNotified();

        game.performUnitActionAt(newBlueArcherPosition);

        assertTrue("Observer should be notified of change in world change at archer position", observerStub.isNotified());
    }
}

class ObserverStub implements GameObserver {
    private boolean notified;
    private int age;
    private Player currentPlayer;

    public ObserverStub() {
        notified = false;
        age = 0;
        currentPlayer = null;
    }

    public void worldChangedAt(Position pos) {
        notified = true;
    }

    public void turnEnds(Player nextPlayer, int age) {
        this.age = age;
        currentPlayer = nextPlayer;
        notified = true;
    }

    public void tileFocusChangedAt(Position position) {
        notified = true;
    }

    public boolean isNotified() {
        return notified;
    }

    public int getAge() {
        return age;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void resetNotified() {
        notified = false;
    }
}