package com.javagamemaker.testing.panser;

import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Rocket extends Bullet {

    public Rocket(String img){
        super(img);
        tag = "rocket";
        loadAnimation(new String[]{"/missile.png"});
        setScale(new Vector2(30,50));
        Collider c = new Collider();
        c.setTrigger(true);
        add(c);
        c.setScale(new Vector2(30,50));
    }
    public Rocket(Vector2 pos, float angle, String tag){

        super(pos,angle,tag);
        animations.clear();
        loadAnimation(new String[]{"/missile.png"});
    }

}
