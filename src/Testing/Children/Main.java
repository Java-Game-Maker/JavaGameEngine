package Testing.Children;

import JGame.Msc.ObjectHandler;
import JGame.Msc.Vector2;
import JGame.Objects.Components.GameObject;
import Testing.Plattformer.Ground;
import Testing.Plattformer.Player;

import javax.swing.*;
import java.awt.*;


public class Main {

    public static JLabel vel = new JLabel("0");
    public static JLabel dir = new JLabel("0");

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.setTitle("Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JGame.Main.isPlaying=true;
        JGame.Main.background= new Color(44, 157, 228);

        Cube cube1 = new Cube(new Vector2(0,0));
        cube1.isMain =true;
        Cube cube2 = new Cube(new Vector2(51,0));
        Cube cube3 = new Cube(new Vector2(-51,0));
        Cube cube4 = new Cube(new Vector2(0,-51));


        cube1.setPosition(new Vector2(200,200));

        cube1.addComponent(cube2);
        cube1.addComponent(cube3);
        cube1.addComponent(cube4);

        ObjectHandler.addObject(cube1);
       // ObjectHandler.addObject(cube2);


        JGame.Main.Start(frame);

    }

}
