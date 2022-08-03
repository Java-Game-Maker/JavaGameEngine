package javagameengine.ui;

import javagameengine.components.Component;
import javagameengine.msc.Vector2;

import java.util.LinkedList;

public class Layout {

    Panel parent;
    public LinkedList<Component> sort(){
        return this.parent.getChildren();
    }

    public Vector2 getScale() {
        return new Vector2(100,100);
    }
}
