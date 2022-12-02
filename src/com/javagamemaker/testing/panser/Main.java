package com.javagamemaker.testing.panser;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.CameraMovement;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;
import com.javagamemaker.testing.spel1.Explosion;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 */

public class Main extends JavaGameEngine {
    public static Sprite animations = new Sprite();
    public static Canvas canvas = new Canvas();

    static void createExpulsionAnimation(){
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
    }

    public static void main(String[] args){
        createExpulsionAnimation();
        Scene s = new Scene(){
            @Override
            public void update() {
                super.update();
                canvas.setLocation(new Point(0, (int) gameWindow.getHeight()-canvas.getHeight()*2));
                canvas.setSize(gameWindow.getWidth(),100);
            }
        };

        s.getCamera().add(new CameraMovement());
        s.add(new Tank(Vector2.zero, Vector2.zero));
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
        s.add(canvas);

        Rocket r = new Rocket("/missile.png");
        r.setPosition(new Vector2(-200,0));
        s.add(r);

        setSelectedScene(s);
        start();
    }
    static class Canvas extends JPanel{
        public Canvas(){
            setBackground(Color.gray);
            setLayout(new GridLayout());
            setSize(300,100);
       }
        static class ItemHolder extends JPanel{
            public Sprite s;
            boolean selected = false;
            public ItemHolder(){
                //s.animations = animations.animations;
                setBackground(Color.darkGray);
                setMaximumSize(new Dimension(50,50));
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(s != null){
                    s.render((Graphics2D) g);
                }
                if(selected && getBackground()!=Color.gray){
                    setBackground(Color.gray);
                }
                else if(!selected && getBackground()==Color.gray) {
                    setBackground(Color.darkGray);
                }
            }
        }
        static class HealthBar extends JPanel{
            int health = 100;
            JPanel bar = new JPanel();

            public HealthBar(){
                setLayout(null);
                bar.setSize(getSize().width*(health/100),getHeight());
                bar.setBackground(Color.red);
                add(bar);
            }

            @Override
            protected void paintComponent(Graphics g) {
                bar.setSize((int)((getSize().getWidth()-1)*health/100),getHeight()-1);
                super.paintComponent(g);
            }
        }
    }
}
