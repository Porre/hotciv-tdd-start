package hotciv.view.tools;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;
import hotciv.view.GfxConstants;

/**
 * Created with IntelliJ IDEA.
 * User: klausfyhn
 * Date: 19/12/12
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */
public class EndOfTurnTool extends NullTool{
    private Game game;
    private DrawingEditor editor;
    private int xCor;
    private int yCor;
    private int turnShieldX;
    private int turnShieldY;
    private int imageWidth;
    private int imageHight;

    public EndOfTurnTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
        turnShieldX = GfxConstants.TURN_SHIELD_X;
        turnShieldY = GfxConstants.TURN_SHIELD_Y;
        imageHight = 40;
        imageWidth = 30;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        if (x > turnShieldX && x < turnShieldX + imageWidth && y > turnShieldY && y < turnShieldY + imageHight) {
            xCor = x;
            yCor = y;
        }
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        if(xCor == x && yCor == y) { // Defines a click
            game.endOfTurn();
            editor.showStatus("It is now " + game.getPlayerInTurn() + " Turn");
        }
    }
}
