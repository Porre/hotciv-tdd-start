package hotciv.standard;

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

    public GameImpl(AgeStrategy age, WinStrategy win) {
        // Set strategies
        ageStrategy = age;
        winStrategy = win;

        // Initialize HashMaps
        cities = new HashMap<Position, City>();
        units = new HashMap<Position, Unit>();

        // RED player starts
        currentPlayer = Player.RED;

        // Red city in (1,1), blue city in (4,1)
        cities.put(new Position(1,1), new CityImpl(Player.RED));
        cities.put(new Position(4,1), new CityImpl(Player.BLUE));

        // Red archer at (2,0), blue legion at (3,2) and red settler at (4,3)
        units.put(new Position(2,0), new UnitImpl(Player.RED, GameConstants.ARCHER));
        units.put(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION));
        units.put(new Position(4,3), new UnitImpl(Player.RED, GameConstants.SETTLER));

        // 16x16 array of tiles (the world)
        world = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

        // Everything is plains except (1,0), (0,1), (2,2)
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                world[i][j] = new TileImpl(new Position(i, j), GameConstants.PLAINS);
            }
        }

        world[1][0] = new TileImpl(new Position(1,0), GameConstants.OCEANS);
        world[0][1] = new TileImpl(new Position(1,0), GameConstants.HILLS);
        world[2][2] = new TileImpl(new Position(1,0), GameConstants.MOUNTAINS);
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
        } else {
            units.remove(to);
            units.remove(from);
            units.put(to, unitFrom);

            if (city != null) {
                city.setOwner(getPlayerInTurn());
            }
            return true;
        }
    }

    public void endOfTurn() {
        // Decide player turn
        if (getPlayerInTurn().equals(Player.RED)) {
            currentPlayer = Player.BLUE;
            if (ageStrategy.getAge() != -4000) {
                CityImpl blueCity = (CityImpl) cities.get(new Position(4,1));
                blueCity.accumulateTotalProductionPoints();
            }
        } else if (getPlayerInTurn().equals(Player.BLUE)) {
            currentPlayer = Player.RED;
            CityImpl redCity = (CityImpl) cities.get(new Position(1,1));
            redCity.accumulateTotalProductionPoints();

            // Advance game age only when it is red players turn again
            ageStrategy.progressAge();
        }
        handleUnitCreation(currentPlayer);
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {}

    public void changeProductionInCityAt(Position p, String unitType) {
        CityImpl c = (CityImpl) cities.get(p);
        c.setProduction(unitType);
    }

    public void performUnitActionAt(Position p) {}

    private void handleUnitCreation(Player player) {
        // Handle unit production for all cities owner by 'player'
        for (Map.Entry entry : cities.entrySet()) {
            Position position = (Position) entry.getKey();
            CityImpl city = (CityImpl) entry.getValue();
            String unitType = city.getProduction();

            // Create unit if owned by player and there is enough production
            if (city.getOwner().equals(player) && unitType != null) {
                if (unitType.equals(GameConstants.ARCHER) && city.getProductionTotal() >= 10) {
                    unitPlacement(position, new UnitImpl(player, GameConstants.ARCHER), (CityImpl) city);
                } else if (unitType.equals(GameConstants.LEGION) && city.getProductionTotal() >= 15) {
                    unitPlacement(position, new UnitImpl(player, GameConstants.LEGION), (CityImpl)  city);
                } else if (unitType.equals(GameConstants.SETTLER) && city.getProductionTotal() >= 30) {
                    unitPlacement(position, new UnitImpl(player, GameConstants.SETTLER), (CityImpl)  city);
                }
            }
        }
    }

    private void unitPlacement(Position position, Unit unit, CityImpl city) {
        int row = position.getRow();
        int col = position.getColumn();

        ArrayList<Position> positions = new ArrayList<Position>();

        Position north = new Position(row - 1, col);
        Position northEast = new Position(row - 1, col + 1);
        Position east = new Position(row, col + 1);
        Position southEast = new Position(row + 1, col + 1);
        Position south = new Position(row + 1, col);
        Position southWest = new Position(row + 1, col - 1);
        Position west = new Position(row, col - 1);
        Position northWest = new Position(row - 1, col - 1);

        positions.add(north);
        positions.add(northEast);
        positions.add(east);
        positions.add(southEast);
        positions.add(south);
        positions.add(southWest);
        positions.add(west);
        positions.add(northWest);

        // If given position is free (the city position) just place unit there,
        // otherwise check clockwise for a free position.

        if (isValidPositionForUnit(position)) {
            units.put(position, unit);
        } else {
            for (Position p : positions) {
                if (isValidPositionForUnit(p)) {
                    units.put(p, unit);
                    city.deductProductionPoints(unit.getTypeString());
                    break;
                }
            }
        }
    }

    private boolean isValidPositionForUnit(Position position) {
        int row = position.getRow();
        int col = position.getColumn();
        Tile tile = getTileAt(position);
        String tileType = tile.getTypeString();

        // Checks if position is within bounds (we will sometimes check for positions outside the grid)
        // and that it is not OCEANS or MOUNTAINS. If no unit is here we can safely place one.
        if (row >= 0 && row < GameConstants.WORLDSIZE && col >= 0 && col < GameConstants.WORLDSIZE) {
            if (getUnitAt(position) == null && !tileType.equals(GameConstants.OCEANS) && !tileType.equals(GameConstants.MOUNTAINS)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<City> getCities(Player player) {
        ArrayList<City> list = new ArrayList<City>();
        for (Map.Entry c : cities.entrySet()) {
            City city = (City) c.getValue();
            if (city.getOwner().equals(player)) {
                list.add(city);
            }
        }
        return list;
    }
}
