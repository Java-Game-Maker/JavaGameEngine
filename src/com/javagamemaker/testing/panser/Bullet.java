package com.javagamemaker.testing.panser;

import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Bullet extends Sprite {

    public int damage = 10;
    public int timer = 4;
    public Bullet(Vector2 startPos,float angle,String otherTag){
        tag = "rocket";
        PhysicsBody b = new PhysicsBody();
        b.velocity = Vector2.getDirection(angle+90).multiply(10);
        add(b);
        loadAnimation(new String[]{"/bullet.png"});
        setScale(new Vector2(60,30));
        setPosition(startPos);
       tag = "bullet";

        Collider c = new Collider(false);
        c.setTrigger(true);
        c.addIgnoreTag(otherTag);
        c.setScale(new Vector2(60,30));

        updateVertices();
        add(c);

        rotateTo(angle+90,Vector2.zero);
        updateVertices();

    }
    public Bullet(String img) {
        loadAnimation(new String[]{img});
        Collider c = new Collider();
        c.setTrigger(true);
        add(c);
    }
    @Override
    public void updateSecond() {
        super.updateSecond();
        if(timer < 0){
            destroy();
        }
        timer--;
    }
}
