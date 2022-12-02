package com.javagamemaker.javagameengine.components.lights;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Camera;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.awt.geom.Area;

public class LightManager {



    public static Area screen = new Area(new Rectangle(0,0,1000,1000));
    public static float opacity = 0.6f;
    public static void render(Graphics2D g){
        g.setColor(new Color(0,0,0,opacity));
        g.fill(screen);

        Camera camera = JavaGameEngine.getSelectedScene().getCamera();

        Vector2 scale = camera.getScale();
        //scale = JavaGameEngine.getWindowSize().multiply(camera.getScale().divide(camera.getScale().multiply(camera.getScale())));

        float width = g.getClip().getBounds().width;
        float percentW = 1-scale.getX();
        float height = g.getClip().getBounds().height;
        float percentH = 1-scale.getY();
        int x = (int) (width*-percentW/2);
        int y = (int) (height*-percentH/2);

        screen = new Area(new Rectangle(x,y, (int) (width), (int) height));
    }


}
