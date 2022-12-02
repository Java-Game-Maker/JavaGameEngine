package com.javagamemaker.javagameengine.components.lights;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.shapes.Circle;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.awt.geom.Area;
import java.util.LinkedList;

public class Light extends Component {
    private int radius = 500;
    @Override
    public void start() {
        super.start();
        setLocalVertices(new Circle(radius,radius));
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

         LinkedList<Vector2> ver = new LinkedList<>();
         for(Vector2 vertex : vertices){
             ver.add(vertex.add(new Vector2(JavaGameEngine.getSelectedScene().getCamera().getPosition())));
         }
         int[] x = new int[ver.size()];
         int[] y = new int[ver.size()];
         int i = 0;

         for(Vector2 point : ver){
             x[i] = (int) point.getX();
             y[i] = (int) point.getY();
             i++;
         }

         LightManager.screen.subtract(new Area(new Polygon(x,y,vertices.size())));

         float[] dist = { 0.5f, 1 };
         Color[] color = { new Color(0,0,0,0), new Color(0,0,0, LightManager.opacity) };
         Point center = new Point((int) (getPosition().getX()),(int) (getPosition().getY()));
         RadialGradientPaint p = new RadialGradientPaint(center,  radius, dist, color);
         g.setPaint(p);
         g.fill(getShape());
         //g.fillOval((int) getPosition().getX()-radius/2, (int) getPosition().getY()-radius/2,radius,radius);
    }
}
