package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * The same as component but renders the polygon
 */
public class GameObject extends Component{

    private Color color = new Color(60,60,60);

    public GameObject(ArrayList<Vector2> localVertices){
        super(localVertices);
    }
    public GameObject(){
        super();
    }

    public GameObject(Vector2 vector2) {
        super(vector2);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void render(Graphics2D g) {
        if(visible){
            Camera camera = JavaGameEngine.getSelectedScene().getCamera();
            Color prev = g.getColor();
            if(getChild(new Sprite())==null){
                g.setColor(color);
                g.fill(getShape());
                g.setColor(prev);
            }
            renderChildren(g);
            //g.translate(camera.getPosition().getX()*layer/100,camera.getPosition().getY()*layer/100);
            //g.translate(-camera.getPosition().getX()*layer/100,-camera.getPosition().getY()*layer/100);
        }
    }
}
