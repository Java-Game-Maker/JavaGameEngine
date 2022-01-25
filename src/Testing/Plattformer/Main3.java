package Testing.Plattformer;

import JavaGameEngine.Display.GameWorld;
import JavaGameEngine.Main;
import JavaGameEngine.Msc.ObjectHandler;
import JavaGameEngine.Msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Main3  {

    public static JLabel vel = new JLabel("0");
    public static JLabel dir = new JLabel("0");

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.setTitle("Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        Main.isPlaying=true;
        Main.background= new Color(44, 157, 228);
        Player p = new Player(new Vector2(100,200));
        ObjectHandler.addObject(p);
        ObjectHandler.addObject(new Ground(new Vector2(300,600), new Vector2(1000,100),"ground1"));

        GameWorld m = new GameWorld();
        m.setBackground(Color.CYAN);
        frame.add(m);
        frame.setVisible(true);
        m.Start();

    }

}
