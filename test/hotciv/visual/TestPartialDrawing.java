package hotciv.visual;

import hotciv.view.tools.FocusTool;
import hotciv.view.tools.ProductionTool;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/**
 * Testing the template implementation of a game specific
 * Drawing role.
 * <p/>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com/
 */
public class TestPartialDrawing {

    public static void main(String[] args) {
        Game game = new StubGame2();

        DrawingEditor editor =
                new MiniDrawApplication("Click anywhere to see Drawing updates",
                        new HotCivFactory4(game));
        editor.open();

        // This stub has been used for testing different tools and as such they have been commented out.
        // editor.setTool(new UpdateTool(editor, game));
        // editor.setTool(new FocusTool(editor, game));

        editor.setTool(new ProductionTool(editor, game));


        editor.showStatus("Click anywhere to state changes reflected on the GUI");

        // Try to set the selection tool instead to see
        // completely free movement of figures, including the icon

        // editor.setTool( new SelectionTool(editor) );
    }
}

/**
 * A tool that simply 'does something' new every time
 * the mouse is clicked anywhere
 */
class UpdateTool extends NullTool {
    private Game game;
    private DrawingEditor editor;

    public UpdateTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    private int count = 0;

    public void mouseDown(MouseEvent e, int x, int y) {
        switch (count) {
            case 0: {
                editor.showStatus("State change: Moving archer to (1,1)");
                game.moveUnit(new Position(2, 0), new Position(1, 1));
                break;
            }
            case 1: {
                editor.showStatus("State change: Moving archer to (2,2)");
                game.moveUnit(new Position(1, 1), new Position(2, 2));
                break;
            }
            case 2: {
                editor.showStatus("State change: End of Turn (over to blue)");
                game.endOfTurn();
                break;
            }
            case 3: {
                editor.showStatus("State change: End of Turn (over to red)");
                game.endOfTurn();
                break;
            }
            case 4: {
                editor.showStatus("State change: Inspecting red city at (5,5)");
                game.setTileFocus(new Position(1, 1));
                break;
            }
            case 5: {
                editor.showStatus("State change: Inspecting red archer at (2,2)");
                game.setTileFocus(new Position(2, 2));
                break;
            }
            case 6: {
                editor.showStatus("State change: Inspecting blue legion at (3,2)");
                game.setTileFocus(new Position(3, 2));
                break;
            }
            case 7: {
                editor.showStatus("State change: Inspecting blue city at (4,1)");
                game.setTileFocus(new Position(4, 1));
                break;
            }
            default: {
                editor.showStatus("No more changes in my list...");
            }
        }
        count++;
    }
}