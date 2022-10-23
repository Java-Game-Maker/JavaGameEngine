package com.javagamemaker.testing;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.asd.javagameengine.components.*;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene();

        GameObject gameObject = new GameObject();
        gameObject.setPosition(new Vector2(100,100));

        GameObject child = new GameObject();
        child.setParentOffset(new Vector2(200,50));

        gameObject.add(child);

        scene1.add(gameObject);

        scene1.load();

       // scene1.add(new GameObject());

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();


    }


}
