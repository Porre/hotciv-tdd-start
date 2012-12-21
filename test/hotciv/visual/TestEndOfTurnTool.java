package hotciv.visual;

import hotciv.framework.*;
import hotciv.stub.StubTile;
import hotciv.view.tools.EndOfTurnTool;
import hotciv.view.tools.MoveUnitTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: klausfyhn
 * Date: 19/12/12
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class TestEndOfTurnTool {
    public static void main(String[] args) {
        Game game = new StubEndOfTurnGame();

        DrawingEditor editor =
                new MiniDrawApplication("Test end of turn",
                        new HotCivFactory4(game));
        editor.open();
        editor.setTool(new EndOfTurnTool(editor, game));

        editor.showStatus("Press end of turn to see the turn end");
    }
}

class StubEndOfTurnGame implements Game {
    private GameObserver observer;
    private Player currentPlayer;

    public Unit getUnitAt( Position p ) {
        return null;
    }
    public City getCityAt( Position p ) { return null; }
    public Player getPlayerInTurn() { return currentPlayer; }
    public Player getWinner() { return null; }
    public int getAge() { return 0; }
    public boolean moveUnit( Position from, Position to ) {return true;}

    public void endOfTurn() {
        if (currentPlayer.equals(Player.RED)) {
            currentPlayer = Player.BLUE;
        } else {
            currentPlayer = Player.RED;
        }
    }
    public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
    public void changeProductionInCityAt( Position p, String unitType ) {}
    public void performUnitActionAt( Position p ) {}

    public void addObserver(GameObserver observer) {
        this.observer = observer;
    }

    public void setTileFocus(Position position) {}

    public StubEndOfTurnGame() {
        defineWorld(1);
        currentPlayer = Player.RED;
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
