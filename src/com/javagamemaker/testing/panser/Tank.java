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
        int health = 100;
        Main.Canvas.HealthBar healthBar = new Main.Canvas.HealthBar();
        Main.Canvas.ItemHolder item1 = new Main.Canvas.ItemHolder();
        Main.Canvas.ItemHolder item2 = new Main.Canvas.ItemHolder();

        public Tank(Vector2 pos, Vector2 img){
            loadAnimation(new Rectangle[]{new Rectangle((int) img.getX()*150, (int) img.getY()*210,150,180)},"/2.png");
            Collider c = new Collider(false);
            c.setTag(getClass().getName());
            c.setTrigger(true);
            add(c);
            setPosition(pos);

            head.loadAnimation(new String[]{"/head.png"});
            head.setParentOffset(new Vector2(0,10));
            add(head);

            Main.canvas.add(healthBar);
            Main.canvas.add(item1);
            Main.canvas.add(item2);
        //  add(new Collider());
            tag = "player";
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
            }
            else if(Input.isKeyDown(Keys.D)){
                angle = 90;
                setPosition(getPosition().add(Vector2.left));
                angle = -90;
                setPosition(getPosition().add(Vector2.right));
            }
            if(Input.isKeyPressed(Keys.SPACE)){
                Explosion e = new Explosion(getPosition().add(Vector2.getDirection(head.getAngle()+90).multiply(40).add(head.getParentOffset())),Main.animations.animations);
                e.setScale(new Vector2(50,50));

                JavaGameEngine.getSelectedScene().instantiate(e);

                Bullet b = new Bullet(getPosition(),head.getAngle(),getChild(new Collider()).getTag());
                b.animations = selectedBullet.animations;
                b.rotateTo(selectedBullet.getAngle(),Vector2.zero);
                Debug.log(selectedBullet.getClass().getName());

                JavaGameEngine.getSelectedScene().instantiate(b);
            }
            if(Input.isKeyDown(Keys.E)){
                head.rotate(1,new Vector2(0,-10));
            }
            if(Input.isKeyDown(Keys.Q)){
                head.rotate(-1,new Vector2(0,-10));
            }
            if(Input.isKeyPressed(Keys.ONE)){
                item1.selected = true;
                item2.selected = false;
                selectedBullet = new Bullet("");
            }
            if(Input.isKeyPressed(Keys.TWO)){
                item1.selected = false;
                item2.selected = true;
                selectedBullet = new Rocket("/missile.png");
            }
        }
    Bullet selectedBullet = new Bullet(getPosition(),90,"");
    @Override
        public void update() {
            super.update();
            doAction();
            healthBar.health = health;
        }

        @Override
        protected void onTriggerEnter(CollisionEvent collisionEvent) {
            super.onTriggerEnter(collisionEvent);
            if(!collisionEvent.getCollider2().getFirstParent().getTag().equals("rocket")){
                collisionEvent.getCollider2().getFirstParent().destroy();
                JavaGameEngine.getSelectedScene().instantiate(new Explosion(getPosition(),Main.animations.animations));

                if(health<=0){
                    destroy();
                }
                health-=10;
            }else{
                item1.s = new Rocket("/missile.png");
                item1.s.setPosition(new Vector2(50,50));
                item1.selected = true;

                collisionEvent.getCollider2().getFirstParent().destroy();
            }
        }
}
