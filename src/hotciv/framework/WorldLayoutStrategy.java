package hotciv.framework;

import java.util.HashMap;

public interface WorldLayoutStrategy {

    public String[] getWorldLayout();

    public HashMap<Position, City> getCityLayout();

    public HashMap<Position, Unit> getUnitLayout();
}
