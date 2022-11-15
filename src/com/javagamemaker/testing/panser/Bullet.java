package com.javagamemaker.testing.panser;

import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Bullet extends GameObject {

    public int damage = 10;
    public int timer = 4;
    public Bullet(Vector2 startPos,float angle,String otherTag){
        PhysicsBody b = new PhysicsBody();
        b.velocity = Vector2.getDirection(angle+90).multiply(10);
        add(b);
        setLocalVertices(new Rect(20,10));
        setPosition(startPos);
        rotateTo(angle+90,Vector2.zero);
        updateVertices();
        tag = "bullet";

        Collider c = new Collider();
        c.setTrigger(true);
        c.addIgnoreTag(otherTag);
        add(c);

        c.setLocalVertices(getLocalVertices());

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
