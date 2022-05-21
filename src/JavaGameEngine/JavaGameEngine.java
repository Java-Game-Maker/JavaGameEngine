package JavaGameEngine;
import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Backend.GameWorld;
import JavaGameEngine.Backend.UpdateThread;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class JavaGameEngine {

    public static int DELAY = 3;
    public static GameWorld GAMEWORLD = new GameWorld();
    static JFrame frame;
    private static float start;
    public static float DeltaTime;
    public static void init()
    {
        frame = new JFrame();
        frame.setSize(600,600);
        frame.setTitle("Java Game Engine");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GAMEWORLD.setBackground(new Color(44, 157, 228));
    }

    /**
     * this it the method to start the game engine
     * do every setup thing before calling start
     */
    public static void start() {
            frame = JavaGameEngine.frame;
            startGame();
    }
    /**
     * this it the method to start the game engine
     * do every setup thing before calling start
     * @param frame the frame you want to render in
     */
    public static void start(JFrame frame) {
        JavaGameEngine.frame = frame;
        startGame();
    }
    public static float previous = System.nanoTime();

    public static float totalElapsed = 0.0f;

    public static float deltaTime = 0f;

    private static int fps = 0;

    private static void startGame(){
        frame.setVisible(true);
        frame.add(GAMEWORLD);
        GAMEWORLD.setFocusable(true);

        UpdateThread calcThread = new UpdateThread(ComponentHandler.getObjects());
        calcThread.start();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                calcThread.Update();

            }
        }, DELAY,DELAY);
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {

                // Debug.log(totalElapsed);
                GAMEWORLD.repaint();
                Toolkit.getDefaultToolkit().sync(); // so it does not lag on linux
                float current = System.nanoTime();
                deltaTime = current - previous;
                previous = current;
                totalElapsed += deltaTime;

                if((totalElapsed/1000000000)>1){
                    GameWorld.fps = String.valueOf(fps);
                    totalElapsed = 0;
                    fps = 0;
                }
                fps++;
            }
        }, DELAY,DELAY);

    }

}