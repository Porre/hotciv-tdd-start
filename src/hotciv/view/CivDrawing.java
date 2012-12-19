package hotciv.view;

import hotciv.framework.*;

import java.awt.*;
import java.util.*;

import minidraw.framework.*;
import minidraw.standard.*;
import minidraw.standard.handlers.*;

public class CivDrawing extends StandardDrawing implements Drawing, GameObserver {

    protected Game game;
    protected Map<Unit, UnitFigure> figureMap = null;
    protected Map<Unit, Figure> cityMap = null;
    private ImageFigure turnShieldIcon, unitShieldIcon, cityShieldIcon,
            workforceFocusIcon, productionIcon;
    private TextFigure moveCounter, gameAge;

    public CivDrawing(DrawingEditor editor, Game game) {
        super();
        this.game = game;
        game.addObserver(this);
        defineUnitMap();
        defineIcons();
    }

    private void defineIcons() {
        Point turnShieldPoint = new Point(GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y);
        Point unitShieldPoint = new Point(GfxConstants.UNIT_SHIELD_X, GfxConstants.UNIT_SHIELD_Y);
        Point cityShieldPoint = new Point(GfxConstants.CITY_SHIELD_X, GfxConstants.CITY_SHIELD_Y);
        Point workforceFocusPoint = new Point(GfxConstants.WORKFORCEFOCUS_X, GfxConstants.WORKFORCEFOCUS_Y);
        Point productionPoint = new Point(GfxConstants.CITY_PRODUCTION_X, GfxConstants.CITY_PRODUCTION_Y);

        turnShieldIcon = new ImageFigure("redshield", turnShieldPoint);
        unitShieldIcon = new ImageFigure(GfxConstants.NOTHING, unitShieldPoint);
        cityShieldIcon = new ImageFigure(GfxConstants.NOTHING, cityShieldPoint);
        workforceFocusIcon = new ImageFigure(GfxConstants.NOTHING, workforceFocusPoint);
        productionIcon = new ImageFigure(GfxConstants.NOTHING, productionPoint);

        moveCounter = new TextFigure("", new Point(GfxConstants.UNIT_COUNT_X, GfxConstants.UNIT_COUNT_Y));
        gameAge = new TextFigure("", new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y));
        setGameAge(game.getAge());

        super.add(turnShieldIcon);
        super.add(unitShieldIcon);
        super.add(cityShieldIcon);
        super.add(workforceFocusIcon);
        super.add(productionIcon);
        super.add(moveCounter);
        super.add(gameAge);
    }


    public Figure add(Figure arg0) {
        throw new RuntimeException("Should not be used...");
    }

    private void defineUnitMap() {
        clearSelection();

        figureMap = new HashMap<Unit, UnitFigure>();
        Position p;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                p = new Position(r, c);
                Unit unit = game.getUnitAt(p);
                if (unit != null) {
                    String type = unit.getTypeString();
                    Point point = new Point(GfxConstants.getXFromColumn(p.getColumn()),
                            GfxConstants.getYFromRow(p.getRow()));
                    UnitFigure unitFigure =
                            new UnitFigure(type, point, unit);
                    unitFigure.addFigureChangeListener(this);
                    figureMap.put(unit, unitFigure);
                    super.add(unitFigure);
                }
            }
        }
    }



    // === Observer Methods ===

    public void worldChangedAt(Position pos) {
        System.out.println("UnitDrawing: world changes at " + pos);
        clearSelection();
        // this is a really brute-force algorithm: destroy
        // all known units and build up the entire set again
        for (Figure f : figureMap.values()) {
            super.remove(f);
        }

        defineUnitMap();
    }

    public void turnEnds(Player nextPlayer, int age) {
        // System.out.println("UnitDrawing: turnEnds for " + nextPlayer + " at " + age);
        String playername = "red";
        if (nextPlayer == Player.BLUE) {
            playername = "blue";
        }
        turnShieldIcon.set(playername + "shield", new Point(GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y));

        // defineUnitMap();
        setGameAge(age);
    }

    public void tileFocusChangedAt(Position position) {
        System.out.println("TEST");
    }




    /* Added help methods */

    private ImageFigure getBlackFigureAt(Point position) {
        return new ImageFigure(GfxConstants.NOTHING, position);
    }

    private void setGameAge(int age) {
        if (age < 0) {
            gameAge.setText((age * (-1)) + "BC");
        } else if (age == 0) {
            gameAge.setText("0");
        } else if (age > 0) {
            gameAge.setText(age + "AD");
        }
    }
}