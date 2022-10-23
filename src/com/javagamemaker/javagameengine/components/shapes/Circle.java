package com.javagamemaker.javagameengine.components.shapes;

import com.javagamemaker.javagameengine.msc.Vector2;

import java.util.LinkedList;

public class Circle extends LinkedList<Vector2> {

    int width, height;

    public Circle(int width, int height){

        for(int x = 0;x <= 360/3;x++){
            add(new Vector2(width* (float) Math.cos(Math.toRadians(x*3)), height * (float) Math.sin(Math.toRadians(x*3))));
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
