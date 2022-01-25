package Testing.Plattformer;

import JavaGameEngine.Main;
import JavaGameEngine.Msc.ObjectHandler;
import JavaGameEngine.Msc.Vector2;

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



        ObjectHandler.addObject(new Player(new Vector2(100,200)));
        //ObjectHandler.addObject(new Enemy(new Vector2(300,500)));
        ObjectHandler.addObject(new Ground(new Vector2(300,600), new Vector2(1000,100),"ground1"));
       // ObjectHandler.addObject(new Ground(new Vector2(300,500),new Vector2(100,100),"ground2"));
        long start = System.nanoTime();
        //Main.instantiate(new Ground(new Vector2(0,0),new Vector2(0,0),"grid "));
        //System.out.println(Main.updates);
        new Bullet(Vector2.left,Vector2.zero);
        long end = System.nanoTime();
       // System.out.println("Dame son "+(end-start)/1000000);
        Main.Start(frame);

    }

}
