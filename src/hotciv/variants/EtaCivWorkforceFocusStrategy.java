package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.Utility;

import java.util.Iterator;

public class EtaCivWorkforceFocusStrategy implements WorkforceFocusStrategy {

    @Override
    public void increaseCitySize(Game game, Position position) {
        CityImpl c = (CityImpl) game.getCityAt(position);
        int needed = 5 + (c.getSize() * 3);

        if (c.getFoodTotal() >= needed && c.getSize() < 9) {
            c.increaseCitySize();
            c.accumulateTotalFoodPoints(c.getFoodTotal() * -1);
        }
    }

    @Override
    public int getIncreaseOfProduction(Game game, Position position) {
        CityImpl city = (CityImpl) game.getCityAt(position);
        int workers = city.getSize() - 1;
        int production = 1;
        int[] tiles = getAmountOfAllTileTypes(game, position);

        // If focus is food then we can't delegate those workers to production
        if (city.getWorkforceFocus().equals(GameConstants.foodFocus)) {
            workers -= tiles[0] + tiles[1];
        }

        // If workers are still left, let them go to forests (if any exist)
        if (workers >= tiles[2]) {
            production += tiles[2] * 3;
            workers -= tiles[2];
        } else if (workers < tiles[2] && workers > 0) {
            production += workers * 3;
            return production;
        }

        // If workers are still left, let them go to oceans (if any exist)
        if (workers >= tiles[3]) {
            production += tiles[3] * 2;
            workers -= tiles[3];
        } else if (workers < tiles[3] && workers > 0) {
            production += workers * 2;
            return production;
        }

        // If workers are still left, let them go to mountains (if any exist)
        if (workers >= tiles[4]) {
            production += tiles[4];
            workers -= tiles[4];
        } else if (workers < tiles[4] && workers > 0) {
            production += workers;
            return production;
        }

        return production;
    }

    @Override
    public int getIncreaseOfFood(Game game, Position position) {
        CityImpl city = (CityImpl) game.getCityAt(position);
        int workers = city.getSize() - 1;
        int food = 1;
        int[] tiles = getAmountOfAllTileTypes(game, position);

        // If focus is production then we can't delegate those workers to food
        if (city.getWorkforceFocus().equals(GameConstants.productionFocus)) {
            workers -= tiles[2] + tiles[3] + tiles[4];
        }

        // If workers are still left, let them go to plains (if any exist)
        if (workers >= tiles[0]) {
            food += tiles[0] * 3;
            workers -= tiles[0];
        } else if (workers < tiles[0] && workers > 0) {
            food += workers * 3;
            return food;
        }

        // If workers are still left, let them go to oceans (if any exist)
        if (workers >= tiles[1]) {
            food += tiles[1];
            workers -= tiles[1];
        } else if (workers < tiles[1] && workers > 0) {
            food += workers;
            return food;
        }

        return food;
    }

    private int[] getAmountOfAllTileTypes(Game game, Position position) {
        int[] result = new int[5];
        Iterator<Position> iterator = Utility.get8NeighborhoodIterator(position);
        while (iterator.hasNext()) {
            String tileType = game.getTileAt(iterator.next()).getTypeString();
            if (tileType.equals(GameConstants.PLAINS)) {
                result[0] += 1;
            } else if (tileType.equals(GameConstants.OCEANS)) {
                result[1] += 1;
            } else if (tileType.equals(GameConstants.FOREST)) {
                result[2] += 1;
            } else if (tileType.equals(GameConstants.HILLS)) {
                result[3] += 1;
            } else if (tileType.equals(GameConstants.MOUNTAINS)) {
                result[4] += 1;
            }
        }
        return result;
    }
}