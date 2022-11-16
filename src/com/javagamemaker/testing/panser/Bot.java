package com.javagamemaker.testing.panser;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Random;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;

public class Bot extends Tank{
    float time = new Random().nextFloat(2,5);
    float timeDir = new Random().nextFloat(0,5);

    int state = 0;// 0 idle , 1 = shoot
    public Bot(Vector2 pos) {
        super(pos,new Vector2(0,2));
        tag = "bot";
        Collider stateChecker = new Collider();
        stateChecker.setLocalVertices(new Rect(300,300));
        stateChecker.addIgnoreTag("bot");
        stateChecker.setTrigger(true);
       // add(stateChecker);

        int [] angles = new int[]{0,90,-90,180,-180};
        angle = angles[new Random().nextInt(5)];
    }

    @Override
    protected void onTriggerEnter(CollisionEvent collisionEvent) {
        if(collisionEvent.getCollider1().getFirstParent().getTag() != "bot")
            super.onTriggerEnter(collisionEvent);
    }

    @Override
    public void updateSecond() {
        super.updateSecond();
        if(time < 0){
            //JavaGameEngine.getSelectedScene().instantiate(new Bullet(getPosition(),head.getAngle(),getChild(new Collider()).getTag()));
            time = new Random().nextFloat(2,5);
        }
        time--;
        if(timeDir < 0){
            int [] angles = new int[]{0,90,-90,180,-180};
            angle = angles[new Random().nextInt(5)];
            timeDir = new Random().nextFloat(1,6);
        }
        timeDir--;
    }
    @Override
    public void doAction() {
        if( getPosition().getX() < JavaGameEngine.getWindowSize().getX() && getPosition().getY() < JavaGameEngine.getWindowSize().getY() &&
            getPosition().getX() > -JavaGameEngine.getWindowSize().getX() && getPosition().getY() > -JavaGameEngine.getWindowSize().getY()
        ){
            setPosition(getPosition().add(Vector2.getDirection(angle-90)));
        }

        float value = (getPosition().lookAt(Main.tank.getPosition())-90-head.getAngle())+ new Random().nextFloat(0,10   );
        head.rotate(value/50,new Vector2(0,-10));
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.drawOval((int) JavaGameEngine.getWindowSize().getY(), (int) JavaGameEngine.getWindowSize().getY(),10,10);
    }
}
