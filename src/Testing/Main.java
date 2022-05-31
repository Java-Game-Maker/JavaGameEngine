package Testing;

import JavaGameEngine.Backend.Scene;
import JavaGameEngine.Backend.UpdateThread;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsWorld;
import JavaGameEngine.Components.Ui.Label;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.UI.Button;
import JavaGameEngine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JavaGameEngine{

    public static int level = 0;
    public static void main(String[] args){
        Main m = new Main();
        PhysicsWorld.setGravityAcceleration(new Vector2(0,9.92f/1000));

        setSelectedScene(new Start());

        m.start();
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
                    Main.setSelectedScene(new Level3());
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

            Player s = new Player(new Vector2(100,300));
            components.add(s);
            components.add(new Label("1"));
            components.add(new Coin(new Vector2(500,400)));
            components.add(new Ground());
        }
    }
    static class Level2 extends Scene{
        public Level2(){
            Player s = new Player(new Vector2(200,300));
            id=1;
            Main.level = 2;

            components.add(s);
            components.add(new Label("2"));

            components.add(new Coin(new Vector2(500,200)));
            components.add(new Ground());
            components.add(new Ground(new Vector2(600,300)));
        }
    }
    static class Level3 extends Scene{
        public Level3(){
            Player s = new Player(new Vector2(200,300));
            id=2;
            Main.level = 3;
            components.add(s);
            components.add(new Label("3"));

            components.add(new Coin(new Vector2(200,0)));
            components.add(new Ground());
            components.add(new Ground(new Vector2(600,300)));
            components.add(new Ground(new Vector2(200,100)));
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
