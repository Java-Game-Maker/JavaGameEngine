package JGame.Objects.Components;

import java.util.ArrayList;

public class ComponentHandler  {

    ArrayList<Component> components = new ArrayList<>();
    private Object parent;

    public void addComponent(Component component)
    {
        components.add(component);
    }

    public <T extends Component> Component getComponent(T component)
    {
        for(Component c : components)
        {
            if(c.getClass().equals(component.getClass()))
                return c;
        }
        return null;
    }
    public <T extends Component> ArrayList<T> getComponents(T component)
    {
        ArrayList<T> components = new ArrayList<>();
        for(Component c : components)
        {
            if(c.getClass().equals(component.getClass()))
            components.add((T) c);
        }

        return components;
    }

}
