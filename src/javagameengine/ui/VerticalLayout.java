package javagameengine.ui;

import javagameengine.components.Component;
import javagameengine.msc.Vector2;

import java.util.LinkedList;

/**
 This layout will sort the children downwards
 */
public class VerticalLayout extends Layout {

    @Override
    public LinkedList<Component> sort() {
        LinkedList<Component> newComponents = new LinkedList<>();
        int height = (int) ((int) -parent.getScale().getY() + parent.getPadding().getY());
        for(int i = 0;i<parent.getChildren().size();i++){

            Component component = parent.getChildren().get(i);

            float scale = component.getScale().getY();
            float p = i*component.getScale().getY();
            float parentScale = parent.getScale().getY();


            component.setLocalPosition(new Vector2(-parent.getPadding().getX(),height+scale ));
            height+=scale+parent.getSpacing();

            newComponents.add(component);
        }

        return newComponents;
    }
    @Override
    public Vector2 getScale() {
        int height = 0;
        int width = 0;
        for(int i = 0;i<parent.getChildren().size();i++){
            height += (int) ((parent.getChildren().get(i).getScale().getY())+parent.getSpacing());
            if(parent.getChildren().get(i).getScale().getX()>=width)
                width = (int) ((int) parent.getChildren().get(i).getScale().getX());
        }
        return new Vector2(width + parent.getPadding().getX()*2,height + parent.getPadding().getY() * 2 - parent.getSpacing());
    }


}
