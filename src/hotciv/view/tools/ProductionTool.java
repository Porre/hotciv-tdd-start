package hotciv.view.tools;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ProductionTool extends NullTool {

    private Game game;
    private DrawingEditor editor;
    private City city;
    private Position cPosition;

    public ProductionTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        Position p = GfxConstants.getPositionFromXY(x, y);

        if (game.getCityAt(p) != null) {
            cPosition = GfxConstants.getPositionFromXY(x, y);
            city = game.getCityAt(cPosition);
        }

        if (city != null && city.getOwner().equals(game.getPlayerInTurn()) && pointProduction(x, y)) {
            String production = city.getProduction();
            if (production.equals(GameConstants.ARCHER)) {
                game.changeProductionInCityAt(cPosition, GameConstants.LEGION);
                editor.showStatus(city.getOwner() + " has changed production to LEGION in city at " + cPosition);
            } else if (production.equals(GameConstants.LEGION)) {
                game.changeProductionInCityAt(cPosition, GameConstants.SETTLER);
                editor.showStatus(city.getOwner() + " has changed production to SETTLER in city at " + cPosition);
            } else if (production.equals(GameConstants.SETTLER)) {
                game.changeProductionInCityAt(cPosition, GameConstants.ARCHER);
                editor.showStatus(city.getOwner() + " has changed production to ARCHER in city at " + cPosition);
            }
            city = null;
            cPosition = null;
        } else if (city != null && city.getOwner().equals(game.getPlayerInTurn()) && pointWorkFocus(x, y)) {
            String focus = city.getWorkforceFocus();
            if (focus.equals(GameConstants.productionFocus)) {
                game.changeWorkForceFocusInCityAt(cPosition, GameConstants.foodFocus);
                editor.showStatus(city.getOwner() + " has changed workforce focus to food in city at " + cPosition);
            } else if (focus.equals(GameConstants.foodFocus)) {
                game.changeWorkForceFocusInCityAt(cPosition, GameConstants.productionFocus);
                editor.showStatus(city.getOwner() + " has changed workforce focus to production in city at " + cPosition);
            }
            city = null;
            cPosition = null;
        }
    }

    private boolean pointWorkFocus(int x, int y) {
        if (x > GfxConstants.WORKFORCEFOCUS_X && x < GfxConstants.WORKFORCEFOCUS_X + 30
                && y > GfxConstants.WORKFORCEFOCUS_Y && y < GfxConstants.WORKFORCEFOCUS_Y + 30) {
            return true;
        }
        return false;
    }

    private boolean pointProduction(int x, int y) {
        if (x > GfxConstants.CITY_PRODUCTION_X && x < GfxConstants.CITY_PRODUCTION_X + 45
                && y > GfxConstants.CITY_PRODUCTION_Y && y < GfxConstants.CITY_PRODUCTION_Y + 49) {
            return true;
        }
        return false;
    }
}