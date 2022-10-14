package testing;

import javagameengine.JavaGameEngine;
import javagameengine.Scene;
import javagameengine.components.*;
import javagameengine.msc.Vector2;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene();



        scene1.getCamera().add(new CameraMovement());

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();


    }


}
