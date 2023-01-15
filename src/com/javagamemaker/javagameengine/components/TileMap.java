package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.msc.Vector2;

public class TileMap extends Scene {

    public int tileWidth=16;

    @Override
    public void add(Component component) {
        component.setScale(new Vector2(tileWidth,tileWidth));
        component.setPosition(component.getPosition().multiply(tileWidth));
        super.add(component);
    }
}
