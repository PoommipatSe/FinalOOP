package Data;

/**
 * Created by Gluayz on 31/10/2559.
 */


import static Edit.Create.*;

public class TileGrid {

    public Tile[][] map,newmap;
    private int tilesWide;
    private int tilesHigh;

    public  TileGrid(){
        newmap = new Tile[30][15];
        this.tilesWide = newmap[0].length;
        this.tilesHigh = newmap.length;
        map = new Tile[tilesWide][tilesHigh];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile((i * 32), j * 32, 32, 32, TileType.Grass, 0);
            }
        }
    }

    public  void setTile(int x, int y, TileType type){
        map[x][y] = new Tile((x * 32), y * 32, 32, 32, type, 1);
    }


    public Tile getTile(int xPlace, int yPlace){
        if (xPlace < getTilesWide() && yPlace < getTilesHigh() && xPlace > -1 && yPlace > -1){
            return map[xPlace][yPlace];
        }
        else {
            return new Tile(0, 0, 0, 0, TileType.NULL, 0);
        }
    }

    public void Draw(){
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                map[i][j].Draw();
            }
        }
    }

    public int getTilesWide() {
        return tilesWide;
    }

    public void setTilesWide(int tilesWide) {
        this.tilesWide = tilesWide;
    }

    public int getTilesHigh() {
        return tilesHigh;
    }

    public void setTilesHigh(int tilesHigh) {
        this.tilesHigh = tilesHigh;
    }
}
