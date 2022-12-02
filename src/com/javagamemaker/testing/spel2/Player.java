package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Player extends GameObject {
    PhysicsBody physicsBody = new PhysicsBody(true);
    float force = 1;
    @Override
    public void start() {
        super.start();
        add(physicsBody);
        add(new Collider());
    }

    int i = 0;
    @Override
    public void updateSecond() {
        super.updateSecond();
        if(i%4==0)
            physicsBody.addForce(Vector2.up.multiply(50));
        i++;
    }

    @Override
    public void update() {
        super.update();
        if(Input.isKeyDown(Keys.A)){
           //physicsBody.addForce(Vector2.left.multiply(force));
            translate(Vector2.left.multiply(force));
        }
        if(Input.isKeyDown(Keys.D)){
            //physicsBody.addForce(Vector2.right.multiply(force));
            translate(Vector2.right.multiply(force));
        }
        JavaGameEngine.getSelectedScene().getCamera().setPosition(getPosition().add(JavaGameEngine.getWindowSize().divide(2)));
    }
}
