package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.components.lights.Light;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;

public class Player extends Sprite {
    PhysicsBody physicsBody = new PhysicsBody(true);
    float force = 1;
    int i = 0;
    int lastChunk = 0;

    boolean hasBegon = false;
    @Override
    public void start() {
        super.start();
        loadAnimation(new String[]{"/spel2/cookie.png"});
        loadAnimation(new String[]{"/spel2/cookie2.png"});
        add(physicsBody);
        Collider c = new Collider();
        c.setTag("player");
        c.setLocalVertices(new Rect(100,90));
        Light l = new Light();
        l.setRadius(600);
        add(l);
        add(c);
    }

    @Override
    public void updateSecond() {
        super.updateSecond();
        if(hasBegon && i%2==0 && Math.round(physicsBody.velocity.getY())==0)
            physicsBody.addForce(Vector2.up.multiply(50));
        i++;
    }

    @Override
    public void update() {
        super.update();
        if(Input.isKeyDown(Keys.A)){
            if(physicsBody.velocity.getX() > -2)
                physicsBody.addForce(Vector2.left.multiply(force));
        }

        if(Input.isKeyDown(Keys.D)){
            if(physicsBody.velocity.getX() < 2)
                physicsBody.addForce(Vector2.right.multiply(force));
        }
        // wrap player
        if(getPosition().getX() < -300)
        {
            translate(new Vector2(600,0));
        }
        if(getPosition().getX() > 300)
        {
            translate(new Vector2(-600,0));
        }
        if(Input.isKeyDown(Keys.SPACE)){
            hasBegon = true;
        }
        //camera follow player y pos
        JavaGameEngine.getSelectedScene().getCamera().setPosition(getPosition().removeX().subtract(JavaGameEngine.getWindowSize().divide(2)).multiply(-1));
    }

    @Override
    public void onCollisionEnter(CollisionEvent collisionEvent) {
        super.onCollisionEnter(collisionEvent);
        animationIndex = 1;
    }

    @Override
    public void onCollisionLeft() {
        super.onCollisionLeft();
        animationIndex = 0;
    }
}
