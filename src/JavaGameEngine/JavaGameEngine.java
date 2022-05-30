package JavaGameEngine;
import JavaGameEngine.Backend.Scene;
import JavaGameEngine.Backend.UpdateThread;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

public class JavaGameEngine {

    public static int DELAY = 3;
    static JFrame frame;
    private static float start;
    public static float DeltaTime;
    private int fpsecund;
    public static LinkedList<Scene> scenes = new LinkedList<>();
    public static int selectedScene = 0;

    public void init()
    {
        frame = new JFrame();
        frame.setSize(600,600);
        frame.setTitle("Java Game Engine");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * this it the method to start the game engine
     * do every setup thing before calling start
     */
    public void start() {
            frame = JavaGameEngine.frame;
            startGame();
    }
    /**
     * this it the method to start the game engine
     * do every setup thing before calling start
     * @param frame the frame you want to render in
     */
    public void start(JFrame frame) {
        JavaGameEngine.frame = frame;
        startGame();
    }
    public static float previous = System.nanoTime();

    public static Scene getScene(){
        return scenes.get(selectedScene);
    }

    public static float totalElapsed = 0.0f;

    public static float deltaTime = 0f;

    private static int fps = 0;
    static float last=0;


    private void startGame(){
        frame.setVisible(true);
        frame.add(scenes.get(selectedScene));
        scenes.get(selectedScene).setFocusable(true);

        UpdateThread calcThread = new UpdateThread(JavaGameEngine.getScene().components,scenes.get(selectedScene));
        calcThread.start();

    }

    public void update(){

    }

}
