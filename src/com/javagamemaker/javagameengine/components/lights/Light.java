package com.javagamemaker.javagameengine.components.lights;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.shapes.Circle;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.awt.geom.Area;
import java.util.LinkedList;

public class Light extends Component {

    @Override
    public void start() {
        super.start();
        setLocalVertices(new Circle(100,100));
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
    }
}
