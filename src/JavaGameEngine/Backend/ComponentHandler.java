package JavaGameEngine.Backend;

import JavaGameEngine.Components.Component;
import JavaGameEngine.msc.Debug;
import sun.awt.image.ImageWatched;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ComponentHandler {

    /*
    CompnentHandler is handling all the components that should be updates and also the ones to be drawn
    When we want to add a new component we can either add it by calling ObjectHadler.addObject(); and pass
    in the new component.
    However, when we want to add or remove an object in runtime we can't call that. This is because the objects are
    updating. What we have to do is to wait for the next update cycle and add the new component before it.
    So what we do is we add the component to another list UpdateThread.newobjects.
    The update thread will before a new updatable check if there is any new objects and in that case
    add them to this list and then preside to the next update cycle;


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

    public static void removeObject(Component object)
    {
        ComponentHandler.objects.remove((object));
    }

    public static void setObjects(LinkedList<Component> _objects) {
        objects = _objects;
    }

}
