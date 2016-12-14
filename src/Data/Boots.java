/**
 * Created by Gluayz on 31/10/2559.
 */
package Data;

import Edit.Time;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Camera;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static Edit.Create.*;
import static org.lwjgl.opengl.GL11.*;

public class Boots {

    public Boots(){

        Begin();
        Game game = new Game();
        while(!Display.isCloseRequested()){
            Time.update();

            game.upDate();
            //grid.Draw();
            //w.Update();
            //player.upDate();




            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }

    public static void main(String[] args){
        new Boots();
    }

}