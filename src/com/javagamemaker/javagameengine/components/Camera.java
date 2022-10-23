package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.msc.Vector2;
import com.javagamemaker.javagameengine.JavaGameEngine;

public class Camera extends Component{


    @Override
    public void start() {
        super.start();
        setPosition(new Vector2(JavaGameEngine.getWindowSize().getX()/2,JavaGameEngine.getWindowSize().getY()/2));
    }

    @Override
    public Vector2 getScale() {
        return scale;
    }

    @Override
    public void setScale(Vector2 scale) {
        this.scale = scale;
    }
}
