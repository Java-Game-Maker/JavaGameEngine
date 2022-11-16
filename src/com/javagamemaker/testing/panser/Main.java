package com.javagamemaker.testing.panser;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.CameraMovement;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;
import com.javagamemaker.testing.spel1.Explosion;

import java.awt.*;

/**
 *
 */

public class Main extends JavaGameEngine {
    public static Sprite animations = new Sprite();
    public static Tank tank = new Tank(Vector2.zero,Vector2.zero){
        @Override
        public void render(Graphics2D g) {
            super.render(g);
            g.drawOval((int) Input.getMousePosition().getX()-20, (int) Input.getMousePosition().getY()-17,50,50);
        }
    };

    public static void main(String[] args){

        Rectangle[] rectangles = new Rectangle[(4)];
        int i = 0;
        for(int x = 0 ; x < 2 ; x++ ){
            for(int y = 0 ; y < 2; y++ ){
                Rectangle r = new Rectangle(y*100,x*100,100,100);
                rectangles[i] = r;
                i++;
            }
        }
        animations.loadAnimation(rectangles, "/spel1/explosion.png");
        Debug.showWhere = true;
        Scene s = new Scene(){
            @Override
            public void update() {
                if(Input.isMousePressed(Keys.RIGHTCLICK)){
                    Bot b = new Bot(Input.getMousePosition());
                    instantiate(b);
                }
                super.update();
            }
        };
        s.getCamera().add(new CameraMovement());
        s.add(new Tank(new Vector2(200,0),Vector2.zero){
            public void doAction(){
                if(Input.isKeyDown(Keys.U)){
                    angle = 180;
                    setPosition(getPosition().add(Vector2.up));
                }
                else if(Input.isKeyDown(Keys.J)){
                    angle = 0;
                    setPosition(getPosition().add(Vector2.down));
                }
                else if(Input.isKeyDown(Keys.H)){
                    angle = 90;
                    setPosition(getPosition().add(Vector2.left));
                }
                else if(Input.isKeyDown(Keys.K)){
                    angle = -90;
                    setPosition(getPosition().add(Vector2.right));
                }
                if(Input.isKeyPressed(Keys.ALTGR)){
                    Explosion e = new Explosion(getPosition(),Main.animations.animations);
                    e.setScale(new Vector2(50,50));
                    JavaGameEngine.getSelectedScene().instantiate(e);
                    JavaGameEngine.getSelectedScene().instantiate(new Bullet(getPosition(),head.getAngle(),getChild(new Collider()).getTag()));
                }

                if(Input.isKeyDown(Keys.I)){
                    head.rotate(1,new Vector2(0,-10));
                }
                if(Input.isKeyDown(Keys.Y)){
                    head.rotate(-1,new Vector2(0,-10));
                }



            }
        });
        s.add(new Tank(Vector2.zero, Vector2.zero));

        setSelectedScene(s);
        start();
    }

}
