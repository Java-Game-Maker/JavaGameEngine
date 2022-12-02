package com.javagamemaker.testing.spacer2;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.msc.Vector2;


public class Laser extends Sprite {
    int sec = 0;
    public Collider c;
    public float reloadTime = 10;
    public Laser(float angle, Vector2 pos){
        init(angle,pos,"/spel1/laser.png");
    }
    public void init(float angle, Vector2 pos, String image){
        setScale(new Vector2(10,25));
        setPosition(pos.add(Vector2.getDirection(angle-90).multiply(20)));
        PhysicsBody b = new PhysicsBody();
        b.setFreeze(true);
        b.setFriction(0);
        add(b);
        b.velocity = Vector2.getDirection(angle-90).multiply(10);
        loadAnimation(new String[]{image});
        c = new Collider(new Rect(10,25));
        c.setTag("laser");
        c.setTrigger(true);
        c.setVisible(false);
        add(c);
        rotate((float)(angle / JavaGameEngine.deltaTime));
    }
    public Laser(){}

    @Override
    protected void onTriggerEnter(CollisionEvent collisionEvent) {
        destroy();
    }

    @Override
    public void update() {
        super.update();
        if(((PhysicsBody)getChild(new PhysicsBody())).velocity.getMagnitude()<=0.2)
            destroy();
    }

    @Override
    public void updateSecond() {
        sec++;
        if(sec>2)
            destroy();
    }
}
