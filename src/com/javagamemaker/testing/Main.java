package com.javagamemaker.testing;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.*;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JavaGameEngine {

    public static void main(String[] args) {
        Scene scene = new Scene();

        GameObject g1 = new GameObject();
        g1.add(new Collider());
        g1.add(new PhysicsBody());
        ((PhysicsBody) g1.getChild(new PhysicsBody())).velocity = new Vector2(5,0);
        g1.setPosition(new Vector2(-400,0));
        //scene.add(g1);

        GameObject g2 = new GameObject();
        g2.add(new Collider());
        g2.add(new PhysicsBody());
        ((PhysicsBody) g2.getChild(new PhysicsBody())).velocity = new Vector2(-4,0);
        ((PhysicsBody) g2.getChild(new PhysicsBody())).mass = 1000;
        g2.setPosition(new Vector2(200,0));
        //scene.add(g2);
        //scene.getComponents1().clear();
        scene.getCamera().add(new CameraMovement());
        for(int i = 0; i < 10000; i++){
            GameObject g = new GameObject();
            g.setPosition(new Vector2(
                    i*101-500,0));
            g.add(new Grabber(g));
            scene.add(g);

        }
        setSelectedScene(scene);
        //setSelectedScene(new PhysicsTest());
        start();
    }

    static class PhysicsTest extends Scene{
        public PhysicsTest(){
            this.getCamera().add(new CameraMovement());
            GameObject g = new GameObject();
            g.add(new Collider());
            g.setScale(new Vector2(1000,100));
            g.setPosition(new Vector2(0,200));
            add(g);

            GameObject g1 = new GameObject();
            g1.add(new Collider());
            //g1.add(new PhysicsBody(true));
            g1.setScale(new Vector2(100,100));
            g1.setPosition(new Vector2(300,50));
            add(g1);

            GameObject player = new GameObject(){
                @Override
                public void update() {
                    super.update();
                    PhysicsBody body =((PhysicsBody) getChild(new PhysicsBody()));
                    if(Input.isKeyDown(Keys.A)){
                        body.addForce(Vector2.left.multiply(0.5f));
                    }
                    if(Input.isKeyDown(Keys.D)){
                        body.addForce(Vector2.right.multiply(0.5f));
                    }

                    if(Input.isKeyPressed(Keys.SPACE)){
                        ((PhysicsBody) getChild(new PhysicsBody())).addForce(Vector2.up.multiply(50));
                    }
                }

                @Override
                public void onMouseEntered() {
                    super.onMouseEntered();
                    Debug.log("enterd");
                }
            };
            player.add(new Collider());
            player.add(new PhysicsBody(true));


            add(player);
            add(new Grabber(player));
        }
        @Override
        public boolean inside(Component component) {
            return true;
        }
    }

}
