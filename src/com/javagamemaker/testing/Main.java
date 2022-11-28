package com.javagamemaker.testing;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.lang.invoke.VolatileCallSite;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene(){
            @Override
            public void start() {
                super.start();
                getCamera().setScale(new Vector2(1,1));
                //getCamera().setPosition(new Vector2(100,0));
            }

            @Override
            public void update() {
                super.update();
            }
        };

        scene1.add(new GameObject());
        scene1.setDebugMode(true);

        scene1.load(true);

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);
        start();
    }


}
