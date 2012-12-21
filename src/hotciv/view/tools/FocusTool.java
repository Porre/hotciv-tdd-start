package hotciv.view.tools;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class FocusTool extends NullTool {

    private Game game;
    private DrawingEditor editor;
    private Position downPosition;

    public FocusTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        downPosition = GfxConstants.getPositionFromXY(x, y);
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        Position p = GfxConstants.getPositionFromXY(x, y);
        Unit unit = game.getUnitAt(p);
        City city = game.getCityAt(p);

        if (downPosition.equals(GfxConstants.getPositionFromXY(x, y))) {
            if (unit != null) {
                editor.showStatus("Inspect " + unit.getOwner() + " " + unit.getTypeString() + " at" + p);
            } else if (city != null) {
                editor.showStatus("Inspect " + city.getOwner() + " city at " + p);
            } else {
                editor.showStatus("You clicked on an empty tile");
            }
            game.setTileFocus(p);
        }
    }
}