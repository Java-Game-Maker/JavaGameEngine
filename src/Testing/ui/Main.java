package Testing.ui;

import JavaGameEngine.Display.Ui.Button;
import JavaGameEngine.Display.Ui.UiEvent;
import JavaGameEngine.Msc.ObjectHandler;

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
        JavaGameEngine.Main.isPlaying=true;
        JavaGameEngine.Main.background= new Color(44, 157, 228);


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

        JavaGameEngine.Main.Start(frame);

    }

}
