package com.javagamemaker.testing;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.*;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JavaGameEngine {

    public static void main(String[] args) {

        Scene s = new Scene();
        JTextField t = new JTextField();
        JButton b =  new JButton("Test");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Debug.log("asd");
            }
        });
        b.setLocation(new Point(100,100));
        b.setSize(100,100);
        s.add(b);
        t.setLocation(100,150);
        t.setSize(100,50);

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
