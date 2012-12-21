package hotciv.view;

import hotciv.framework.*;

import java.awt.*;
import java.util.*;

import minidraw.framework.*;
import minidraw.standard.*;

public class CivDrawing extends StandardDrawing implements Drawing, GameObserver {

    protected Game game;
    protected Map<Unit, UnitFigure> figureMap = null;
    protected Map<City, CityFigure> cityMap = null;
    private ImageFigure turnShieldIcon, unitShieldIcon, cityShieldIcon,
            workforceFocusIcon, productionIcon;
    private Point turnShieldPoint, unitShieldPoint, cityShieldPoint, workforceFocusPoint, productionPoint;
    private TextFigure moveCounter, gameAge;

    public CivDrawing(DrawingEditor editor, Game game) {
        super();
        this.game = game;
        game.addObserver(this);
        defineUnitMap();
        defineCityMap();
        defineIcons();
    }

    private void defineIcons() {
        turnShieldPoint = new Point(GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y);
        unitShieldPoint = new Point(GfxConstants.UNIT_SHIELD_X, GfxConstants.UNIT_SHIELD_Y);
        cityShieldPoint = new Point(GfxConstants.CITY_SHIELD_X, GfxConstants.CITY_SHIELD_Y);
        workforceFocusPoint = new Point(GfxConstants.WORKFORCEFOCUS_X, GfxConstants.WORKFORCEFOCUS_Y);
        productionPoint = new Point(GfxConstants.CITY_PRODUCTION_X, GfxConstants.CITY_PRODUCTION_Y);

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

    private void defineCityMap() {
        clearSelection();

        cityMap = new HashMap<City, CityFigure>();
        Position p;

        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                p = new Position(i, j);
                City city = game.getCityAt(p);
                if (city != null) {
                    Point point = new Point(GfxConstants.getXFromColumn(p.getColumn()), GfxConstants.getYFromRow(p.getRow()));
                    CityFigure cityFigure = new CityFigure(city, point);
                    cityFigure.addFigureChangeListener(this);
                    cityMap.put(city, cityFigure);
                    super.add(cityFigure);
                }
            }
        }
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
        clearSelection();

        for (Figure f : cityMap.values()) {
            super.remove(f);
        }
        defineCityMap();

        for (Figure f : figureMap.values()) {
            super.remove(f);
        }
        defineUnitMap();
    }

    public void turnEnds(Player nextPlayer, int age) {
        String playername = "red";
        if (nextPlayer == Player.BLUE) {
            playername = "blue";
        }
        turnShieldIcon.set(playername + "shield", new Point(GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y));

        setGameAge(age);
    }

    public void tileFocusChangedAt(Position position) {
        City city = game.getCityAt(position);
        Unit unit = game.getUnitAt(position);
        if (city != null) {
            Player owner = city.getOwner();
            String player = "red";
            if (owner.equals(Player.BLUE)) {
                player = "blue";
            }
            String production = city.getProduction();
            String work = city.getWorkforceFocus();
            workforceFocusIcon.set(work, workforceFocusPoint);
            productionIcon.set(production, productionPoint);
            cityShieldIcon.set(player + "shield", cityShieldPoint);
        } else {
            workforceFocusIcon.set(GfxConstants.NOTHING, workforceFocusPoint);
            productionIcon.set(GfxConstants.NOTHING, productionPoint);
            cityShieldIcon.set(GfxConstants.NOTHING, cityShieldPoint);
        }

        if (unit != null) {
            String type = unit.getTypeString();
            Player owner = unit.getOwner();
            String player = "red";
            if (owner.equals(Player.BLUE))  {
                player = "blue";
            }
            unitShieldIcon.set(player + "shield", unitShieldPoint);
            moveCounter.setText(Integer.toString(unit.getMoveCount()));
        } else {
            unitShieldIcon.set(GfxConstants.NOTHING, unitShieldPoint);
            moveCounter.setText("");
        }
    }

    /* Added help methods */
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