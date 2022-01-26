package JavaGameEngine.Display;

import JavaGameEngine.Msc.ObjectHandler;
import JavaGameEngine.Objects.Components.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CalcThread extends Thread{

    private static HashMap<Integer, Component> objects = new HashMap<>();
    public static LinkedList<Component> newObjects = new LinkedList<>();
    public static LinkedList<Component> delObjects = new LinkedList<>();

    public void setObjects(HashMap<Integer, Component> objects) {
        this.objects = objects;
    }

    private HashMap<Integer, Component> UpdateObjects()
    {
        for (Map.Entry<Integer, Component> stringGameObjectEntry : objects.entrySet()) {
            Component s = (Component) ((Map.Entry) stringGameObjectEntry).getValue();
            s.Update();

        }
        return objects;
    }

    public void Update() {
        ObjectHandler.setObjects(UpdateObjects());
        if(CalcThread.newObjects.size()>0) {
            for (Component o : CalcThread.newObjects) {
                ObjectHandler.addObject(o);
            }
        }
        if(CalcThread.delObjects.size()>0) {
            for (Component o : CalcThread.delObjects) {
                ObjectHandler.removeObject(o);
            }
        }

        newObjects.clear();
        delObjects.clear();
    }

    @Override
    public void run() {
        super.run();
        ObjectHandler.setObjects(UpdateObjects());

    }
}
