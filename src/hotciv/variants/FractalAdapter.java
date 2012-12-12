package hotciv.variants;

import hotciv.framework.*;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;

public class FractalAdapter implements WorldLayoutStrategy {

    private ThirdPartyFractalGenerator generator;

    public FractalAdapter() {
        generator = new ThirdPartyFractalGenerator();
    }

    public String[] getWorldLayout() {
        String[] result = new String[GameConstants.WORLDSIZE];
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            result[i] = new String();
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                result[i] += generator.getLandscapeAt(i, j);
            }
        }
        return result;
    }

    public HashMap<Position, City> getCityLayout() {
        return null;
    }

    public HashMap<Position, Unit> getUnitLayout() {
        return null;
    }
}