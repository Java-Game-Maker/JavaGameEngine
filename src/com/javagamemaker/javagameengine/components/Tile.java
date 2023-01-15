package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.util.ArrayList;

public class Tile extends GameObject{
    @Override
    public void translate(Vector2 towards) {
        int width = 1;
        try{
            width = ((TileMap)JavaGameEngine.getSelectedScene()).tileWidth;
        }catch (Exception e){}
        towards = towards.multiply(width);
        super.translate(towards);
    }

    @Override
    public void setPosition(Vector2 position) {
        //this.lastPosition = this.position;
        int width = 1;
        try{
            width = ((TileMap)JavaGameEngine.getSelectedScene()).tileWidth;
        }catch (Exception e){}

        if(getParent()!=null){
            this.position = position.add(parentOffset.multiply(width*2)).add(rotOffset);

        }else{
            prevPosition = this.position;
            this.position = position;
        }
        for(Component c : getChildren()){
            c.setPosition(position);
        }

        updateVertices();

    }
}
