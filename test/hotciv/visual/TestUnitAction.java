package hotciv.visual;

import hotciv.factories.SemiCivFactory;
import hotciv.framework.*;
import hotciv.standard.DieImpl;
import hotciv.standard.GameImpl;
import hotciv.stub.StubGame2;
import hotciv.stub.StubTile;
import hotciv.variants.GammaCivAction;
import hotciv.view.tools.EndOfTurnTool;
import hotciv.view.tools.UnitActionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: klausfyhn
 * Date: 19/12/12
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class TestUnitAction {

    public static void main(String[] args) {
        Game game = new GameImpl(new SemiCivFactory(), new DieImpl());

        DrawingEditor editor =
                new MiniDrawApplication("Test unit action",
                        new HotCivFactory4(game));
        editor.open();
        editor.setTool(new UnitActionTool(editor, game));

        editor.showStatus("Press a unit and shift to invoke its action");
    }
}
