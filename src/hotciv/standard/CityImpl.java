package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;

/**
 * Created with IntelliJ IDEA.
 * User: OrhojLaptop
 * Date: 07-11-12
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class CityImpl implements City {

    private Player owner;
    private String production;

    public CityImpl(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public int getSize() {
        return 1;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String unitType) {
        production = unitType;
    }

    public String getWorkforceFocus() {
        return GameConstants.productionFocus;
    }

    public int getTotalProductionPoints() {
        return 0;
    }

}
