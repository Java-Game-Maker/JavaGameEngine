package JavaGameEngine;
import JavaGameEngine.Backend.GameWorld;
import JavaGameEngine.Backend.Scene;
import JavaGameEngine.Backend.UpdateThread;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

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
    public static GameWorld gameWorld = new GameWorld();

    /**
     * This should be called before start
     * it sets up a frame
     */
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

    /**
     * This is used by the engine (don't change it)
     */
    public static boolean startNewScene = true;


    public static void setSelectedScene(Scene scene) {
        startNewScene = true;
        gameWorld.getCurrentScene().setActive(false);
        gameWorld.setCurrentScene(scene);
    }
    public static Scene getScene(){
        return gameWorld.getCurrentScene();
    }

    /**
     * @return Vector2 x window width y window height
     */
    public static Vector2 getWindowSize(){
        return new Vector2((float) frame.getSize().getWidth(), (float) frame.getSize().getHeight());
    }

    private void startGame(){
        init();

        frame.setVisible(true);
        frame.add(gameWorld);
        gameWorld.setCurrentScene(getScene());

        UpdateThread calcThread = new UpdateThread(JavaGameEngine.getScene().components,getScene());
        calcThread.start();
    }

    public void update(){

    }

}
