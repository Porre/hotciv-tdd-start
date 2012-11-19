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
    public void unitAction(Game game, Position pos) {
        Unit u = game.getUnitAt(pos);
        HashMap<Position, Unit> units = ((GameImpl) game).getUnits();
        HashMap<Position, City> cities = ((GameImpl) game).getCities();

        if (u.getTypeString().equals(GameConstants.SETTLER)) {
            units.remove(pos);
            cities.put(pos, new CityImpl(u.getOwner()));
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
