package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Bullet extends Sprite {

    public Bullet(Vector2 startPos, Vector2 dir){
        loadAnimation(new String[]{"/spel2/sprites/cookie.png"});
        PhysicsBody p = new PhysicsBody();
        p.velocity = dir.multiply(10);
        p.setFriction(0.01f);
        add(p);
        Collider c = new Collider(true);
        c.setTrigger(true);
        add(c);
        setPosition(startPos);
        setScale(new Vector2(20,20));
    }
    int t = 0;

    @Override
    public void updateSecond() {
        super.updateSecond();
        if(t>=3) destroy();
        t++;
    }
}
