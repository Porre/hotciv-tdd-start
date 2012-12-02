package hotciv.standard;

import hotciv.factories.AbstractFactory;
import hotciv.framework.*;

import java.util.*;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
*/

public class GameImpl implements Game {

    private Tile[][] world;
    private HashMap<Position, City> cities;
    private HashMap<Position, Unit> units;
    private Player currentPlayer;
    private AgeStrategy ageStrategy;
    private WinStrategy winStrategy;
    private UnitActionStrategy unitActionStrategy;
    private WorkforceFocusStrategy workforceFocusStrategy;
    private WorldLayoutStrategy worldStrategy;
    private BattleStrategy battleStrategy;
    private Die die;
    private int roundNumber;

    public GameImpl(AbstractFactory factory, Die d) {
        ageStrategy = factory.getAgeStrategy();
        winStrategy = factory.getWinStrategy();
        unitActionStrategy = factory.getUnitActionStrategy();
        worldStrategy = factory.getWorldLayoutStrategy();
        workforceFocusStrategy = factory.getWorkforceFocusStrategy();
        battleStrategy = factory.getBattleStrategy();

        cities = worldStrategy.getCityLayout();
        units = worldStrategy.getUnitLayout();

        // RED player starts
        currentPlayer = Player.RED;

        die = d;

        roundNumber = 1;

        // 16x16 array of tiles (the world)
        world = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        generateWorld();
    }

    public Tile getTileAt(Position p) {
        return world[p.getRow()][p.getColumn()];
    }

    public Unit getUnitAt(Position p) {
        return units.get(p);
    }

    public City getCityAt(Position p) {
        return cities.get(p);
    }

