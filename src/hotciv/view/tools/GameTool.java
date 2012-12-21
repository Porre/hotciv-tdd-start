package hotciv.view.tools;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class GameTool extends NullTool {

    private Tool moveUnitTool;
    private Tool focusTool;
    private Tool productionTool;
    private Tool endOfTurnTool;
    private Tool unitActionTool;

    public GameTool(DrawingEditor editor, Game game) {
        moveUnitTool = new MoveUnitTool(editor, game);
        focusTool = new FocusTool(editor, game);
        productionTool = new ProductionTool(editor, game);
        endOfTurnTool = new EndOfTurnTool(editor, game);
        unitActionTool = new UnitActionTool(editor, game);
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        endOfTurnTool.mouseDown(e, x, y);
        focusTool.mouseDown(e, x, y);
        moveUnitTool.mouseDown(e, x, y);
        productionTool.mouseDown(e, x, y);
        unitActionTool.mouseDown(e, x, y);
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        moveUnitTool.mouseUp(e, x, y);
        endOfTurnTool.mouseUp(e, x, y);
        focusTool.mouseUp(e, x, y);
        unitActionTool.mouseUp(e, x, y);
    }
}
