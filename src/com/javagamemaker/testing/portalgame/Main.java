package com.javagamemaker.testing.portalgame;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;

public class Main extends JavaGameEngine {


    public static void main(String[] args){
        Scene level1 = new Scene();

        GameObject thing = new GameObject();
        PhysicsBody p = new PhysicsBody();
        p.velocity = new Vector2(0,2);
        thing.add(p);
        Collider c  = new Collider();
        c.setTrigger(true);
        thing.add(c);

        level1.add(thing);

        level1.add(new Portal(new Vector2(-200,-200),new Vector2(0,200)));
        setSelectedScene(level1);

        start();
    }

}
