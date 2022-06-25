package Testing;

import javagameengine.backend.GameWorld;
import javagameengine.backend.Scene;
import javagameengine.backend.UpdateThread;
import javagameengine.backend.input.Input;
import javagameengine.backend.input.Keys;
import javagameengine.components.Camera;
import javagameengine.components.Component;
import javagameengine.components.colliders.SquareCollider;
import javagameengine.components.GameObject;
import javagameengine.components.physics.PhysicsWorld;
import javagameengine.JavaGameEngine;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JavaGameEngine{

    public static int level = 0;
    public static void main(String[] args){
        Main m = new Main();
        ChildCollide l = new ChildCollide();
        setSelectedScene(l);

        m.start();
        //Debug.showWhere = true;
    }

    @Override
    public void update() {
        super.update();
        if(!UpdateThread.running){
            setSelectedScene(new Start());
            UpdateThread.running = true;
        }

    }

    static class Start extends Scene{
        public Start(){
            Main.level = 0;

            JButton b = new JButton("Level1");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Main.setSelectedScene(new Level1());
                    System.out.println(1);
                }
            });
            add(b);
            b.setBounds(200,200,100,50);

            JButton b1 = new JButton("Level2");
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Main.setSelectedScene(new Level2());
                    System.out.println(2);

                }
            });
            add(b1);
            b1.setBounds(400,200,100,50);

            JButton b2 = new JButton("Level3");
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Main.setSelectedScene(new Level4());
                    System.out.println(3);

                }
            });
            add(b2);
            b2.setBounds(600,200,100,50);

        }
    }

    static class Level1 extends Scene{
        public Level1(){
            id=0;
            Main.level = 1;

            Player s = new Player(new Vector2(0,-100));
            components.add(s);
            components.add(new Goal(new Vector2(100,-100)));
            components.add(new Ground(new Vector2(0,0)));
         //   components.add(new Goal());
        }

    }
    static class Level2 extends Scene{
        public Level2(){
            id=1;
            Main.level = 2;
            Player s = new Player(new Vector2(0,100));

            components.add(s);
            components.add(new Goal(new Vector2(400,-100)));
            components.add(new Ground(new Vector2(400,0)));
            components.add(new Ground(new Vector2(120,200)));
        }
    }
    static class Level3 extends Scene{
        public Level3(){
            Player s = new Player(new Vector2(0,-100));
            id=2;
            Main.level = 3;
            components.add(s);
            components.add(new Ground(new Vector2(0,0)));
            components.add(new Ground(new Vector2(400,-200)));
            components.add(new Ground(new Vector2(0,-400)));
            components.add(new Goal(new Vector2(-100,-500)));
        }
    }
    static class End extends Scene{
        public End(){
            JButton b = new JButton("Restart");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Main.setSelectedScene(new Start());
                    level = 0;
                }
            });
            add(b);
            b.setBounds(400,200,100,50);
            Main.level = 4;

        }
    }


    static class T extends GameObject{
        public T(){
            SquareCollider s = new SquareCollider();
            addChild(s);
        }
    }
}
