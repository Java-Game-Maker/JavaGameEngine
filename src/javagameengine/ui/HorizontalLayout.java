package javagameengine.ui;

import javagameengine.components.Component;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.util.LinkedList;

/**
This layout will sort the children sideways
 */
public class HorizontalLayout extends Layout {


    @Override
    public LinkedList<Component> sort() {
        LinkedList<Component> newComponents = new LinkedList<>();
        for(int i = 0;i<parent.getChildren().size();i++){
            Component component = parent.getChildren().get(i);

            float scale = component.getScale().getX();
            float p = i*component.getScale().getX();
            float parentScale = parent.getScale().getX();

            component.setLocalPosition(new Vector2((i*(scale+parent.getSpacing()))-parentScale+scale+parent.getPadding().getX(),-parent.getPadding().getY()));
            newComponents.add(component);
        }

        return newComponents;
    }
    @Override
    public Vector2 getScale() {
        int height = 0;
        int width = 0;
        for(int i = 0; i < parent.getChildren().size(); i++){
            width += (int) ((parent.getChildren().get(i).getScale().getX()) + parent.getSpacing());
            if(parent.getChildren().get(i).getScale().getY() >= height)
                height = (int) ((int) parent.getChildren().get(i).getScale().getY());
        }
        return new Vector2(width - parent.getSpacing() + parent.getPadding().getX() * 2,height+10);
    }
}
