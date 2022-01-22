package Testing;

import Main.Main;
import Main.Msc.ObjectHandler;
import Main.Msc.Vector2;
import Testing.Player;

import javax.swing.*;

public class Main2 {

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.setTitle("SwingGameEngine");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ObjectHandler.AddAnimal(new Player(new Vector2(200,200)));


        Main.Start(frame);

    }

}
