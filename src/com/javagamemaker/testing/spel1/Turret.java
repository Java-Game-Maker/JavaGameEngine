package com.javagamemaker.testing.spel1;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;

public class Turret extends Stone{
    Sprite turret = new Sprite();

    public Turret(Vector2 pos){
        super(pos);
        turret.setScale(new Vector2(50,50));
        turret.setTimer(30f);
        setScale(new Vector2(60,60));
        Rectangle[] rectangles = new Rectangle[13];
        int i = 0;
        for(int y = 0;y < 4;y++){
            for(int x = 0;x < 4;x++) {
                Rectangle rectangle = new Rectangle(x*32,y*32,32,32);
                rectangles[i] = rectangle;

                i++;
                if(i == 13)
                    break;
            }
            if(i == 13)
                break;
        }
        turret.loadAnimation(rectangles,"/spel1/turret.png");
        add(turret);
    }
    @Override
    public void update() {
        super.update();
        float a =(float) getPosition().lookAtDouble(Main.ship.getPosition());
        turret.rotateTo(a+90,new Vector2(0,10));
    }

    @Override
    public void updateSecund() {
        super.updateSecund();
        if(getPosition().getDistance(Main.ship.getPosition())<Main.getWindowSize().getMagnitude()/2){
            Laser laser = new Laser(turret.getAngle(),getPosition().add(Vector2.getDirection(turret.getAngle()+90).multiply(-40)),null);
            laser.c.setTag("turret tag");
            JavaGameEngine.getSelectedScene().instantiate(laser);
        }

    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        turret.render(g);

    }
}
