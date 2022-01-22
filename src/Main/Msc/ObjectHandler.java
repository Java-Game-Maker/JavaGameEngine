package Main.Msc;

import Main.Objects.Object;

import java.util.ArrayList;

public class ObjectHandler {

    private static ArrayList<Object> objects = new ArrayList<>();

    public static void AddObject(Object object)
    {
        objects.add(object);
    }

    public static ArrayList<Object> getObjects() {
        return objects;
    }

    public static void setObjects(ArrayList<Object> objects2) {
        objects = objects2;
    }
}
