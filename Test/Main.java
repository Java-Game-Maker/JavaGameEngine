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

import java.awt.*;

public class Main extends JavaGameEngine {

    public static void main(String[] args){
        Scene scene = new Scene();

        scene.add(new Player("Player 1"));
        scene.add(new Player("Player 2"){
            @Override
            public void start() {
                setPosition(new Vector2(0,-150));
                super.start();
            }
        });

        Input.addContext("Player 1");
        //Input.addContext("Player 2");
        Input.addContext("All");

        scene.add(new CollidingBox(new Vector2(200,200)){
            @Override
            public void start() {
                setScale(new Vector2(1500,100));
                super.start();
            }
        });

        setSelectedScene(scene);
        start();

    }

    static class Player extends GameObject {

        public Player(String context) {
            add(new PlatformPlayerController(new InputComponent(context)));
            add(new Grabber(this));
        }

        @Override
        public void update() {
            super.update();
            if(Input.isKeyPressed(Keys.E)){
                if(Input.getActiveContext().contains("Player 1")){
                    Input.addContext("Player 2");
                    Input.removeContext("Player 1");
                }
                else{

                    Input.addContext("Player 1");
                    Input.removeContext("Player 2");
                }
            }
            if(Input.isKeyPressed(Keys.P)){
                Input.addContext("Player 1");
                Input.addContext("Player 2");
            }
        }
    }

}
