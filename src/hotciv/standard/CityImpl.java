package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class CityImpl implements City {

    private Player owner;
    private String production;
    private String balance;
    private int productionTotal;
    private int foodTotal;
    private int size;

    public CityImpl(Player player) {
        owner = player;
        productionTotal = 0;
        foodTotal = 0;
        size = 1;
        balance = GameConstants.productionFocus;
        production = GameConstants.ARCHER;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public int getSize() {
        return size;
    }

    public void increaseCitySize() {
        size++;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String unitType) {
        production = unitType;
    }

    public String getWorkforceFocus() {
        return balance;
    }

    public void setWorkforceFocus(String focus) {
        balance = focus;
    }

    public void accumulateTotalProductionPoints(int value) {
        productionTotal += value;
    }

    public void accumulateTotalFoodPoints(int value) {
        foodTotal += value;
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

    public int getFoodTotal() {
        return foodTotal;
    }
}
