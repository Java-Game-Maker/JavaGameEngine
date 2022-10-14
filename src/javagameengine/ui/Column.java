package javagameengine.ui;

import javagameengine.components.Component;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.util.LinkedList;

public class Column extends Layout{
    public Column(UiElement parent) {
        super(parent);
    }

    public Column() {
    }

    @Override
    public void orient(LinkedList<Component> elements){

        Vector2 scale = Vector2.zero;

        int count = 0;
        for( Component element : elements ){
            float def = (parent.getPosition().getY())+(count*element.getScale().getY())-parent.getScale().getY()/2 + element.getScale().getY()/2;
            //((def + element.getScale().getY()/2) + element.getScale().getY()*count)+((count==0)?0:gap) + parent.getPadding().getTop())
            element.setPosition(new Vector2(parent.getPosition().getX(),  (def + parent.getPadding().getTop()) ));
           // element.setScale(new Vector2(100,50).add(((UiElement)element).getPadding().getX()));

            scale = scale.add(element.getScale());
            count++;
        }
        float parentH = (scale.getY()) ;
        // Debug.log(parentH);
        parent.setScale(new Vector2(scale.getX() / count + parent.getPadding().getX(),parentH + parent.getPadding().getY()));
    }

}
