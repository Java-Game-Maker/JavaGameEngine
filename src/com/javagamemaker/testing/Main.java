package com.javagamemaker.testing;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene(){
            @Override
            public void start() {
                super.start();
                getCamera().setScale(new Vector2(2,2));
                //getCamera().setPosition(new Vector2(100,0));
            }
        };
        scene1.load();

        Debug.log(scene1.getComponents1().size());


        scene1.setDebugMode(true);

       // scene1.add(new GameObject());

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();


    }


}
