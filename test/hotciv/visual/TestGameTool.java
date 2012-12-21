package hotciv.visual;

import hotciv.factories.SemiCivFactory;
import hotciv.framework.Game;
import hotciv.standard.DieImpl;
import hotciv.standard.GameImpl;
import hotciv.view.tools.GameTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class TestGameTool {

    public static void main(String[] args) {

        Game game = new GameImpl(new SemiCivFactory(), new DieImpl());

        DrawingEditor editor = new MiniDrawApplication("HotCiv", new MiniDrawFactory(game));
        editor.open();
        editor.setTool(new GameTool(editor, game));
    }
}
