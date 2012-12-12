package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.FractalAdapter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFractal {

    private WorldLayoutStrategy world;

    @Before
    public void setUp() {
        world = new FractalAdapter();
    }

    @Test
    public void mapHasCorrectSize() {
        assertEquals(world.getWorldLayout().length, GameConstants.WORLDSIZE);
    }

    @Test
    public void rowsOfMapHasCorrectLength() {
        for (String s : world.getWorldLayout()) {
            assertTrue(s + " was not of correct length", s.length() == GameConstants.WORLDSIZE);
        }
    }
}
