package hotciv.framework;

import java.util.HashMap;

public interface WorldLayoutStrategy {

    /**
     * Returns a String array representing a world.
     * @return String array representing the world.
     */
    public String[] getWorldLayout();

    /**
     * Returns a HashMap holding cities and their positions
     * @return HashMap of cities
     */
    public HashMap<Position, City> getCityLayout();

    /**
     * Returns a HashMap holding units and their positions
     * @return HashMap of units
     */
    public HashMap<Position, Unit> getUnitLayout();
}
