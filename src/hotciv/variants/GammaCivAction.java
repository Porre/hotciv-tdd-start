package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Klaus Fyhn Jacobsen
 * Date: 19-11-12
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public class GammaCivAction implements UnitActionStrategy {

    @Override
    public void unitAction(Game game, Position position) {
        Unit u = game.getUnitAt(position);
        GameImpl gameImpl = (GameImpl) game;
        HashMap<Position, Unit> units = gameImpl.getUnits();
        HashMap<Position, City> cities = gameImpl.getCities();

        if (u.getTypeString().equals(GameConstants.SETTLER)) {
            units.remove(position);
            cities.put(position, new CityImpl(u.getOwner()));
        } else if (u.getTypeString().equals(GameConstants.ARCHER)) {
            UnitImpl archer = (UnitImpl) u;
            if (archer.isFortified()) {
                archer.deFortify();
            } else {
                archer.fortify();
            }
        }
    }

}
