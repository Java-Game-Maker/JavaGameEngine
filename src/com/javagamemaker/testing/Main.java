package com.javagamemaker.testing;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.*;
import com.javagamemaker.javagameengine.components.Component;
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
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        Scene s = new Scene(){
            @Override
            public void update() {
            super.update();
            if(Input.isKeyPressed(Keys.E)){
                p.setVisible(!p.isVisible());
            }

            }
        };
        JTextField t = new JTextField();
        t.setSize(199,19);
        p.add(t);
        p.add(new JButton("Subbmit"));

        p.setSize(100,100);
        p.setLocation(100,100);
        s.add(p);

        setSelectedScene(s);
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
