package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.Grabber;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Random;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Ground extends Sprite {
    public Ground(float width,Vector2 pos){
        loadAnimation(new String[]{"/spel2/groundtile.png"});
        setScale(new Vector2(width,50));
        setPosition(pos);
    }

    @Override
    public void onCameraLeft() {
        super.onCameraLeft();
        if(getPosition().getY() > Main.player.getPosition().getY()){
            float diff = Main.getSelectedScene().screen.getBounds().height;
            setPosition(new Vector2(new Random().nextFloat(-100,300),getPosition().getY()-diff/2-100));
            Debug.log(getPosition());
        }
    }
    @Override
    public void update() {
        super.update();
        if(Main.player.getPosition().getY()+90 < getPosition().getY() && getChild(new Collider())==null){
            Collider c =new Collider(true);
            c.setLocalVertices(new Rect(getScale()));
            c.updateVertices();
            add(c);
        }
        else if(Main.player.getPosition().getY()+50 > getPosition().getY() && getChild(new Collider())!=null){
            getChild(new Collider()).destroy();
        }

    }
}
