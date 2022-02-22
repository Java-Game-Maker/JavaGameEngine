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

    public void collisionDetection(){
        for(Component c1 : ComponentHandler.getObjects()){
            for(Component c2 : ComponentHandler.getObjects()) {
                try {
                    ShapeCollider collider1 = (ShapeCollider) c1.getChild(new ShapeCollider());
                    ShapeCollider collider2 = (ShapeCollider) c2.getChild(new ShapeCollider());
                    if (collider1 != collider2) {
                        for (int i = 0; i < collider2.shape.npoints; i++) {
                            if (collider1.shape.contains(new Point(collider2.shape.xpoints[i], collider2.shape.ypoints[i]))) {
                                Debug.log(collider1.getParent()+" collided with "+collider2.getParent());
                            }
                            if (collider2.shape.contains(new Point(collider1.shape.xpoints[i], collider1.shape.ypoints[i]))) {
                                Debug.log(collider1.getParent()+" collided with "+collider2.getParent());
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void Update() {
        ComponentHandler.setObjects(UpdateObjects());
        collisionDetection();
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

