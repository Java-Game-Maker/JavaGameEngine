package JavaGameEngine;

import JavaGameEngine.Display.CalcThread;
import JavaGameEngine.Display.GameWorld;
import JavaGameEngine.Msc.ObjectHandler;
import JavaGameEngine.Objects.Components.GameObject;
import java.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class Main {

    public static int DELAY = 16;
    public static Color background;
    public static int updates = 0;
    /**
     * Update game world*/
    public static boolean isPlaying = false;

    static boolean update=false;
    public static JLabel label = new JLabel();
    public static void Start(JFrame frame)
    {
        GameWorld m = new GameWorld();
        m.add(label);
        m.setFocusable(true);
        m.setBackground(background);
        frame.add(m);
        frame.setVisible(true);

        //Component.square.setPosition(new Vector2(200,300));

        CalcThread calcThread = new CalcThread();
        calcThread.setObjects(ObjectHandler.getHashMapObjects());
        calcThread.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                calcThread.Update();

            }
        }, DELAY,DELAY);
        while(true)
        {
            m.repaint();
        }
    }
    public static void instantiate(GameObject obj)
    {
        GameWorld.newObjects.add(obj);
    }
}
