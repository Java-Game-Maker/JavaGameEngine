package testing;

import javagameengine.JavaGameEngine;
import javagameengine.Scene;
import javagameengine.components.*;
import javagameengine.msc.Vector2;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene();

        GameObject gameObject = new GameObject();
        gameObject.setPosition(new Vector2(100,100));
        gameObject.add(new PhysicsBody());
        scene1.add(gameObject);

        scene1.add(new GameObject());

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();


    }


}
