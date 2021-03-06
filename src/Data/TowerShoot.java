package Data;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Edit.Create.DrawQuadTexRot;
import static Edit.Create.DrawTileTex;
import static Edit.Create.QuickLoad;
import static Edit.Time.Delta;

/**
 * Created by Gluayz on 14/12/2559.
 */
public class TowerShoot {

    private float x, y, timeSinceLastShot, firingSpeed, angle;
    private int width, height, damage;
    private Texture baseTexture, cannonTexture;
    private Tile startTile;
    private ArrayList<Shoot> projectiles;
    private ArrayList<Monster> enemies;
    private Monster target;

    public TowerShoot(Texture baseTexture, Tile startTile, int damage, ArrayList<Monster> enemies) {
        this.baseTexture = baseTexture;
        this.cannonTexture = QuickLoad("cannonGun");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = (int) startTile.getWidth();
        this.height = (int) startTile.getHeight();
        this.damage = damage;
        this.firingSpeed = 3;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<Shoot>();
        this.enemies = enemies;
        this.target = acquireTarget();
        this.angle = calculateAngle();
    }

    private Monster acquireTarget() {
        return enemies.get(0);
    }

    private float calculateAngle() {
        double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(angleTemp) - 90;
    }

    private void shoot() {
        timeSinceLastShot = 0;
        projectiles.add(new Shoot(QuickLoad("bullet"), target, x + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4, y + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4, 900, 10));
    }

    public void update() {
        timeSinceLastShot += Delta();
        if (timeSinceLastShot > firingSpeed)
            shoot();

        for (Shoot p: projectiles)
            p.update();

        angle = calculateAngle();
        draw();
    }

    public void draw() {
        DrawTileTex(baseTexture, x, y, width, height);
        DrawQuadTexRot(cannonTexture, x, y, width, height, angle);
    }


}
