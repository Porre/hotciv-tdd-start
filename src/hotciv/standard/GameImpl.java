package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

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
    private int gameAge;

    public GameImpl() {
        // Initialize HashMaps
        cities = new HashMap<Position, City>();
        units = new HashMap<Position, Unit>();

        // Game starts in 4000 BC
        gameAge = -4000;

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
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                world[i][j] = new TileImpl(new Position(i, j), GameConstants.PLAINS);
            }
        }

        world[1][0] = new TileImpl(new Position(1,0), GameConstants.OCEANS);
        world[0][1] = new TileImpl(new Position(1,0), GameConstants.HILLS);
        world[2][2] = new TileImpl(new Position(1,0), GameConstants.MOUNTAINS);
     }

    public Tile getTileAt( Position p ) {
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
        if (gameAge == -3000) {
            return Player.RED;
        } else {
            return null;
        }
    }

    public int getAge() {
        return gameAge;
    }

    public boolean moveUnit(Position from, Position to) {
        Tile destinationTile = world[to.getRow()][to.getColumn()];
        String type = destinationTile.getTypeString();
        Unit unitFrom = getUnitAt(from);
        Unit unitTo = getUnitAt(to);

        if (type.equals(GameConstants.MOUNTAINS) || type.equals(GameConstants.OCEANS)) {
            return false;
        } else if (unitTo != null) {
             if (unitFrom.getOwner().equals(unitTo.getOwner())) {
                 return false;
             } else {
                 units.remove(to);
                 units.remove(from);
                 units.put(to, unitFrom);
                 return true;
             }
        } else {
            // Valid move, remove unit from current position and move to next
            units.remove(from);
            units.put(to, unitFrom);
            return true;
        }
    }

    public void endOfTurn() {
        // Decide player turn
        if (getPlayerInTurn().equals(Player.RED)) {
            currentPlayer = Player.BLUE;
        } else if (getPlayerInTurn().equals(Player.BLUE)) {
            currentPlayer = Player.RED;
        }

        // Advance game age
        gameAge += 100;
    }

    public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

    public void changeProductionInCityAt( Position p, String unitType ) {
        CityImpl c = (CityImpl) cities.get(p);
        c.setProduction(unitType);
    }

    public void performUnitActionAt( Position p ) {}
}
