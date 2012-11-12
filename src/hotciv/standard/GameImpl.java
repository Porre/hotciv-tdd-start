package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;

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
    private ArrayList<CityPair> cities;
    private Player currentPlayer;
    private int gameAge;

    public GameImpl() {
        // Initialize arraylist
        cities = new ArrayList<CityPair>();

        // Game starts in 4000 BC
        gameAge = -4000;

        // RED player starts
        currentPlayer = Player.RED;

        // Red city in (1,1), blue city in (4,1)
        City blueCity = new CityImpl(Player.BLUE);
        City redCity = new CityImpl(Player.RED);
        cities.add(new CityPair(new Position(1,1), redCity));
        cities.add(new CityPair(new Position(4,1), blueCity));

        // 16x16 array of tiles (the world)
        world = new Tile[16][16];

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

    public Unit getUnitAt( Position p ) { return null; }

    public City getCityAt(Position p) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getPosition().equals(p)) {
                return cities.get(i).getCity();
            }
        }
        return null;
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

        if (type.equals(GameConstants.MOUNTAINS) || type.equals(GameConstants.OCEANS)) {
            return false;
        } else {
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
    public void changeProductionInCityAt( Position p, String unitType ) {}
    public void performUnitActionAt( Position p ) {}
}
