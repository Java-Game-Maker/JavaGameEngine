package Testing.HowTo;

import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.Msc.ObjectHandler;

import javax.swing.*;
import java.awt.*;


public class Main  {

    public static void main(String[] args)
    {
        //Setting up the window to
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.setTitle("Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JavaGameEngine.isPlaying=true; // if this is false the engine will not update the objects

        JavaGameEngine.background= new Color(44, 157, 228); // setting the skybox color

        ObjectHandler.addObject(new Player());
        ObjectHandler.addObject(new Ground());

        //and then we start the engine
        JavaGameEngine.Start(frame);
    }
}
