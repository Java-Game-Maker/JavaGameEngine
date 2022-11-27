package com.javagamemaker.testing;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene(){
            @Override
            public void start() {
                super.start();
                getCamera().setScale(new Vector2(2,2));
                //getCamera().setPosition(new Vector2(100,0));
            }

            @Override
            public void update() {
                super.update();
                Debug.log(getComponents1().size());
            }
        };
        //scene1.load();

        //scene1.add(new GameObject());

        scene1.setDebugMode(true);

       // scene1.add(new GameObject());

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();
    }


}
