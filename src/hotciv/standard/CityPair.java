package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Position;

/**
 * Created with IntelliJ IDEA.
 * User: Orhoj
 * Date: 12-11-12
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class CityPair {

    private City city;
    private Position position;

    public CityPair(Position p, City c) {
        position = p;
        city = c;
    }

    public Position getPosition() {
        return position;
    }

    public City getCity() {
        return city;
    }
}