package Main.Msc;

import Main.Objects.Object;

import java.util.ArrayList;
import java.util.LinkedList;

public class ObjectHandler {

    private static LinkedList<Object> objects = new LinkedList<>();

    public static void addObject(Object object)
    {
        objects.add(object);
    }

    public static LinkedList<Object> getObjects() {
        return objects;
    }

    public static void removeObject(Object object)
    {
        objects.remove(object);
    }
    public static void setObjects(LinkedList<Object> objects2) {
        objects = objects2;
    }
}
