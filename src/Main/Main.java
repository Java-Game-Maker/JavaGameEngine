package Main;

import Main.Display.Map;

import javax.swing.*;

public class Main {

    public static int DELAY = 10;

    public static void Start(JFrame frame)
    {

        Map m = new Map();
        m.setFocusable(true);

        frame.add(m);
        frame.setVisible(true);

        while(true)
        {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            m.Update();
        }


    }

}
