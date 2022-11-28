package com.javagamemaker.testing.spacer2;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Random;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;

public class Stone extends GameObject {

    float health = 100;

    public Stone(Vector2 pos){
        Random r = new Random();
        float s = r.nextFloat(10,400);
        health = s;
        setScale(new Vector2(s,s));
        //loadAnimation(new Rectangle[]{new Rectangle(0,0,45,45)}, "/spel1/stone.png");
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
    public void updateSecond() {
        super.updateSecond();
        if(getPosition().getDistance(Main.player.getPosition())>1000){
            Vector2 maxBox = Main.player.getPosition().add(JavaGameEngine.getWindowSize().multiply(3));
            Vector2 minBox = Main.player.getPosition().subtract(JavaGameEngine.getWindowSize().multiply(3));
            Random rr = new Random();
            float xp = rr.nextFloat(minBox.getX(),maxBox.getX());
            float yp = rr.nextFloat(minBox.getY(),maxBox.getY());
            ((PhysicsBody)getChild(new PhysicsBody())).velocity = getPosition().lookAt(Main.player.getPosition()).multiply(2);
            setPosition(new Vector2(xp,yp));
        }
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString(String.valueOf(health),getPosition().getX(),getPosition().getY()-50);
        g.setColor(c);

    }
}
