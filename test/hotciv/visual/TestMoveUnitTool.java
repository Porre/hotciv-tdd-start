package hotciv.visual;

import hotciv.framework.*;
import hotciv.stub.StubTile;
import hotciv.view.tools.MoveUnitTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

import java.util.HashMap;
import java.util.Map;

public class TestMoveUnitTool {

    public static void main(String[] args) {
        Game game = new StubGameUnitMove();

        DrawingEditor editor =
                new MiniDrawApplication("Click on archer to move",
                        new HotCivFactory4(game));
        editor.open();
        editor.setTool(new MoveUnitTool(editor, game));

        editor.showStatus("Invalid tile at [1,1]");
    }
}

class StubGameUnitMove implements Game {
    private Position archer;
    private GameObserver observer;

    public Unit getUnitAt( Position p ) {
        if (p.equals(archer)) {
            return new StubUnit(GameConstants.ARCHER, Player.RED);
        }
        return null;
    }
    public City getCityAt( Position p ) { return null; }
    public Player getPlayerInTurn() { return Player.RED; }
    public Player getWinner() { return null; }
    public int getAge() { return 0; }
    public boolean moveUnit( Position from, Position to ) {
        if (to.equals(new Position(1,1))) {
            return false;
        }
        archer = to;
        observer.worldChangedAt(from);
        return true;
    }
    public void endOfTurn() {}
    public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
    public void changeProductionInCityAt( Position p, String unitType ) {}
    public void performUnitActionAt( Position p ) {}

    public void addObserver(GameObserver observer) {
        this.observer = observer;
    }

    public void setTileFocus(Position position) {}

    public StubGameUnitMove() {
        defineWorld(1);
        archer = new Position(4, 5);
    }

    // A simple implementation to draw the map of DeltaCiv
    protected Map<Position,Tile> world;
    public Tile getTileAt( Position p ) { return world.get(p); }


    /** define the world.
     * @param worldType 1 gives one layout while all other
     * values provide a second world layout.
     */
    protected void defineWorld(int worldType) {
        world = new HashMap<Position,Tile>();
        String[] layout;
        if ( worldType == 1 ) {
            layout = new String[] {
                    "...ooMooooo.....",
                    "..ohhoooofffoo..",
                    ".oooooMooo...oo.",
                    ".ooMMMoooo..oooo",
                    "...ofooohhoooo..",
                    ".ofoofooooohhoo.",
                    "...ooo..........",
                    ".ooooo.ooohooM..",
                    ".ooooo.oohooof..",
                    "offfoooo.offoooo",
                    "oooooooo...ooooo",
                    ".ooMMMoooo......",
                    "..ooooooffoooo..",
                    "....ooooooooo...",
                    "..ooohhoo.......",
                    ".....ooooooooo..",
            };
        } else {
            layout = new String[] {
                    "...ooo..........",
                    ".ooooo.ooohooM..",
                    ".ooooo.oohooof..",
                    "offfoooo.offoooo",
                    "oooooooo...ooooo",
                    ".ooMMMoooo......",
                    ".....ooooooooo..",
                    "...ooMooooo.....",
                    "..ohhoooofffoo..",
                    "...ofooohhoooo..",
                    ".oooooMooo...oo.",
                    ".ooMMMoooo..oooo",
                    ".ofoofooooohhoo.",
                    "..ooooooffoooo..",
                    "....ooooooooo...",
                    "..ooohhoo.......",
            };
        }
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                world.put( p, new StubTile(p, type));
            }
        }
    }
}


class StubUnit implements Unit {
    private String type;
    private Player owner;

    public StubUnit(String type, Player owner) {
        this.type = type;
        this.owner = owner;
    }

    public String getTypeString() {
        return type;
    }

    public Player getOwner() {
        return owner;
    }

    public int getMoveCount() {
        return 1;
    }

    public int getDefensiveStrength() {
        return 0;
    }

    public int getAttackingStrength() {
        return 0;
    }
}

