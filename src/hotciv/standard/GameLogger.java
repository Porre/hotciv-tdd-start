package hotciv.standard;

import hotciv.framework.*;

public class GameLogger implements Game {

    private Game game;

    public GameLogger(Game game) {
        this.game = game;
    }

    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    public Player getWinner() {
        return game.getWinner();
    }

    public int getAge() {
        return game.getAge();
    }

    public boolean moveUnit(Position from, Position to) {
        Unit unit = game.getUnitAt(from);
        boolean validMove = false;
        if (validMove = game.moveUnit(from, to)) {
            System.out.println(unit.getOwner() + " moves " + unit.getTypeString() + " from (" + from.getRow() + "," + from.getColumn() + ") to (" + to.getRow() + "," + to.getColumn() + ").");
        }
        return validMove;
    }

    public void endOfTurn() {
        System.out.println(game.getPlayerInTurn() + " ends turn.");
        game.endOfTurn();
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        City city = game.getCityAt(p);
        game.changeWorkForceFocusInCityAt(p, balance);
        System.out.println(city.getOwner() + " changes work force focus in city at (" + p.getRow() + "," + p.getColumn() + ") to " + balance);
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        City city = game.getCityAt(p);
        game.changeProductionInCityAt(p, unitType);
        System.out.println(city.getOwner() + " changes production in city at (" + p.getRow() + "," + p.getColumn() + ") to " + unitType);
    }

    public void performUnitActionAt(Position p) {
        Unit unit = game.getUnitAt(p);
        game.performUnitActionAt(p);
        System.out.println(unit.getOwner() + " is performing " + unit.getTypeString() + " action (" + p.getRow() + "," + p.getColumn() + ")");
    }

    @Override
    public void addObserver(GameObserver observer) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTileFocus(Position position) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
