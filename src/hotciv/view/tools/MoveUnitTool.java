package hotciv.view.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;
import hotciv.view.GfxConstants;
import hotciv.framework.GameConstants;

import java.awt.event.MouseEvent;

public class MoveUnitTool extends NullTool {
    private Game game;
    private DrawingEditor editor;
    private Unit unit;
    private Position position;

    public MoveUnitTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        position = GfxConstants.getPositionFromXY(x, y);
        unit = game.getUnitAt(position);
        if(unit != null){
            editor.showStatus("Unit has been selected: " + unit.getTypeString());
        }
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        Position newPosition = GfxConstants.getPositionFromXY(x, y);

        boolean negativeX = newPosition.getColumn() >= 0;
        boolean negativeY = newPosition.getRow() >= 0;
        boolean outOfBounceX = newPosition.getColumn() <= GameConstants.WORLDSIZE;
        boolean outOfBounceY = newPosition.getRow() <= GameConstants.WORLDSIZE;

        if (unit != null) {
            if (negativeX && negativeY && outOfBounceX && outOfBounceY) {
                boolean validMove = game.moveUnit(position, newPosition);
                if (validMove) {
                    editor.showStatus(unit.getTypeString() + " has been moved from " + position + " to " + newPosition);
                } else {
                    editor.showStatus("Invalid move");
                }
            } else {
                editor.showStatus("New unit position out of bounce");
            }
        }
        unit = null;
    }
}
