package com.javagamemaker.javagameengine;

import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the main class in the JavaGameEngine gameengine
 * To start your game extend this class and run the start(); function to start the game
 */
public class JavaGameEngine{

    public static JavaGameEngine gameInstance;
    private static boolean newScene = false;
    public static float masterVolume = 1f;
    public static int DELAY = 2;
    /**this is the JPanel that is rendering our scenes and retrieving inputs*/
    public static final GameWorld gameWorld = new GameWorld(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    };
    /** the scene that is renderned and updated*/
    static Scene selectedScene = new Scene();
    /**
     * this is the gravity constant
     */
    public static Vector2 g = new Vector2(0,0.04982f);
    /**
     * default game window size
     */
    public static Vector2 size = new Vector2(720,500);
    public static JFrame gameWindow = new JFrame(){
        @Override
        public void paintComponents(Graphics g) {
            super.paintComponents(g);

        }
    };

    /**return selected scene*/
    public static Scene getSelectedScene() {
        return selectedScene;
    }
    //so the scene does not start twice at the start
    public static boolean firstFrame = true;
    /**
     * Changes the active scene
     * @param selectedScene new scene to
     */
    public static void setSelectedScene(Scene selectedScene) {
        newScene = true;

        for(Component uiComp : getSelectedScene().getUiElements()){
            gameWorld.remove(uiComp);
        }

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
    public static float fpsCap = 60;

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
    public static void update() {

       // try {
       //     Thread.sleep(DELAY);
       // } catch (InterruptedException e) {
       //     throw new RuntimeException(e);
       // }

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
        deltaTime = (now - prevTime)/10;
        prevTime = now;
        //Debug.startCount();
        selectedScene.update();
        //gameWindow.repaint();
        //For linux
        Toolkit.getDefaultToolkit().sync();
        Input.setScrollValue(0);

        if (newScene) {
            gameWindow.validate();
            if(!firstFrame)
                selectedScene.start();

            newScene = false;
        }
        firstFrame = false;
    }
    public static boolean started = false;
    /**
     * Starts the game
     * call this last in your main function
     */
    public static void start() {

        started = true;
        //Set som basic properties
        gameWorld.setLayout(null);
        gameWindow.setSize((int) size.getX(), (int) size.getY());
        gameWindow.setContentPane(gameWorld);
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setVisible(true);

        selectedScene.start();
        javax.swing.Timer timer = new javax.swing.Timer(DELAY, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                update();
                gameWindow.revalidate();
                gameWindow.repaint();
                getSelectedScene().setSize(gameWorld.getSize());
            }

        });
        timer.setCoalesce(true);
        timer.setRepeats(true);
        timer.start();


    }

}
