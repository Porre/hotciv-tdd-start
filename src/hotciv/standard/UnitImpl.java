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
        return 0;
    }

    public int getAttackingStrength() {
        return 0;
    }
}