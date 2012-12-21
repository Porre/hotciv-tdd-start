package hotciv.view.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class UnitActionTool extends NullTool {
    private Game game;
    private DrawingEditor editor;
    private Unit unit;
    private Position position;
    private int xCor;
    private int yCor;

    public UnitActionTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        position = GfxConstants.getPositionFromXY(x, y);
        unit = game.getUnitAt(position);
        if (unit != null) {
            xCor = x;
            yCor = y;
        }
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        position = GfxConstants.getPositionFromXY(x, y);
        unit = game.getUnitAt(position);

        if (x == xCor && y == yCor) {
            if (e.isShiftDown()) {
                game.performUnitActionAt(position);
                editor.showStatus(unit.getTypeString() + " performed action");
            }
        }
    }
}
