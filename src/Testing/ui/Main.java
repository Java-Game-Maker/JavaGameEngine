package Testing.ui;

import JGame.Display.Ui.Button;
import JGame.Display.Ui.UiEvent;
import JGame.Msc.ObjectHandler;
import JGame.Msc.Vector2;

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


        Button b = new Button();
        b.addNewEventListener(new UiEvent() {
            @Override
            public void mouseIsDown(int e) {
                super.mouseIsDown(e);
                if(e == 1)
                {
                    System.out.println("what");
                }
                else
                    System.out.println("na");
            }
        });
        ObjectHandler.addObject(b);

        JGame.Main.Start(frame);

    }

}
