package Testing.Physics;

import Main.Display.Map;
import Main.Main;
import Main.Msc.ObjectHandler;
import Main.Msc.Vector2;
import Main.Objects.Object;
import Testing.Plattformer.Item;

import javax.swing.*;
import java.awt.*;


public class Main3 {

    public static JLabel vel = new JLabel("0");

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.setTitle("Physics Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Map.addJComponent(vel);
        Main.isPlaying=true;
        Main.background= new Color(44, 157, 228);

        ObjectHandler.addObject(new Ball());

        Main.Start(frame);
    }

}
