package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class AlphaCivWorldLayout implements WorldLayoutStrategy {

    private String[] layout;
    private HashMap<Position, City> cities;
    private HashMap<Position, Unit> units;

    public AlphaCivWorldLayout() {
        cities = new HashMap<Position, City>();
        cities.put(new Position(1, 1), new CityImpl(Player.RED));
        cities.put(new Position(4, 1), new CityImpl(Player.BLUE));

        units = new HashMap<Position, Unit>();
        units.put(new Position(2, 0), new UnitImpl(Player.RED, GameConstants.ARCHER));
        units.put(new Position(4, 3), new UnitImpl(Player.RED, GameConstants.SETTLER));
        units.put(new Position(3, 2), new UnitImpl(Player.BLUE, GameConstants.LEGION));

        layout = new String[] {
            "ohooooooooooooooo",
            ".oooooooooooooooo",
            "ooMoooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
            "ooooooooooooooooo",
        };
    }

    public String[] getWorldLayout() {
        return layout;
    }

    public HashMap<Position, City> getCityLayout() {
        return cities;
    }

    public HashMap<Position, Unit> getUnitLayout() {
        return units;
    }
}
