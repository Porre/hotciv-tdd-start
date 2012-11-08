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
    private ArrayList<Tile> tiles = new ArrayList<Tile>();

    public GameImpl() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i == 1 && j == 0) {
                    tiles.add(new TileImpl(new Position(i,j), "Ocean"));
                } else if (i == 0 && j == 1) {
                    tiles.add(new TileImpl(new Position(i,j),"Hill"));
                } else if (i == 2 && j == 2) {
                    tiles.add(new TileImpl(new Position(i,j),"Mountain"));
                } else {
                    tiles.add(new TileImpl(new Position(i,j),"Plain"));
                }
            }
        }
     }

    public Tile getTileAt( Position p ) {
        for (int i = 0; i < tiles.size(); i++){
            Tile t = tiles.get(i);
            if (t.getPosition().equals(p)) {
                return t;
            }
        }
        return null;
    }

    public Unit getUnitAt( Position p ) { return null; }

    public City getCityAt(Position p) {
        return new CityImpl();
    }


    public Player getPlayerInTurn() { return null; }

    public Player getWinner() { return null; }
    public int getAge() { return 0; }

    public boolean moveUnit( Position from, Position to ) {
        Tile tileTo = null;
        for (int i = 0; i < tiles.size(); i++){
            Tile t = tiles.get(i);
            if (t.getPosition().equals(to)) {
                tileTo = t;
                break;
            }
        }

        if (tileTo.getTypeString().equals("Mountain") || tileTo.getTypeString().equals("Ocean")) {
            return false;
        } else {
            return true;
        }
    }

    public void endOfTurn() {}
    public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
    public void changeProductionInCityAt( Position p, String unitType ) {}
    public void performUnitActionAt( Position p ) {}
}
