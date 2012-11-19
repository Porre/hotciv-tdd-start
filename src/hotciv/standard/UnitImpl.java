package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Orhoj
 * Date: 13-11-12
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
public class UnitImpl implements Unit {

    private Player owner;
    private String unitType;
    private int defensiveStrength = 3;
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
        return defensiveStrength;
    }

    public int getAttackingStrength() {
        return 0;
    }

    public void fortify() {
        defensiveStrength = 2*defensiveStrength;
        fortified = true;
    }

    public void deFortify() {
        defensiveStrength = defensiveStrength / 2;
        fortified = false;
    }

    public boolean isFortified() {
        return fortified;
    }

}