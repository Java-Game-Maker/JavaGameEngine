package JavaGameEngine.Backend;

import JavaGameEngine.Components.Collider.ShapeCollider;
import JavaGameEngine.Components.Component;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Debug;

import java.awt.*;
import java.util.LinkedList;

public class UpdateThread extends Thread{

    private static LinkedList<Component> objects = new LinkedList<>();
    public static LinkedList<Component> newObjects = new LinkedList<>();
    public static LinkedList<Component> delObjects = new LinkedList<>();

    public void setObjects(LinkedList<Component>  objects) {
        UpdateThread.objects = objects;
    }

    public UpdateThread(LinkedList<Component> o) {
        this.setObjects(o);
    }

    private LinkedList<Component>  UpdateObjects()
    {
        for (Component component : ComponentHandler.getObjects()) {
            component.update();
        }
        return ComponentHandler.getObjects();
    }

    /**
     * Checks if any collider is colliding and if so
     * runs the oncoliisoin in the coponents that are colliding
     */
    public void collisionDetection(){
        for(Component c1 : ComponentHandler.getObjects()){
            for(Component c2 : ComponentHandler.getObjects()) {
                try {

                    ShapeCollider collider1 = (ShapeCollider) c1.getChild(new ShapeCollider());
                    ShapeCollider collider2 = (ShapeCollider) c2.getChild(new ShapeCollider());

                    if (collider1 != collider2) {
                        for (int i = 0; i < collider2.shape.npoints; i++) {
                            Point c1point = new Point(collider1.shape.xpoints[i], collider1.shape.ypoints[i]);
                            Point c2point = new Point(collider2.shape.xpoints[i], collider2.shape.ypoints[i]);
                            //Checks the first collider
                            if (collider1.shape.contains(c1point)) {
                                Debug.log(c1point.toString());
                                collider1.getParent().onCollision(collider2.getParent());
                                collider2.getParent().onCollision(collider1.getParent());
                            }
                            //checks the secound collider
                            if (collider2.shape.contains(c2point)) {

                                collider1.getParent().onCollision(collider2.getParent());
                                collider2.getParent().onCollision(collider1.getParent());
                            }
                        }
                    }
                } catch (Exception e) {}
            }
        }
    }

    public void Update() {
        ComponentHandler.setObjects(UpdateObjects());

        //collisionDetection();
        
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

    @Override
    public void run() {
        super.run();
        ComponentHandler.setObjects(UpdateObjects());
    }
}

