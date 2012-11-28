package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestEpsilonCiv {
    private Game game;
    /** Fixture for EpsilonCiv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaCivAge(), new EpsilonCivWin(), new GammaCivAction(), new AlphaCivWorldLayout()
            , new EpsilonCivBattle());
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

    // ========= TEST STUBS ==========
    class StubTile implements Tile {
        private String type;
        public StubTile(String type, int r, int c) { this.type = type; }
        public Position getPosition() { return null; }
        public String getTypeString() { return type; }
    }

    class StubUnit implements Unit {
        private String type; private Player owner;
        public StubUnit(String type, Player owner) {
            this.type = type; this.owner = owner;
        }
        public String getTypeString() { return type; }
        public Player getOwner() { return owner; }
        public int getMoveCount() { return 0; }
        public int getDefensiveStrength() { return 0; }
        public int getAttackingStrength() { return 0; }
    }

    /**
     * Test stub for testing battle calculations from EpsilonBattleStrategy
     * (0,0) forest
     * (1,0) hill
     * (0,1) plains
     * (1,1) city
     * Red has units at (2,3), (3,2) and (3,3). Blue has one at (4,4)
     */
    class GameStubForEpsilonTesting implements Game {
        public Tile getTileAt(Position p) {
            if ( p.getRow() == 0 && p.getColumn() == 0 ) {
                return new StubTile(GameConstants.FOREST, 0, 0);
            }
            if ( p.getRow() == 1 && p.getColumn() == 0 ) {
                return new StubTile(GameConstants.HILLS, 1, 0);
            }
            return new StubTile(GameConstants.PLAINS, 0, 1);
        }
        public Unit getUnitAt(Position p) {
            if ( p.getRow() == 2 && p.getColumn() == 3 ||
                    p.getRow() == 3 && p.getColumn() == 2 ||
                    p.getRow() == 3 && p.getColumn() == 3 ) {
                return new StubUnit(GameConstants.ARCHER, Player.RED);
            }
            if ( p.getRow() == 4 && p.getColumn() == 4 ) {
                return new StubUnit(GameConstants.ARCHER, Player.BLUE);
            }
            return null;
        }
        public City getCityAt(Position p) {
            if ( p.getRow() == 1 && p.getColumn() == 1 ) {
                return new City() {
                    public Player getOwner() { return Player.RED; }
                    public int getSize() { return 1; }
                    public String getProduction() {
                        return null;
                    }
                    public String getWorkforceFocus() {
                        return null;
                    }
                };
            }
            return null;
        }

        // the rest is unused test stub methods...
        public void changeProductionInCityAt(Position p, String unitType) {}
        public void changeWorkForceFocusInCityAt(Position p, String balance) {}
        public void endOfTurn() {}
        public Player getPlayerInTurn() {return null;}
        public Player getWinner() {return null;}
        public int getAge() { return 0; }
        public boolean moveUnit(Position from, Position to) {return false;}
        public void performUnitActionAt( Position p ) {}
    }



}


