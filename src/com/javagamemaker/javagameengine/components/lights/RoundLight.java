package com.javagamemaker.javagameengine.components.lights;

import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RoundLight extends Light {
    @Override
    public Shape getShape() {

        Vector2 lightScale = new Vector2(500,500);
        Vector2 lightPosition = getPosition();

        Shape s = new Ellipse2D.Double(lightPosition.getX(),lightPosition.getY(),300,300);
        return  s;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.fill(new Ellipse2D.Double(getPosition().getX(),getPosition().getY(),10,10));
    }
}
