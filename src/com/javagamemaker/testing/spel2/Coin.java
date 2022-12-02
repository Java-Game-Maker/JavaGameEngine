package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.components.Animation.Animation;
import com.javagamemaker.javagameengine.components.Animation.AnimationPoint;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.util.LinkedList;

public class Coin extends Sprite {

    Animation animation = new Animation();
    public Coin(){
        loadAnimation(new String[]{"/spel2/cookie.png"});
        LinkedList<AnimationPoint> points = new LinkedList<>();
        points.add(new AnimationPoint(new Vector2(0,1),0));
        points.add(new AnimationPoint(new Vector2(0,-1),50));
        animation.setSelectedPoints(points);
        Collider c =new Collider(true);
        c.setTrigger(true);
        add(c);
    }

    @Override
    protected void onTriggerEnter(CollisionEvent collisionEvent) {
        super.onTriggerEnter(collisionEvent);
        if(collisionEvent.getCollider2().getTag() == "player")
            destroy();
    }

    @Override
    public void update() {
        super.updateSecond();
        translate(animation.getPoint().multiply(-1));
    }
}
