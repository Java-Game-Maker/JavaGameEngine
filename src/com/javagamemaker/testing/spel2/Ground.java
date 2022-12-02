package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.Grabber;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Ground extends GameObject {

    public Ground(float width,Vector2 pos){
       setScale(new Vector2(width,50));
       setPosition(pos);
    }

    @Override
    public void update() {
        super.update();
        if(Main.player.getPosition().getY() < getPosition().getY() && getChild(new Collider())==null){
           add(new Collider(localVertices));
        }
    }
}
