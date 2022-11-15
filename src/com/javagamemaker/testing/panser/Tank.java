package com.javagamemaker.testing.panser;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;
import com.javagamemaker.testing.spel1.Explosion;

import java.awt.*;

public class Tank extends Sprite {
        Sprite head = new Sprite();
        public Tank(Vector2 pos, Vector2 img){
            loadAnimation(new Rectangle[]{new Rectangle((int) img.getX()*150, (int) img.getY()*210,150,180)},"/2.png");
            Collider c = new Collider(false);
            c.setTag(getClass().getName());
            c.setTrigger(true);
            add(c);
            setPosition(pos);
            tag = "player";

        }

    @Override
    public void start() {
        super.start();
        head.loadAnimation(new String[]{"/head.png"});
        head.setParentOffset(new Vector2(0,10));
        add(head);
        head.setScale(new Vector2(80,80));

    }

    public void doAction(){
            if(Input.isKeyDown(Keys.W)){
                angle = 180;
                setPosition(getPosition().add(Vector2.up));
            }
            else if(Input.isKeyDown(Keys.S)){
                angle = 0;
                setPosition(getPosition().add(Vector2.down));
            }
            else if(Input.isKeyDown(Keys.A)){
                angle = 90;
                setPosition(getPosition().add(Vector2.left));
            }
            else if(Input.isKeyDown(Keys.D)){
                angle = -90;
                setPosition(getPosition().add(Vector2.right));
            }
            if(Input.isMousePressed(Keys.LEFTCLICK)){
                Explosion e = new Explosion(getPosition(),Main.animations.animations);
                e.setScale(new Vector2(50,50));
                JavaGameEngine.getSelectedScene().instantiate(e);
                JavaGameEngine.getSelectedScene().instantiate(new Bullet(getPosition(),head.getAngle(),getChild(new Collider()).getTag()));
            }
            float value = (getPosition().lookAt(Input.getMousePosition())-90-head.getAngle());
            head.rotate(value/50,new Vector2(0,-10));

        }

        @Override
        public void update() {
            super.update();
            doAction();
        }

        @Override
        protected void onTriggerEnter(CollisionEvent collisionEvent) {
            super.onTriggerEnter(collisionEvent);
            JavaGameEngine.getSelectedScene().instantiate(new Explosion(getPosition(),Main.animations.animations));
            collisionEvent.getCollider1().getFirstParent().destroy();
            destroy();
        }
}
