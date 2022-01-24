package JGame.Msc;

import JGame.Objects.GameObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ObjectHandler {

    private static HashMap<Integer, GameObject> objects = new HashMap<>();

    public static void addObject(GameObject object)
    {
        System.out.println("add");
        System.out.println(getLargestId());
        object.setId(getLargestId());
        objects.put(getLargestId(),object);
    }

    private static int getLargestId()
    {
        int s = 0;
        for (Map.Entry<Integer, GameObject> stringGameObjectEntry : objects.entrySet()) {
            if(stringGameObjectEntry.getKey()>=s)
                s=(stringGameObjectEntry.getKey()+1);
        }
        return s;
    }

    public static HashMap<Integer, GameObject> getHashMapObjects() {
        return objects;
    }
    public static LinkedList<GameObject> getObjects() {
        LinkedList<GameObject> obs = new LinkedList<>();
        for (Map.Entry<Integer, GameObject> stringGameObjectEntry : objects.entrySet()) {
            GameObject s = (GameObject) ((Map.Entry) stringGameObjectEntry).getValue();
            obs.add(s);
        }
        return obs;
    }
    public static void removeObject(GameObject object)
    {
        objects.remove(object.getId());

    }

    public static void setObjects(HashMap<Integer, GameObject> _objects) {
       // System.out.println("Set");
        objects = _objects;
    }
}
