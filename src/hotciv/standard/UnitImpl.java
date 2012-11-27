package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

    private Player owner;
    private String unitType;
    private boolean fortified = false;

    public UnitImpl(Player player, String type) {
        owner = player;
        unitType = type;
    }

    public String getTypeString() {
        return unitType;
    }

    public Player getOwner() {
        return owner;
    }

    public int getMoveCount() {
        return 0;
    }

    public int getDefensiveStrength() {
        if (unitType.equals(GameConstants.ARCHER)) {
            if (fortified) {
                return 6;
            } else {
                return 3;
            }
        } else if (unitType.equals(GameConstants.LEGION)) {
            return 2;
        } else if (unitType.equals(GameConstants.SETTLER)) {
            return 3;
        } else {
            return 0;
        }
    }

    public int getAttackingStrength() {
        if (unitType.equals(GameConstants.ARCHER)) {
            return 2;
        } else if (unitType.equals(GameConstants.LEGION)) {
            return 4;
        } else if (unitType.equals(GameConstants.SETTLER)) {
            return 0;
        } else {
            return 0;
        }
    }

    public void fortify() {
        fortified = true;
    }

    public void deFortify() {
        fortified = false;
    }

    public boolean isFortified() {
        return fortified;
    }

}