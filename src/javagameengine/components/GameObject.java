package javagameengine.components;

import javagameengine.JavaGameEngine;
import javagameengine.components.shapes.Rect;
import javagameengine.input.Input;
import javagameengine.msc.Vector2;
import testing.Main;

import java.awt.*;
import java.util.LinkedList;


/**
 * The same as component but renders the polygon
 */
public class GameObject extends Component{

    private Color color = new Color(60,60,60);

    public GameObject(LinkedList<Vector2> localVertices){
        super(localVertices);
    }
    public GameObject(){
        super();
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
            Color prev = g.getColor();
            if(getChild(new Sprite())==null){

                g.setColor(color);
                g.fillPolygon(getPolygon());
                if(JavaGameEngine.getSelectedScene().selectedComponent == this){
                    g.setColor(Color.GREEN);
                    g.drawPolygon(getPolygon());
                }
                g.setColor(prev);
            }
            g.setColor(color);
            g.fillPolygon(getPolygon());
            g.setColor(prev);

            super.render(g);
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
