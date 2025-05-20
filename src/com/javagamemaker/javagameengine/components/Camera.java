package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;


/**
 * Every scene has a camera and the camera is controlling the graphics
 * The camera scale controls the zoom
 * and its position controls the graphics position
 */
public class Camera extends Component {

    public boolean parallax = false;
    @Override
    public void start() {
        super.start();
        setPosition(new Vector2(JavaGameEngine.getWindowSize().getX() / 2, JavaGameEngine.getWindowSize().getY() / 2));
    }

    @Override
    public Vector2 getScale() {
        return scale;
    }

    @Override
    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

    }
}
