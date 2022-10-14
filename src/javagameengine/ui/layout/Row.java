package javagameengine.ui.layout;

import javagameengine.components.Component;
import javagameengine.msc.Vector2;
import javagameengine.ui.UiElement;
import javagameengine.ui.layout.Layout;

import java.util.LinkedList;

public class Row extends Layout {
    public Row(UiElement parent) {
        super(parent);
    }

    public Row() {
    }

    @Override
    public void orient(LinkedList<Component> elements){

        Vector2 scale = Vector2.zero;

        int count = 0;
        for( Component element : elements ){
            float def = (parent.getPosition().getX())+(count*element.getScale().getX())-parent.getScale().getX()/2 + element.getScale().getX()/2;
            //((def + element.getScale().getY()/2) + element.getScale().getY()*count)+((count==0)?0:gap) + parent.getPadding().getTop())
            element.setPosition(new Vector2( (def + parent.getPadding().getLeft()) , parent.getPosition().getY()));
            //element.setScale(new Vector2(100,50).add(((UiElement)element).getPadding().getX()));

            scale = scale.add(element.getScale());
            count++;
        }
        float parentH = (scale.getX()) ;
        // Debug.log(parentH);
        parent.setScale(new Vector2( parentH + parent.getPadding().getX(),scale.getY() / count + parent.getPadding().getY()));
    }

}
