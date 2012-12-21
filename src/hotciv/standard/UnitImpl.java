package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

    private Player owner;
    private String unitType;
    private boolean fortified = false;
    private int attackStrength;
    private int defensiveStrength;

    public UnitImpl(Player player, String type) {
        owner = player;
        unitType = type;

        if (unitType.equals(GameConstants.ARCHER)) {
            defensiveStrength = 3;
            attackStrength = 2;
        } else if (unitType.equals(GameConstants.LEGION)) {
            defensiveStrength = 2;
            attackStrength = 4;
        } else if (unitType.equals(GameConstants.SETTLER)) {
            defensiveStrength = 3;
            attackStrength = 0;
        } else {
            defensiveStrength = 0;
        }

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
        if (fortified) {
            return defensiveStrength * 2;
        } else {
            return defensiveStrength;
        }
    }

    public int getAttackingStrength() {
        return attackStrength;
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