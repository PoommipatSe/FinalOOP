package Data;

import static Edit.Create.QuickLoad;

/**
 * Created by Gluayz on 14/12/2559.
 */
public class Game {

    private TileGrid grid;
    private Player player;
    private WaveCreate waveManager;
    public static final int TILE_SIZE = 32;

    public Game() {
        grid = new TileGrid();
        waveManager = new WaveCreate(new Monster(QuickLoad("mons1"), grid.getTile(0, 8), grid, 64, 64, 70),2, 2);
        player = new Player(grid, waveManager);
    }

    public void upDate() {
        grid.Draw();
        waveManager.update();
        player.upDate();

    }

}
