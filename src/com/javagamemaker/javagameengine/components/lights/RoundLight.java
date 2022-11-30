package com.javagamemaker.javagameengine.components.lights;

import com.javagamemaker.javagameengine.components.Component;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RoundLight extends Light {

    @Override
    public Polygon getPolygon() {
        Shape s = new Ellipse2D.Double(0,0,100,100);
        return (Polygon) s;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }
}
