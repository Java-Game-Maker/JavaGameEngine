package javagameengine.ui;

import javagameengine.components.Component;
import javagameengine.components.shapes.Rect;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.util.LinkedList;

public class Layout {
    UiElement parent = new UiElement();
    int gap = 3;
    public Layout() {
    }
    public Layout(UiElement parent) {
        this.parent = parent;
    }

    public void orient(LinkedList<Component> elements) {

    }

}