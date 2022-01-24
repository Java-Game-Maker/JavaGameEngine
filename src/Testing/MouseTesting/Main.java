package Testing.MouseTesting;

import JGame.Msc.ObjectHandler;
import JGame.Msc.Vector2;
import Testing.Plattformer.Ground;

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


        ObjectHandler.addObject(new Betty(new Vector2(100,200)));
        ObjectHandler.addObject(new Betty(new Vector2(200,200)));
        ObjectHandler.addObject(new Betty(new Vector2(300,200)));
       // ObjectHandler.addObject(new Item(true));
        //ObjectHandler.addObject(new Enemy(new Vector2(300,500)));
        ObjectHandler.addObject(new Ground(new Vector2(300,600), new Vector2(1000,100),"ground1"));
       // ObjectHandler.addObject(new Ground(new Vector2(300,500),new Vector2(100,100),"ground2"));


        JGame.Main.Start(frame);
    }

}
