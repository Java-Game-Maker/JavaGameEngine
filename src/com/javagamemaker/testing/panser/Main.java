package com.javagamemaker.testing.panser;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.CameraMovement;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

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
                    Debug.log(getComponents1().size());
                    instantiate(b);
                }
                super.update();
            }
        };
        s.getCamera().add(new CameraMovement());
        s.add(tank);
        for(int in = 0;in < 5; in++){
            s.add(new Bot(new Vector2(200,(in*110)-400)));
        }

        setSelectedScene(s);
        start();
    }

}
