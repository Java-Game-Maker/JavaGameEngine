package testing.sin;

import javagameengine.JavaGameEngine;
import javagameengine.Scene;
import javagameengine.components.CameraMovement;
import javagameengine.components.Component;
import javagameengine.components.GameObject;
import javagameengine.input.Input;
import javagameengine.input.Keys;
import javagameengine.msc.Vector2;

import java.awt.*;

public class Main extends JavaGameEngine {

    public static void main(String[] args){
        Scene s = new Scene();
        s.getCamera().setPosition(new Vector2(0,200));
        Component renderer = new Component(){

            float magnitude = 50;
            float periods = 0.1f;

            @Override
            public void update() {
                super.update();
                if(Input.isKeyDown(Keys.UPARROW)) magnitude++;
                if(Input.isKeyDown(Keys.DOWNARROW)) magnitude--;
                if(Input.isKeyDown(Keys.RIGHTARROW)) periods+=0.1f;
                if(Input.isKeyDown(Keys.LEFTARROW)) periods-=0.1f;
            }

            @Override
            public void render(Graphics2D g) {
                super.render(g);
                for(int i = -300;i<300;i++){

                    int y = (int) ((int) magnitude*Math.sin(Math.toRadians(i)*periods));
                    int y2 = (int) ((int) magnitude*Math.cos(Math.toRadians(i)*periods));
                    g.fillOval(y2,y,10,10);
                    g.fillOval(i,y2,10,10);

                }
            }
        };
        s.add(renderer);
        s.getCamera().add(new CameraMovement());

        setSelectedScene(s);
        start();

    }


}
