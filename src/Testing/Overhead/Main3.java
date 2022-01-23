package Testing.Overhead;

import Main.Main;
import Main.Msc.ObjectHandler;
import Main.Msc.Vector2;
import Testing.Plattformer.Ground;
import Testing.Plattformer.Player;

import javax.swing.*;
import java.awt.*;


public class Main3 {

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.setTitle("Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Main.isPlaying=true;
        Main.background= new Color(205, 171, 126);

        ObjectHandler.addObject(new Giraffe());
        ObjectHandler.addObject(new Tree());
        ObjectHandler.addObject(new Lion());
        ObjectHandler.addObject(new Giraffe());
        ObjectHandler.addObject(new Tree());
        ObjectHandler.addObject(new Lion());ObjectHandler.addObject(new Giraffe());
        ObjectHandler.addObject(new Tree());
        ObjectHandler.addObject(new Lion());ObjectHandler.addObject(new Giraffe());
        ObjectHandler.addObject(new Tree());
        ObjectHandler.addObject(new Lion());ObjectHandler.addObject(new Giraffe());
        ObjectHandler.addObject(new Tree());
        ObjectHandler.addObject(new Lion());
        Main.Start(frame);
    }

}
