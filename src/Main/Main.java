package Main;

import Main.Display.Map;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static int DELAY = 10;
    public static Color background;
    public static boolean isPlaying = false;

    public static void Start(JFrame frame)
    {

        Map m = new Map();
        m.setFocusable(true);
        m.setBackground(background);
        frame.add(m);
        frame.setVisible(true);

        while(true)
        {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            if(isPlaying)
            {
                m.Update();
            }
        }


    }

}
