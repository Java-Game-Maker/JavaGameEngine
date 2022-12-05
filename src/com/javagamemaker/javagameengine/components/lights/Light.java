package com.javagamemaker.javagameengine.components.lights;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.shapes.Circle;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

public class Light extends Component {
    private int radius = 501;

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void start() {
        super.start();
        setLocalVertices(new Rect(radius,radius));
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        if(JavaGameEngine.getSelectedScene().useLight){
            //g.fill(getShape());

            float[] dist = { 0.2f, 1 };
            Color[] color = { new Color(0,0,0,0), new Color(0,0,0, LightManager.opacity) };
            Point center = new Point((int) (getPosition().getX()),(int) (getPosition().getY()));
            RadialGradientPaint p = new RadialGradientPaint(center,  radius, dist, color);

            g.setPaint(p);

            g.fill(new Ellipse2D.Double(getPosition().getX()-radius,getPosition().getY()-radius,radius*2,radius*2));
            //g.fillOval((int) getPosition().getX()-radius/2, (int) getPosition().getY()-radius/2,radius,radius);
            LightManager.screen.subtract(new Area(new Ellipse2D.Double(getPosition().getX()-radius+(new Vector2(JavaGameEngine.getSelectedScene().getCamera().getPosition())).getX(),getPosition().getY()-radius+(new Vector2(JavaGameEngine.getSelectedScene().getCamera().getPosition()).getY()),radius*2,radius*2)));
        }
    }
}