    public Player getPlayerInTurn() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winStrategy.getWinner(this);
    }

    public int getAge() {
        return ageStrategy.getAge();
    }

    public boolean moveUnit(Position from, Position to) {
        Tile destinationTile = world[to.getRow()][to.getColumn()];
        String type = destinationTile.getTypeString();
        Unit unitFrom = getUnitAt(from);
        Unit unitTo = getUnitAt(to);
        CityImpl city = (CityImpl) getCityAt(to);

        if (type.equals(GameConstants.MOUNTAINS) || type.equals(GameConstants.OCEANS)) {
            return false;
        } else if (unitTo != null && unitFrom.getOwner().equals(unitTo.getOwner())) {
            return false;
        } else if (((UnitImpl) unitFrom).isFortified())  {
            return false;
        } else if (!unitFrom.getOwner().equals(currentPlayer)) {
            return false;
        } else if (unitTo != null) {
            if (battleStrategy.getBattleResult(this, from, to, die)) {
                units.remove(to);
            } else {
                units.remove(from);
                return true;
            }
        }

        units.remove(from);
        units.put(to, unitFrom);

        if (city != null && city.getOwner() != unitFrom.getOwner()) {
            city.setOwner(getPlayerInTurn());
        }
        return true;
    }

    public void endOfTurn() {
        // Switch player turns and progress age
        if (getPlayerInTurn().equals(Player.RED)) {
            currentPlayer = Player.BLUE;
        } else if (getPlayerInTurn().equals(Player.BLUE)) {
            currentPlayer = Player.RED;
            ageStrategy.progressAge();
            roundNumber++;
        }

        // Accumulate production/food points for cities
        ArrayList<Position> cityList = getCityPositionsOwnedByPlayer(currentPlayer);
        for (int i = 0; i < cityList.size(); i++) {
            CityImpl city = (CityImpl) cities.get(cityList.get(i));
            city.accumulateTotalProductionPoints(workforceFocusStrategy.getIncreaseOfProduction(this, cityList.get(i)));
            city.accumulateTotalFoodPoints(workforceFocusStrategy.getIncreaseOfFood(this, cityList.get(i)));
            workforceFocusStrategy.increaseCitySize(this, cityList.get(i));
        }

        // Create new units and deduct production
        handleUnitCreation(currentPlayer);
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {}

    public void changeProductionInCityAt(Position p, String unitType) {
        CityImpl c = (CityImpl) cities.get(p);
        c.setProduction(unitType);
    }

    public void performUnitActionAt(Position p) {
        unitActionStrategy.unitAction(this, p);
    }

    private void handleUnitCreation(Player player) {
        // Handle unit production for all cities owner by 'player'
        for (Map.Entry entry : cities.entrySet()) {
            Position position = (Position) entry.getKey();
            CityImpl city = (CityImpl) entry.getValue();
            String unitType = city.getProduction();
            ArrayList<Position> positions = getValidUnitPositionsList(position);

            // Create unit if owned by player and there is enough production
            if (city.getOwner().equals(player) && unitType != null && !positions.isEmpty()) {
                if (unitType.equals(GameConstants.ARCHER) && city.getProductionTotal() >= 10) {
                    units.put(positions.get(0), new UnitImpl(player, GameConstants.ARCHER));
                    city.deductProductionPoints(unitType);
                } else if (unitType.equals(GameConstants.LEGION) && city.getProductionTotal() >= 15) {
                    units.put(positions.get(0), new UnitImpl(player, GameConstants.LEGION));
                    city.deductProductionPoints(unitType);
                } else if (unitType.equals(GameConstants.SETTLER) && city.getProductionTotal() >= 30) {
                    units.put(positions.get(0), new UnitImpl(player, GameConstants.SETTLER));
                    city.deductProductionPoints(unitType);
                }
            }
        }
    }

    private ArrayList<Position> getValidUnitPositionsList(Position position) {
        int row = position.getRow();
        int col = position.getColumn();

        ArrayList<Position> positions = new ArrayList<Position>();
        ArrayList<Position> result = new ArrayList<Position>();

        Position north = new Position(row - 1, col);
        Position northEast = new Position(row - 1, col + 1);
        Position east = new Position(row, col + 1);
        Position southEast = new Position(row + 1, col + 1);
        Position south = new Position(row + 1, col);
        Position southWest = new Position(row + 1, col - 1);
        Position west = new Position(row, col - 1);
        Position northWest = new Position(row - 1, col - 1);

        positions.add(position);
        positions.add(north);
        positions.add(northEast);
        positions.add(east);
        positions.add(southEast);
        positions.add(south);
        positions.add(southWest);
        positions.add(west);
        positions.add(northWest);

        for (int i = 0; i < positions.size(); i++) {
            if (isValidPositionForNewUnit(positions.get(i))) {
                result.add(positions.get(i));
            }
        }
        return result;
    }

    public boolean isValidPositionForNewUnit(Position position) {
        int row = position.getRow();
        int col = position.getColumn();
        Tile tile = getTileAt(position);
        Unit unit = getUnitAt(position);
        String tileType = tile.getTypeString();

        if (row >= 0 && row < GameConstants.WORLDSIZE && col >= 0 && col < GameConstants.WORLDSIZE) {
            if (unit == null && !tileType.equals(GameConstants.OCEANS) && !tileType.equals(GameConstants.MOUNTAINS)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void generateWorld() {
        String line;
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            line = worldStrategy.getWorldLayout()[i];
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                char tileChar = line.charAt(j);
                String type = "error";
                if (tileChar == '.') {
                    type = GameConstants.OCEANS;
                } else if (tileChar == 'o') {
                    type = GameConstants.PLAINS;
                } else if (tileChar == 'M') {
                    type = GameConstants.MOUNTAINS;
                } else if (tileChar == 'f') {
                    type = GameConstants.FOREST;
                } else if (tileChar == 'h') {
                    type = GameConstants.HILLS;
                }
                world[i][j] = new TileImpl(new Position(i, j), type);
            }
        }
    }

    public ArrayList<Position> getCityPositionsOwnedByPlayer(Player player) {
        ArrayList<Position> list = new ArrayList<Position>();
        for (Map.Entry c : cities.entrySet()) {
            City city = (City) c.getValue();
            Position position = (Position) c.getKey();
            if (city.getOwner().equals(player)) {
                list.add(position);
            }
        }
        return list;
    }

    public HashMap<Position, Unit> getUnits() {
        return units;
    }

    public HashMap<Position, City> getCities() {
        return cities;
    }

    public int getRound() {
        return roundNumber;
    }

}