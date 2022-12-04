package com.javagamemaker.testing.spel2;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.components.lights.Light;
import com.javagamemaker.javagameengine.components.lights.LightManager;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;

public class Player extends Sprite {
    PhysicsBody physicsBody = new PhysicsBody(true);
    float force = 2;
    int i = 0;
    int lastChunk = 0;

    boolean hasBegon = false;
    GameObject object = new GameObject();
    public Player(){
        layer = 200;
    }
    float coins = 1000;
    @Override
    public void start() {
        super.start();

        Debug.log("asd");
        object.setScale(new Vector2(50,10));
        object.setParentOffset(new Vector2(50,0));

        add(object);
        loadAnimation(new String[]{"/spel2/sprites/cookie.png"});
        loadAnimation(new String[]{"/spel2/sprites/cookie2.png"});
        add(physicsBody);
        Collider c = new Collider(true);
        c.setTag("player");
        c.setLocalVertices(new Rect(100,90));
        Light l = new Light();
        l.setRadius(600);
        //LightManager.opacity = 0.5f;
        add(l);
        add(c);
    }

    @Override
    public void updateSecond() {
        super.updateSecond();
        if(hasBegon && i%2==0 && Math.round(physicsBody.velocity.getY())==0){
            Scene.playSound("/spel2/sound/jump.wav",0.2f);
            physicsBody.addForce(Vector2.up.multiply(52*1.5f));
        }
        i++;
    }

    @Override
    public void update() {
        super.update();
        Level1.coinsLabel.setText("Coins: "+coins);
        if(Input.isKeyDown(Keys.A)){
            if(physicsBody.velocity.getX() > -5)
                physicsBody.addForce(Vector2.left.multiply(force));
        }

        if(Input.isKeyDown(Keys.D)){
            if(physicsBody.velocity.getX() < 5)
                physicsBody.addForce(Vector2.right.multiply(force));
        }
        float width = Main.getSelectedScene().getWidth();
        // wrap player
        if(getPosition().getX() < -width/2)
        {
            translate(new Vector2(width,0));
        }
        if(getPosition().getX() > width/2)
        {
            translate(new Vector2(-width,0));
        }
        if(Input.isKeyDown(Keys.SPACE)){
            hasBegon = true;
        }
        if(Input.isMousePressed(Keys.LEFTCLICK) && coins > 0){
            Vector2 startPos = object.getPosition().add(object.getPosition().lookAt(Input.getMousePosition()).multiply(50));
            Main.getSelectedScene().instantiate(new Bullet(startPos,Vector2.getDirection(object.getPosition().lookAtDouble(Input.getMousePosition()))));
            coins --;
        }
        object.rotateTo(object.getPosition().lookAtDouble(Input.getMousePosition()),new Vector2(-20,0));
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
