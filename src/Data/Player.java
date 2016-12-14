package Data;

/**
 * Created by Gluayz on 6/11/2559.
 */

import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static Edit.Create.*;

public class Player {

    private TileGrid grid;
    private TileType[] types;
    private int index;
    private WaveCreate waveManager;
    private ArrayList<TowerShoot> towerList;
    private boolean leftMouseButtonDown;


    public Player(TileGrid grid, WaveCreate waveManager){
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Tower1;
        this.types[2] = TileType.Tower1;
        this.index = 1;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<TowerShoot>();
        this.leftMouseButtonDown = false;
    }

    public void setTile(){
        if (path()) {
            towerList.add(new TowerShoot(Twload("tw111"), grid.getTile(Mouse.getX() / 32, (HEIGHT - Mouse.getY() - 1) / 32), 10, waveManager.getCurrentWave().getEnemyList()));
            grid.setTile((int) Math.floor(Mouse.getX() / 32), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 32), TileType.Tower111);
            grid.setTile((int) Math.floor((Mouse.getX() / 32) + 1), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 32), TileType.Tower112);
            grid.setTile((int) Math.floor(Mouse.getX() / 32), (int) Math.floor(((HEIGHT - Mouse.getY() - 1) / 32) + 1), TileType.Tower113);
            grid.setTile((int) Math.floor((Mouse.getX() / 32)) + 1, (int) Math.floor(((HEIGHT - Mouse.getY() - 1) / 32)) + 1, TileType.Tower114);
        }
    }

    public void upDate(){
        for (TowerShoot t : towerList)
            t.update();

        if (Mouse.isButtonDown(0)){
            if (Mouse.getX() < 480){
                path();
                setTile();
            }
        }
    }

    public boolean path(){
        Tile myTile0 = grid.getTile((int) Math.floor(Mouse.getX() / 32), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 32));
        Tile myTile1 = grid.getTile((int) Math.floor(Mouse.getX() / 32)+1, (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 32));
        Tile myTile2 = grid.getTile((int) Math.floor((Mouse.getX() / 32)), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 32)+1);
        Tile myTile3 = grid.getTile((int) Math.floor(Mouse.getX() / 32)+1, (int) Math.floor(((HEIGHT - Mouse.getY() - 1) / 32) + 1));

        if (myTile0.getType() == TileType.Grass && myTile1.getType() == TileType.Grass && myTile2.getType() == TileType.Grass && myTile3.getType() == TileType.Grass){
            return true;
        }

        return false;
    }

    private void selectTower (){

    }

}

