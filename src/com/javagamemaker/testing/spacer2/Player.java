package com.javagamemaker.testing.spacer2;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.*;
import com.javagamemaker.javagameengine.components.shapes.Circle;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;

public class Player extends Sprite {
    PhysicsBody b = new PhysicsBody(false);
    Sprite thruster = new Sprite();
    int points = 0;
    int health = 100;
    Sprite sprite = new Sprite();

    Laser selectedAmmo = new Rocket();
    public Player(){

        sprite.loadAnimation(new String[]{"/star.jpg"});

        sprite.setScale(new Vector2(3000,2000));
        sprite.setFreezeRotation(true);
        add(sprite);

        thruster.loadAnimation(new Rectangle[]{new Rectangle(0,0,32,32),new Rectangle(32,0,32,32),new Rectangle(0,32,32,32)}, "/spel1/thruster.png");
        thruster.setScale(new Vector2(30,30));
        thruster.setParentOffset(new Vector2(0,29));
        thruster.setVisible(false);

        add(thruster);

        setScale(new Vector2(50,50));
        loadAnimation(new Rectangle[]{new Rectangle(117,120,50,30)}, "/spel1/spacegame.png");

        Collider collider = new Collider(new Rect(50,50));
        collider.setTrigger(true);
        collider.setVisible(false);
        collider.addIgnoreTag("laser");

        add(collider);

        localVertices = new Circle(10,10);
        updateVertices();
        add(b);
        layer = 10;
    }
    private void shoot(){
        JavaGameEngine.getSelectedScene().instantiate(new Rocket(angle,getPosition().add(Vector2.getDirection(angle).multiply(-20))));
        JavaGameEngine.getSelectedScene().instantiate(new Laser(angle,getPosition().add(Vector2.getDirection(angle).multiply(20))));
    }
    float time = 1;
    @Override
    public void updateSecond() {
    }
    @Override
    public void updateMili() {
        if(Input.isKeyDown(Keys.SPACE) && time > selectedAmmo.reloadTime){
            shoot();
            time = 0;
        }
        time+=1;
    }
    @Override
    public void update() {
        super.update();

        if(Input.isKeyDown(Keys.A)){
            getFirstParent().rotate(-2);            //body.setRotationalPoint(new Vector2(0,0));

        }
        if(Input.isKeyDown(Keys.D)){
            //body.setRotationalForce(0.9f);
            getFirstParent().rotate(2);
            //   body.setRotationalPoint(new Vector2(0,0));
        }
        if(Input.isKeyDown(Keys.W) && b.velocity.getMagnitude() < 5){
            thruster.setVisible(true);
            b.addForce(Vector2.getDirection(angle-90).multiply(0.24));
        }
        else{
            thruster.setVisible(false);
        }
        if(Input.isKeyDown(Keys.S) && b.velocity.getMagnitude() < 5){
            b.addForce(Vector2.getDirection(angle-90).multiply(-0.1));
        }

        JavaGameEngine.getSelectedScene().getCamera().setPosition(getFirstParent().getPosition().multiply(-1).add(JavaGameEngine.getWindowSize().divide(2)));
    }
    @Override
    protected void onTriggerEnter(CollisionEvent collisionEvent) {
        super.onTriggerEnter(collisionEvent);
       // health-=10*time;
       // if(health<=0)
       // {
       //     Main.getSelectedScene().getCamera().start();
       //     destroy();
       // }
    }

    @Override
    public void setPosition(Vector2 position) {
        super.setPosition(position);
        sprite.setParentOffset(getPosition().multiply(-0.05f));
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        g.drawString(String.valueOf(health)+" [+]",getPosition().getX()+Main.getWindowSize().getX()/3,getPosition().getY()+Main.getWindowSize().getY()/3);
        g.drawString(String.valueOf(Main.fps)+" fps",getPosition().getX()+Main.getWindowSize().getX()/3,getPosition().getY()+Main.getWindowSize().getY()/3+40);
        g.drawString(String.valueOf(points)+ " P",getPosition().getX()+Main.getWindowSize().getX()/3,getPosition().getY()+Main.getWindowSize().getY()/3+20);

    }
}