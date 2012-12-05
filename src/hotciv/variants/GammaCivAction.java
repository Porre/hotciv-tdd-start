package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

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
