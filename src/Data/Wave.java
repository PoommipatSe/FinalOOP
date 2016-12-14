package Data;

import sun.security.x509.DeltaCRLIndicatorExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

import static Edit.Time.Delta;

/**
 * Created by Gluayz on 14/12/2559.
 */
public class Wave {
    private float timeSinceLastSpawn, spawnTime;
    private Monster enemyType;
    private CopyOnWriteArrayList<Monster> enemyList;
    private int enemiesPerWave;
    private boolean waveCompleted;


    public Wave(Monster enemyType, float spawnTime, int enemiesPerWave) {
        this.enemyType = enemyType;
        this.spawnTime = spawnTime;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new CopyOnWriteArrayList<Monster>();
        this.waveCompleted = false;

        Spawn();
    }

    public void Update() {
        boolean allEnemiesDead = true;
        if (enemyList.size() < enemiesPerWave) {
            timeSinceLastSpawn += Delta();
            if (timeSinceLastSpawn > spawnTime) {
                Spawn();
                timeSinceLastSpawn = 0;
            }
        }
        for (Monster e : enemyList) {
            if (e.isAlive()) {
                allEnemiesDead = false;
                e.Update();
                e.Draw();
            }else{
                enemyList.remove(e);
            }
        }
        if (allEnemiesDead)
            waveCompleted = true;
    }

    private void Spawn() {
        enemyList.add(new Monster(enemyType.getTexture(), enemyType
                .getStartTile(), enemyType.getTileGrid(), 32, 32, enemyType
                .getSpeed()));

    }

    public boolean isCompleted() {
        return waveCompleted;
    }

    public CopyOnWriteArrayList<Monster> getEnemyList() {
        return enemyList;
    }


}
