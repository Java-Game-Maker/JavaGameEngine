package JavaGameEngine.Msc;

import JavaGameEngine.Objects.Components.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ObjectHandler {

    private static HashMap<Integer, Component> objects = new HashMap<>();

    public static void addObject(Component object)
    {
          object.setId(getLargestId());
        objects.put(getLargestId(),object);
    }

    private static int getLargestId()
    {
        int s = 0;
        for (Map.Entry<Integer, Component> stringGameObjectEntry : objects.entrySet()) {
            if(stringGameObjectEntry.getKey()>=s)
                s=(stringGameObjectEntry.getKey()+1);
        }
        return s;
    }

    public static HashMap<Integer, Component> getHashMapObjects() {
        return objects;
    }
    public static LinkedList<Component> getObjects() {
        LinkedList<Component> obs = new LinkedList<>();
        for (Map.Entry<Integer, Component> stringGameObjectEntry : objects.entrySet()) {
            Component s = (Component) ((Map.Entry) stringGameObjectEntry).getValue();
            obs.add(s);
        }
        return obs;
    }
    public static void removeObject(Component object)
    {
        objects.remove(object.getId());

    }

    public static void setObjects(HashMap<Integer, Component> _objects) {
       // System.out.println("Set");
        objects = _objects;
    }
}
