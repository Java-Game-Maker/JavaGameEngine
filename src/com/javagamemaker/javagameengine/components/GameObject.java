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

    @Override
    public void start() {
        super.start();

        if(Main.getSelectedScene().isDebugMode()){
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Graphics2D g) {

        if(JavaGameEngine.getSelectedScene().isDebugMode()){
            super.render(g);
            Color prev = g.getColor();
            if(getChild(new Sprite())==null){

                g.setColor(color);
                g.fillPolygon(getPolygon());
                if(JavaGameEngine.getSelectedScene().getSelectedComponent() == this){
                    g.setColor(Color.GREEN);
                    g.drawPolygon(getPolygon());
                }
                g.setColor(prev);
            }
            g.setColor(color);
            g.fillPolygon(getPolygon());
            g.setColor(prev);

        }
        else if(visible){
            Color prev = g.getColor();
            if(getChild(new Sprite())==null){
                g.setColor(color);
                g.fillPolygon(getPolygon());
                g.setColor(prev);
            }
            g.setColor(color);
            g.fillPolygon(getPolygon());
            g.setColor(prev);
            super.render(g);
        }

    }
}
