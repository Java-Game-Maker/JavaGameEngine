package Testing.Plattformer;

import JavaGameEngine.Main;
import JavaGameEngine.Msc.ObjectHandler;
import JavaGameEngine.Msc.Vector2;
import JavaGameEngine.Objects.Components.GameObject;

import javax.swing.*;
import java.awt.*;


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


        Player player = new Player(new Vector2(100,200));
        GameObject g = new GameObject();
        g.setOffset(new Vector2(50,0));
        g.setScale(new Vector2(10,10));
        player.addComponent(g);
        ObjectHandler.addObject(player);
        ObjectHandler.addObject(new Ground(new Vector2(300,500), new Vector2(1000,100),"ground1"));

        Main.Start(frame);

    }

}
