package com.javagamemaker.testing.spel1;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.*;
import com.javagamemaker.javagameengine.components.shapes.Circle;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Random;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.util.LinkedList;

public class Ship extends Sprite {
    PhysicsBody b = new PhysicsBody(false);
    Sprite thruster = new Sprite();
    int points = 0;
    int health = 100;

    public Ship(){

        thruster.loadAnimation(new Rectangle[]{new Rectangle(0,0,32,32),new Rectangle(32,0,32,32),new Rectangle(0,32,32,32)},"/spel1/thruster.png");
        thruster.setScale(new Vector2(30,30));
        thruster.setParentOffset(new Vector2(0,29));
        thruster.setVisible(false);

        add(thruster);

        setScale(new Vector2(50,50));
        loadAnimation(new Rectangle[]{new Rectangle(117,120,50,30)},"/spel1/spacegame.png");

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
    @Override
    public void start() {
        super.start();
        time = 0;
        health = 100;
        Vector2 maxBox = getPosition().add(JavaGameEngine.getWindowSize().multiply(1));
        Vector2 minBox = getPosition().subtract(JavaGameEngine.getWindowSize().multiply(1));
        Random r = new Random();
        for(int i = 0;i<1000;i++){
            float x = r.nextFloat(minBox.getX(),maxBox.getX());
            float y = r.nextFloat(minBox.getY(),maxBox.getY());
            stars.add(new Vector2(x,y));
        }
    }

    private void shoot(){
        JavaGameEngine.getSelectedScene().instantiate(new Laser(angle,getPosition().add(Vector2.getDirection(angle).multiply(-20)),this));
        JavaGameEngine.getSelectedScene().instantiate(new Laser(angle,getPosition().add(Vector2.getDirection(angle).multiply(20)),this));
    }
    LinkedList<Stone> oldStones = new LinkedList<>();
    LinkedList<Vector2> stars = new LinkedList<>();
    public void spawnStones(){

        stars.clear();
    }
    Vector2 mos = getPosition();
    float time = 1;
    @Override
    public void updateSecund() {
        time+=0.1;
    }
    @Override
    public void updateMili() {
        if(getPosition().getDistance(mos)>JavaGameEngine.getWindowSize().getMagnitude() / 4){
            //spawnStones();
            mos = getPosition();
            stars.clear();
            Vector2 maxBox = getPosition().add(JavaGameEngine.getWindowSize().multiply(2));
            Vector2 minBox = getPosition().subtract(JavaGameEngine.getWindowSize().multiply(2));
            Random r = new Random();
            for(int i = 0;i<1000;i++){
                float x = r.nextFloat(minBox.getX(),maxBox.getX());
                float y = r.nextFloat(minBox.getY(),maxBox.getY());
                stars.add(new Vector2(x,y));
            }
        }

        if(Input.isKeyDown(Keys.SPACE)){
            shoot();
        }
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
        health-=10*time;
        if(health<=0)
        {
            destroy();
            Main.getSelectedScene().getCamera().start();
            Main.getSelectedScene().instantiate(new Main.Pause(String.valueOf(points)));
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void render(Graphics2D g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        for(Vector2 star : stars){
            g.fillOval((int) star.getX(), (int) star.getY(),5,5);
        }
        g.setColor(c);
        super.render(g);
        c = g.getColor();
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        g.drawString(String.valueOf(health)+" [+]",getPosition().getX()+Main.getWindowSize().getX()/3,getPosition().getY()+Main.getWindowSize().getY()/3);
        g.drawString(String.valueOf(Main.fps)+" fps",getPosition().getX()+Main.getWindowSize().getX()/3,getPosition().getY()+Main.getWindowSize().getY()/3+40);
        g.drawString(String.valueOf(points)+ " P",getPosition().getX()+Main.getWindowSize().getX()/3,getPosition().getY()+Main.getWindowSize().getY()/3+20);
        g.setColor(c);

    }
}
