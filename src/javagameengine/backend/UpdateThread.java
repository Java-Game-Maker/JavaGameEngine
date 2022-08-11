package javagameengine.backend;

import javagameengine.components.Component;
import javagameengine.JavaGameEngine;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.util.LinkedList;

/**
 * UpdateThread is where we handels the gameloop
 * It is here we update all the components in the game and rerenders the game.
 */
public class UpdateThread extends Thread{

    private static LinkedList<Component> objects = new LinkedList<>();
    public static LinkedList<Component> newObjects = new LinkedList<>();
    public static LinkedList<Component> delObjects = new LinkedList<>();
    private int fpsecund=0;

    public void setObjects(LinkedList<Component>  objects) {
        UpdateThread.objects = objects;
    }

    private Scene gameWorld;

    public UpdateThread(LinkedList<Component> o,Scene gameWorld) {
        this.setObjects(o);
        this.gameWorld = gameWorld;
    }

    //updates all the components
    private LinkedList<Component>  UpdateObjects()
    {
        for (Component component : JavaGameEngine.getScene().components) {
            if(component.isEnabled()){
                component.update();
            }
        }
        return JavaGameEngine.getScene().components;
    }

    public static boolean running = true;

    /**
     * The method that updates all objects and adds/deletes objects that should be added/deleted
     */
    public void Update() {
        if(running){
            JavaGameEngine.getScene().components=(UpdateObjects());
            if(UpdateThread.newObjects.size()>0) {
                for (Component o : UpdateThread.newObjects) {
                    o.setPosition(o.getPosition().add(JavaGameEngine.origin));
                    JavaGameEngine.getScene().components.add(o);
                }
                newObjects.clear();
            }
            if(UpdateThread.delObjects.size()>0) {
                for (Component o : UpdateThread.delObjects) {
                    JavaGameEngine.getScene().components.remove(o);
                }
                delObjects.clear();
            }
        }

    }
    private long last = 0;
    public static double deltatime=1.0f;
    private double prevTime = ((double)System.currentTimeMillis());
    @Override
    public void run() {
        super.run();
        while(Thread.currentThread() == this){
            double now = ((double)System.currentTimeMillis());
            deltatime = (now-prevTime)/10;
            prevTime = now;


            try {
                Thread.sleep(JavaGameEngine.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JavaGameEngine.getScene().getCamera().update();
            //Updates all the objects
            Update();
            JavaGameEngine.mainClass.update();
            /*
             * If the os is linux we have to run that line
             * If windows it lags so... IFS
             */
            if(System.getProperty("os.name").equals("Linux"))
                Toolkit.getDefaultToolkit().sync();

            //Renders
            JavaGameEngine.getScene().update();
            JavaGameEngine.gameWorld.validate();
            JavaGameEngine.gameWorld.repaint();
            if(JavaGameEngine.startNewScene){
                JavaGameEngine.gameWorld.getCurrentScene().start();
                JavaGameEngine.startNewScene = false;
            }

            //Fps counter
            if(System.nanoTime()-last>1000000000){
                gameWorld.fps = Float.toString(fpsecund);

                fpsecund = 0;
                last = System.nanoTime();
            }
            fpsecund+=1;
        }
    }
}

