package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class DeltaCivWorldLayout implements WorldLayoutStrategy {

    private String[] layout;
    private HashMap<Position, City> cities;
    private HashMap<Position, Unit> units;

    public DeltaCivWorldLayout() {
        cities = new HashMap<Position, City>();
        cities.put(new Position(8, 12), new CityImpl(Player.RED));
        cities.put(new Position(4, 5), new CityImpl(Player.BLUE));

        units = new HashMap<Position, Unit>();
        units.put(new Position(4, 4), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        units.put(new Position(8, 3), new UnitImpl(Player.BLUE, GameConstants.ARCHER));
        units.put(new Position(5, 5), new UnitImpl(Player.BLUE, GameConstants.SETTLER));

        layout = new String[] {
                "...ooMooooo.....",
                "..ohhoooofffoo..",
                ".oooooMooo...oo.",
                ".ooMMMoooo..oooo",
                "...ofooohhoooo..",
                ".ofoofooooohhoo.",
                "...ooo..........",
                ".ooooo.ooohooM..",
                ".ooooo.oohooof..",
                "offfoooo.offoooo",
                "oooooooo...ooooo",
                ".ooMMMoooo......",
                "..ooooooffoooo..",
                "....ooooooooo...",
                "..ooohhoo.......",
                ".....ooooooooo..",
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
