package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * Created with IntelliJ IDEA.
 * User: OrhojLaptop
 * Date: 07-11-12
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class CityImpl implements City {

    public Player getOwner() {
        Player player = Player.RED;
        return player;
    }

    public int getSize() {
        return 0;
    }

    public String getProduction() {
        return null;
    }

    public String getWorkforceFocus() {
        return null;
    }
}
