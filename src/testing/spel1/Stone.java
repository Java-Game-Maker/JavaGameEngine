package testing.spel1;

import javagameengine.CollisionEvent;
import javagameengine.JavaGameEngine;
import javagameengine.components.Collider;
import javagameengine.components.PhysicsBody;
import javagameengine.components.Sprite;
import javagameengine.components.shapes.Circle;
import javagameengine.components.shapes.Rect;
import javagameengine.msc.Debug;
import javagameengine.msc.Random;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.util.concurrent.ExecutionException;

public class Stone extends Sprite {

    float health = 100;

    public Stone(Vector2 pos){
        Random r = new Random();
        float s = r.nextFloat(10,400);
        health = s;
        setScale(new Vector2(s,s));
        loadAnimation(new Rectangle[]{new Rectangle(0,0,45,45)},"/spel1/stone.png");
        Collider c = new Collider(false);
        c.setTrigger(true);
        c.setTag("Stone");
        c.addIgnoreTag("Stone");
        c.addIgnoreTag("turret laser");
        c.setLocalVertices(new Rect(scale));
        add(c);
        PhysicsBody b = new PhysicsBody();
        add(b);
        b.velocity = new Vector2(r.nextFloat(0,2),r.nextFloat(0,2));
        b.setFriction(0);
        b.setFreeze(true);
        setPosition(pos);
        layer=20;
    }

    @Override
    public void updateSecund() {
        super.updateSecund();
        if(getPosition().getDistance(Main.ship.getPosition())>1000){
            Vector2 maxBox = Main.ship.getPosition().add(JavaGameEngine.getWindowSize().multiply(3));
            Vector2 minBox = Main.ship.getPosition().subtract(JavaGameEngine.getWindowSize().multiply(3));
            Random rr = new Random();
            float xp = rr.nextFloat(minBox.getX(),maxBox.getX());
            float yp = rr.nextFloat(minBox.getY(),maxBox.getY());
            setPosition(new Vector2(xp,yp));
        }
    }

    @Override
    public void onTriggerEnter(CollisionEvent event) {
        super.onTriggerEnter(event);
        health -= 10;
        if(health<=0){
            try{
                if(((Laser)event.getCollider1().getFirstParent()).player != null){
                    Main.ship.points ++;

                }
            }catch (Exception e){}

            JavaGameEngine.getSelectedScene().instantiate(new Explosion(getPosition(),Main.animations.animations));

            Vector2 maxBox = Main.ship.getPosition().add(JavaGameEngine.getWindowSize().multiply(5));
            Vector2 minBox = Main.ship.getPosition().subtract(JavaGameEngine.getWindowSize().multiply(5));
            Random rr = new Random();
            float xp = rr.nextFloat(minBox.getX(),maxBox.getX());
            float yp = rr.nextFloat(minBox.getY(),maxBox.getY());
            setPosition(new Vector2(xp,yp));

        }
    }

    Vector2 p = Vector2.zero;

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString(String.valueOf(health),getPosition().getX(),getPosition().getY()-50);
        g.setColor(c);

    }
}