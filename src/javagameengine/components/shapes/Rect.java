package javagameengine.components.shapes;

import javagameengine.msc.Vector2;

import java.util.LinkedList;

public class Rect extends LinkedList<Vector2> {

    int width, height;

    public Rect(int width, int height){
        add(new Vector2(-width/2,-height/2)); // top left
        add(new Vector2(-width/2,height/2));  // bottom left
        add(new Vector2(width/2,height/2));   // bottom right
        add(new Vector2(width/2,-height/2));   // top right
    }
    public Rect(Vector2 scale){
        int width = (int) scale.getX();
        int height = (int) scale.getY();
        add(new Vector2(-width/2,-height/2)); // top left
        add(new Vector2(-width/2,height/2));  // bottom left
        add(new Vector2(width/2,height/2));   // bottom right
        add(new Vector2(width/2,-height/2));   // top right
    }
}
