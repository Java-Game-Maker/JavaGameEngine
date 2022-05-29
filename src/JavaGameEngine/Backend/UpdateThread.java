package JavaGameEngine.Backend;

import JavaGameEngine.Components.Component;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.util.LinkedList;

public class UpdateThread extends Thread{

    private static LinkedList<Component> objects = new LinkedList<>();
    public static LinkedList<Component> newObjects = new LinkedList<>();
    public static LinkedList<Component> delObjects = new LinkedList<>();
    private int fpsecund=0;

    public void setObjects(LinkedList<Component>  objects) {
        UpdateThread.objects = objects;
    }

    private GameWorld gameWorld;

    public UpdateThread(LinkedList<Component> o,GameWorld gameWorld) {
        this.setObjects(o);
        this.gameWorld = gameWorld;
    }

    public static Component camera = new Component(new Vector2(0,0),new Vector2(0,0));


    private LinkedList<Component>  UpdateObjects()
    {
        for (Component component : ComponentHandler.getObjects()) {
            if(component.isEnabled()){
                component.setCameraPosition(UpdateThread.camera.getPosition());
                component.update();
            }
        }
        return ComponentHandler.getObjects();
    }
    public static boolean running = true;
    public void Update() {
        if(running){
            ComponentHandler.setObjects(UpdateObjects());
            if(UpdateThread.newObjects.size()>0) {
                for (Component o : UpdateThread.newObjects) {
                    ComponentHandler.addObject(o);
                }
                newObjects.clear();
            }
            if(UpdateThread.delObjects.size()>0) {
                for (Component o : UpdateThread.delObjects) {
                    ComponentHandler.removeObject(o);
                }
                delObjects.clear();
            }
        }
        gameWorld.repaint();
    }
    private long last = 0;
    @Override
    public void run() {
        super.run();
        while(true){
            try {
                Thread.sleep(JavaGameEngine.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Update();
            if(System.nanoTime()-last>1000000000){
                gameWorld.fps = Float.toString(fpsecund);
                fpsecund = 0;
                last = System.nanoTime();
            }
            fpsecund+=1;
        }
    }
}

