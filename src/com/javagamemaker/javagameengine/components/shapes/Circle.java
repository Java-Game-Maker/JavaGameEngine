package com.javagamemaker.javagameengine.components.shapes;

import com.javagamemaker.javagameengine.msc.Vector2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Circle extends ArrayList<Vector2> {

    int width, height;

    public Circle(int width, int height){
        // higher scaler better preformance badder circles
        float scaler = 3f;
        for(int x = 0;x <= 360/scaler;x++){
            add(new Vector2(width* (float) Math.cos(Math.toRadians(x*scaler)), height * (float) Math.sin(Math.toRadians(x*scaler))));
        }

    }
    public Circle(Vector2 scale){
        int width = (int) scale.getX();
        int height = (int) scale.getY();
        for(int x = 0;x <= 360;x++){
            add(new Vector2(width* (float) Math.cos(Math.toRadians(x)), height * (float) Math.sin(Math.toRadians(x))));
        }
    }
}
