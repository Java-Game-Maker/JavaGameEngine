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
    public Coin(Vector2 pos){
        setPosition(pos);
        loadAnimation(new String[]{"/spel2/milk.png"});
        LinkedList<AnimationPoint> points = new LinkedList<>();
        points.add(new AnimationPoint(new Vector2(0,1),0));
        points.add(new AnimationPoint(new Vector2(0,-1),50));
        animation.setSelectedPoints(points);

        setScale(new Vector2(50,50));
        Collider c = new Collider(false);
        c.setTrigger(true);
        add(c);
        c.setScale(new Vector2(50,50));
        c.updateVertices();
    }

    @Override
    protected void onTriggerEnter(CollisionEvent collisionEvent) {
        super.onTriggerEnter(collisionEvent);
        if(collisionEvent.getCollider2().getTag() == "player"){
            destroy();
            if(Main.player.physicsBody.velocity.getY() > -8)
            Main.player.physicsBody.addForce(Vector2.up.multiply(6));
        }

    }

    @Override
    public void update() {
        super.updateSecond();
        //translate(animation.getPoint().multiply(-1));
    }
}
