package Data;

/**
 * Created by Gluayz on 14/12/2559.
 */
public class CheckPoint {
    private Tile tile;
    private int xDirection, yDirection;

    public CheckPoint(Tile tile, int xDirection, int yDirection) {
        this.tile = tile;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }



    public Tile getTile() {
        return tile;
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }
}
