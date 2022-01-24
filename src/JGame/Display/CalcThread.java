package JGame.Display;

import JGame.Msc.ObjectHandler;
import JGame.Objects.GameObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CalcThread extends Thread{

    private static HashMap<Integer, GameObject> objects = new HashMap<>();
    public static LinkedList<GameObject> newObjects = new LinkedList<>();
    public static LinkedList<GameObject> delObjects = new LinkedList<>();

    public void setObjects(HashMap<Integer, GameObject> objects) {
        this.objects = objects;
    }

    private HashMap<Integer, GameObject> update()
    {
        for (Map.Entry<Integer, GameObject> stringGameObjectEntry : objects.entrySet()) {
            GameObject s = (GameObject) ((Map.Entry) stringGameObjectEntry).getValue();
            s.Update();

        }
        return objects;
    }

    public void Update() {
        ObjectHandler.setObjects(update());
        if(CalcThread.newObjects.size()>0) {
            for (GameObject o : CalcThread.newObjects) {
                ObjectHandler.addObject(o);
            }
            System.out.print("new");

        }
        if(CalcThread.delObjects.size()>0) {
            for (GameObject o : CalcThread.delObjects) {
                ObjectHandler.removeObject(o);

            }
        }

        newObjects.clear();
        delObjects.clear();
    }

    @Override
    public void run() {
        super.run();
        ObjectHandler.setObjects(update());

    }
}
