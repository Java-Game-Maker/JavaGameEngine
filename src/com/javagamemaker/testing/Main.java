package com.javagamemaker.testing;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.*;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;

public class Main extends JavaGameEngine {

    public static void main(String[] args) {
        PhysicsTest t = new PhysicsTest();
        setSelectedScene(t);



        start();
    }

    static class PhysicsTest extends Scene{
        public PhysicsTest(){

            this.getCamera().add(new CameraMovement());

            GameObject left = new GameObject();
            left.setColor(Color.GREEN);
            left.add(new PhysicsBody());
            left.add(new Collider());
            ((PhysicsBody) left.getChild(new PhysicsBody())).velocity = new Vector2(5,0.1f);
            ((PhysicsBody) left.getChild(new PhysicsBody())).mass = 20;
            left.setPosition(new Vector2(-400,0));

            this.add(left);

            GameObject right = new GameObject();
            right.add(new PhysicsBody());
            right.add(new Collider());
            ((PhysicsBody) right.getChild(new PhysicsBody())).velocity = new Vector2(-5,0.1f);
            right.setPosition(new Vector2(400,0));

            this.add(right);
        }
        @Override
        public boolean inside(Component component) {
            return true;
        }
    }

}
