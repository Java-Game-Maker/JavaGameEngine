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

        Player s = new Player(new Vector2(300,300));

        Scene mainScene = new Scene();

        mainScene.components.add(s);
        mainScene.components.add(new Coin());
        mainScene.components.add(new Ground());

        scenes.add(mainScene);

        Scene mainScene1 = new Scene();

        s = new Player(new Vector2(400,300));

        mainScene1.components.add(s);

        mainScene1.components.add(new Ground());

        Ground ground = new Ground();
        ground.setPosition(new Vector2(800,400));
        mainScene1.components.add(ground);

        Coin c = new Coin();
        c.startPos = new Vector2(800,250);
        c.setPosition(new Vector2(800,250));

        mainScene1.components.add(c);

        scenes.add(mainScene1);


        Scene mainScene2 = new Scene();
        mainScene2.components.add(new GameObject());



        scenes.add(mainScene2);
        m.start();
    }



    static class T extends GameObject{
        public T(){
            SquareCollider s = new SquareCollider();
            addChild(s);
        }
    }
}
