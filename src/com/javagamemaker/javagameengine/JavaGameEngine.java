package com.javagamemaker.javagameengine;

import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;

public class JavaGameEngine{

    public static JavaGameEngine gameInstance;
    private static boolean newScene = false;
    public static int DELAY = 5;
    /**this is the JPanel that is rendering our scenes and retrieving inputs*/
    public static final GameWorld gameWorld = new GameWorld();
    /** the scene that is renderned and updated*/
    static Scene selectedScene = new Scene();
    /**
     * this is the gravity constant
     */
    public static Vector2 g = new Vector2(0,0.03982f);
    /**
     * default game window size
     */
    public static Vector2 size = new Vector2(720,500);
    public static JFrame gameWindow = new JFrame();

    /**return selected scene*/
    public static Scene getSelectedScene() {
        return selectedScene;
    }

    /**
     * Changes the active scene
     * @param selectedScene new scene to
     */
    public static void setSelectedScene(Scene selectedScene) {
        newScene = true;
        gameWorld.remove(getSelectedScene());
        selectedScene.startScene();
        gameWorld.add(selectedScene);
        JavaGameEngine.selectedScene = selectedScene;
    }
    public JavaGameEngine() {

    }

    /**
     * Time since last update (ms)
     */
    public static double deltaTime = 0;
    private static double prevTime = ((double)System.currentTimeMillis());

    private static double time = System.currentTimeMillis();
    /**
     * this is the amount of frames drawn every second
     */
    public static float fps = 5;
    private static float counter = 5;
    /**
     * This caps the amount of frames drawn in a second (0 = uncapped)
     */
    public static float fpsCap = 0;

    /**
     * This function can not be called in a components constructor because the game window has not been
     * initialized yet. If you need the size in the start use the start function instead of the constructor.
     *
     * @return GameWindow size
     */
    public static Vector2 getWindowSize() {
        return new Vector2(gameWindow.getSize().width, gameWindow.getSize().height);
    }

    /**
     * update loop
     */
    private static void update() {
        double now = ((double) System.currentTimeMillis());
        //Increases counter every tick but when a 1/10 of a second we reset the counter
        //and sets the fps to the counter
        // To cap the fps we just increase the delay if our fps is too high
        // and decrease it when it is too low
        if (now - time >= 100) {
            fps = counter * 10;
            gameWindow.setTitle("FPS " + fps);
            if (fpsCap > 0 && fps > fpsCap) DELAY++;
            if (fpsCap > 0 && fps < fpsCap && DELAY > 5) DELAY--;

            counter = 0;
            time = now;
        }
        counter++;

        // delta time is the time from previous frame (tick speed)
        deltaTime = (now - prevTime) / 10;
        prevTime = now;

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        selectedScene.update();
        gameWindow.repaint();

        //For linux
        Toolkit.getDefaultToolkit().sync();
        Input.setScrollValue(0);

        if (newScene) {
            gameWindow.validate();
            selectedScene.start();

            newScene = false;
        }
    }

    /**
     * Starts the game
     * call this last in your main function
     */
    public static void start() {

        //Set som basic properties
        gameWindow.setSize((int) size.getX(), (int) size.getY());
        gameWindow.setContentPane(gameWorld);
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setVisible(true);
        selectedScene.start();

        while (true) {
            update();
        }

    }

}
