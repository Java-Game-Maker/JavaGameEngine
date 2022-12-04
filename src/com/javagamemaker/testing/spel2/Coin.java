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
        loadAnimation(new String[]{"/spel2/sprites/milk.png"});
        LinkedList<AnimationPoint> points = new LinkedList<>();
        points.add(new AnimationPoint(new Vector2(0,1),0));
        points.add(new AnimationPoint(new Vector2(0,-1),50));
        animation.setSelectedPoints(points);

        Collider c = new Collider(true);
        c.setTrigger(true);
        add(c);
        c.updateVertices();
        setScale(new Vector2(50,50));
    }

    @Override
    protected void onTriggerEnter(CollisionEvent collisionEvent) {
        super.onTriggerEnter(collisionEvent);
        if(collisionEvent.getCollider2().getTag() == "player"){
            destroy();
            if(Main.player.physicsBody.velocity.getY() > -8)
                Main.player.physicsBody.addForce(Vector2.up.multiply(50));
            Main.player.coins ++;
            Main.getSelectedScene().playSound(("/spel2/sound/coin.wav"));
        }

    }

    @Override
    public void update() {
        super.updateSecond();
        //translate(animation.getPoint().multiply(-1));
    }
}
