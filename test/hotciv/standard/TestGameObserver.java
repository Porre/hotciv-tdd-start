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
}

class ObserverStub implements GameObserver {
    private boolean notified;

    public ObserverStub() {
        notified = false;
    }

    public void worldChangedAt(Position pos) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void turnEnds(Player nextPlayer, int age) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void tileFocusChangedAt(Position position) {
        System.out.println("Observer notified at position: " + position);
        notified = true;
    }

    public boolean isNotified() {
        return notified;
    }
}