package JavaGameEngine.Backend;

import JavaGameEngine.Components.Component;
import java.util.LinkedList;

public class ComponentHandler {

    /*


    Maybe we only need to store game-objects and not Components
    it is only the game-objects that should me parents I think
     */
    private static LinkedList<Component> objects = new LinkedList<>();

    public static void addObject(Component object) {

        ComponentHandler.objects.add(object);
    }

    public static LinkedList<Component> getObjects() {
        return objects;
    }

    public static LinkedList<Component> getAllObjects() {

        return getAllObjects_(objects);
    }

    private static LinkedList<Component> getAllObjects_(LinkedList<Component> parent) {
        for(Component component : parent){
            parent.addAll(component.getChildren());
            return parent;
        }
        return parent;
    }

    public static void removeObject(Component object)
    {
        ComponentHandler.objects.remove((object));
    }

    public static void setObjects(LinkedList<Component> _objects) {
        objects = _objects;
    }

}
