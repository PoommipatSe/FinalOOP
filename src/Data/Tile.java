package Data;

import org.newdawn.slick.opengl.Texture;

import static Edit.Create.*;

/**
 * Created by Gluayz on 31/10/2559.
 */
public class Tile {

    private float x;
    private float y;
    private float width;
    private float height;
    private Texture texture;
    private TileType type;

    public Tile(float x, float y, float width, float height, TileType type,int src){
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setType(type);
        if (src == 0) {
            this.setTexture(QuickLoad(type.textureName));
        }
        else if (src ==1){
            this.setTexture(Twload(type.textureName));
        }
    }

    public  void Draw(){
        DrawTileTex(texture, x, y, width, height);
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

    public int getXPlace() {
        return (int) x / 32;
    }

    public int getYPlace() {
        return (int) y / 32;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }
}
