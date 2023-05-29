import com.javagamemaker.javagameengine.GameWorld;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.gamecompnents.CollidingBox;
import com.javagamemaker.javagameengine.components.gamecompnents.Grabber;
import com.javagamemaker.javagameengine.components.gamecompnents.PlatformPlayerController;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.InputComponent;
import com.javagamemaker.javagameengine.input.InputManager;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Main extends JavaGameEngine {

    public static void main(String[] args){
        Scene scene = new Scene();

        scene.add(new Player("Player 1"));

        //Input.addContext("Player 1");
        Input.addContext("Player 2");
        Input.addContext("All");

        scene.add(new CollidingBox(new Vector2(0,200)));

        setSelectedScene(scene);
        start();

    }

    static class Player extends GameObject {

        InputComponent c;
        InputComponent c2;
        int count = 0;

        public Player(String context) {
            c = new InputComponent(context);
            c2 = new InputComponent("All");

            add(new PlatformPlayerController());
            add(new Grabber(this));

            add(c);
            add(c2);
        }

        @Override
        public void update() {
            super.update();
            if (c.isKeyPressed(Keys.A)){
                Debug.log("A was pressed "+count+" "+this.<InputComponent>getChild(new InputComponent("")).getContext());
                count++;
            }
            if(c2.isKeyPressed(Keys.D)){
                Debug.log("Detta ska alla skriva");
            }
        }
    }

}
