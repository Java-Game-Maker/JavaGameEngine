package Testing;

import JavaGameEngine.Backend.Scene;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Vector2;

public class Main extends JavaGameEngine{

    public static void main(String[] args){
        Main m = new Main();
        m.init();

        Player s = new Player(new Vector2(40,10));

        Scene mainScene = new Scene();
        mainScene.components.add(s);
        mainScene.components.add(new Ground());

        scenes.add(mainScene);

        Scene mainScene1 = new Scene();
        s = new Player(new Vector2(400,10));
        mainScene1.components.add(s);

        mainScene1.components.add(new GameObject());

        scenes.add(mainScene1);

        m.start();
    }



    static class T extends GameObject{
        public T(){
            SquareCollider s = new SquareCollider();
            addChild(s);
        }
    }
}
