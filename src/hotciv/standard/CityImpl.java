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
    private int productionPerTurn = 6;
    private int productionTotal;

    public CityImpl(Player player) {
        owner = player;
        productionTotal = 0;
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

    public void accumulateTotalProductionPoints() {
        productionTotal += productionPerTurn;
    }

    public void deductProductionPoints(String unitType) {
        if (unitType.equals(GameConstants.ARCHER)) {
            productionTotal -= 10;
        } else if (unitType.equals(GameConstants.LEGION)) {
            productionTotal -= 15;
        } else if (unitType.equals(GameConstants.SETTLER)) {
            productionTotal -= 30;
        }
    }

    public int getProductionTotal() {
        return productionTotal;
    }
}
