package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * Created with IntelliJ IDEA.
 * User: OrhojLaptop
 * Date: 07-11-12
 * Time: 13:49
 * To change this template use File | Settings | File Templates.
 */
public class TileImpl implements Tile {
    private Position position;
    private String type;

    public TileImpl(Position position, String type) {
        this.position = position;
        this.type = type;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getTypeString() {
        return type;
    }
}
