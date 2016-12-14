package Data;

/**
 * Created by Gluayz on 14/12/2559.
 */
public class WaveCreate {
    private float timeSinceLastWave, timeBetweenEnemies;
    private int waveNumber, enemiesPerWave;
    private Monster enemyType;
    private Wave currentWave;

    public WaveCreate(Monster enemyType, float timeBetweenEnemies, int enemiesPerWave) {
        this.enemyType = enemyType;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;

        this.currentWave = null;

        newWave();
    }

    public void update() {
        if (!currentWave.isCompleted())
            currentWave.Update();
        else
            newWave();
    }

    private void newWave() {
        currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave);
        waveNumber++;
        System.out.println("Beginning Wave " + waveNumber);
    }

    public Wave getCurrentWave() {
        return currentWave;
    }
}
