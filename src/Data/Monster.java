package Data;

import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Edit.Create.*;
import static Edit.Time.*;

/**
 * Created by Gluayz on 10/11/2559.
 */
public class Monster {
    private int width,height,health,currCheckPoint;
    private  float speed;
    private float x;
    private float y;
    private Texture texture;
    private Tile startTile;
    private boolean first = true, alive = true;
    private TileGrid grid;

    private ArrayList<CheckPoint> checkPoints;
    private int[] directions;

    public Monster(Texture texture, Tile startTile, TileGrid grid, int width, int height, float speed){
        this.setTexture(texture);
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.grid = grid;

        this.checkPoints = new ArrayList<CheckPoint>();
        this.directions = new int[2];
        //X direction
        this.directions[0] = 0;
        //Y direction
        this.directions[1] = 0;
        directions = FindNextD(startTile);
        PopulateCheckpointList();



    }

    public void Update(){
        if (isFirst()){
            first = false;
        }
        else {
            if (CheckpointReached()) {
                if (currCheckPoint + 1 == checkPoints.size())
                    Die();
                else
                    currCheckPoint++;
            } else {
                x += Delta() * checkPoints.get(currCheckPoint).getxDirection() * speed;
                y += Delta() * checkPoints.get(currCheckPoint).getyDirection() * speed;
            }
        }

    }

    private boolean CheckpointReached() {
        boolean reached = false;
        Tile t = checkPoints.get(currCheckPoint).getTile();
        //Check if position reached tile within variance of 3 (arbitrary)
        if (x > t.getX() - 3 && x < t.getX() + 3 && y > t.getY() - 3 && y < t.getY() + 3) {
            reached = true;
            x = t.getX();
            y = t.getY();
        }
        return reached;
    }

    private void PopulateCheckpointList() {
        checkPoints.add(FindNextC(startTile, directions = FindNextD(startTile)));
        int counter = 0;
        boolean cont = true;
        while (cont) {
            int[] currentD = FindNextD(checkPoints.get(counter).getTile());
            //Check if a next direction/checkpoint exists, end after 20 checkpoints (arbitrary)
            if (currentD[0] == 2 || counter == 20) {
                cont = false;
            } else {
                checkPoints.add(FindNextC(checkPoints.get(counter).getTile(), directions = FindNextD(checkPoints.get(counter).getTile())));
            }
            counter++;
        }
    }

    private CheckPoint FindNextC(Tile s, int[] dir) {
        Tile next = null;
        CheckPoint c = null;

        //Boolean to decide if next checkpoint is found
        boolean found = false;

        //Integer to increment each loop
        int counter = 1;

        while (!found) {
            if (s.getType() != grid.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter).getType()) {
                found = true;
                //Move counter back 1 to find tile before new tiletype
                counter -= 1;
                next = grid.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter);
            }
            counter++;
        }
        c = new CheckPoint(next, dir[0], dir[1]);
        return c;
    }

    private int[] FindNextD(Tile s) {
        int[] dir = new int[2];
        Tile u = grid.getTile(s.getXPlace(), s.getYPlace() - 1); //up
        Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace()); //right
        Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1); //down
        Tile l = grid.getTile(s.getXPlace() - 1, s.getYPlace()); //left

        if (s.getType() == u.getType() && directions[1] != 1) {
            dir[0] = 0;
            dir[1] = -1;
        } else if (s.getType() == r.getType() && directions[0] != -1) {
            dir[0] = 1;
            dir[1] = 0;
        } else if (s.getType() == d.getType() && directions[1] != -1) {
            dir[0] = 0;
            dir[1] = 1;
        } else if (s.getType() == l.getType() && directions[0] != 1) {
            dir[0] = -1;
            dir[1] = 0;
        } else {
            dir[0] = 2;
            dir[1] = 2;
        }

        return dir;
    }

    public int path(){
        Tile myTileD = grid.getTile((int)x/32,(int)y/32);
        Tile nextTileD = grid.getTile((int)(x/32),(int)(y/32)+1);

        Tile myTileU = grid.getTile((int)x/32,(int)y/32);
        Tile nextTileU = grid.getTile((int)(x/32)-1,(int)(y/32));

        Tile myTileR = grid.getTile((int)x/32,(int)y/32);
        Tile nextTileR = grid.getTile((int)(x/32)+1,(int)(y/32)+1);

        Tile myTileL = grid.getTile((int)x/32,(int)y/32);
        Tile nextTileL = grid.getTile((int)(x/32)-1,(int)(y/32)+1);

        if (myTileD.getType() != nextTileD.getType()){
            return -1;
        }
        else if (myTileU.getType() != nextTileU.getType()){
            return 1;
        }
        else if (myTileR.getType() != nextTileR.getType()){
            return 2;
        }
        else if (myTileD.getType() != nextTileD.getType()){
            return 3;
        }
        else if (myTileL.getType() != nextTileL.getType()) {
            return 4;
        }
        return -1;
    }

    private void Die() {
        alive = false;
    }

    public void Draw(){
        DrawTileTex(getTexture(), getX(), getY(), getWidth(), getHeight());
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public TileGrid getTileGrid(){
        return grid;
    }

    public boolean isAlive() {
        return alive;
    }
}
