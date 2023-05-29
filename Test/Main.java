import com.javagamemaker.javagameengine.GameWorld;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.InputComponent;
import com.javagamemaker.javagameengine.input.InputManager;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;

public class Main extends JavaGameEngine {

    public static void main(String[] args){
        Scene scene = new Scene();

        scene.add(new Player("Player 1"));
        scene.add(new Player("Player 1"));

        Input.addContext("Player 1");
        Input.addContext("Player 2");


        setSelectedScene(scene);
        start();

    }

    static class Player extends GameObject {

        public Player(String context) {
            add(new InputComponent(context));
        }

        @Override
        public void update() {
            super.update();
            if (Input.isKeyPressed(Keys.A)){
                Debug.log("A was pressed "+this.<InputComponent>getChild(new InputComponent("")).getContext());
            }
        }
    }

}
