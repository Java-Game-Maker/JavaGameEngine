package com.javagamemaker.testing.spel1;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.CameraMovement;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Random;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;

/*

You are a ship in space that can move around in a infinite space where you can find
valuable rocks but also dangerous rock turrets that protects the rocks


make so when a stone is a long way from the player set its pos closer (instead of adding and removing all the time)
 */


public class Main extends JavaGameEngine {
    public static Ship ship = new Ship();

    public static Sprite animations = new Sprite();

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
        animations.loadAnimation(rectangles,"/spel1/explosion.png");

        Main.size = new Vector2(1920/2,1080/2);

        Scene s = new Scene(){
            @Override
            public void start() {
                super.start();
                Vector2 maxBox = ship.getPosition().add(JavaGameEngine.getWindowSize().multiply(5));
                Vector2 minBox = ship.getPosition().subtract(JavaGameEngine.getWindowSize().multiply(5));
                Random r = new Random();

                for(int i = 0;i<30;i++){
                    float x = r.nextFloat(minBox.getX(),maxBox.getX());
                    float y = r.nextFloat(minBox.getY(),maxBox.getY());
                    int d = (int)(r.nextFloat(0,5));
                    // Debug.log(d);
                    if(d>0){
                        Stone stone = new Stone(new Vector2(x,y));
                        // Turret stone = new Turret(new Vector2(x,y));
                        JavaGameEngine.getSelectedScene().instantiate(stone);

                    }
                    else{
                        Turret stone = new Turret(new Vector2(x,y));
                        JavaGameEngine.getSelectedScene().instantiate(stone);
                    }
                }
            }
        };
        s.setBackground(new Color(0,5,20));
        //JavaGameEngine.fpsCap = 10;
        s.getCamera().add(new CameraMovement());
        //s.add(new Turret(new Vector2(200,0)));
        Debug.showWhere = true;


        s.add(new Pause());

        setSelectedScene(s);
        start();

    }

    public static class Pause extends Component{

        public Pause(){
            layer = 100;
        }
        String points = "";
        public Pause(String points){
            layer = 100;
            this.points = points;
        }
        float time = 0;
        @Override
        public void start() {
            super.start();
            time = 0;
        }

        @Override
        public void update() {
            super.update();
            if(Input.isKeyDown(Keys.SPACE)&&time>1){
                Main.ship = new Ship();
                Main.getSelectedScene().instantiate(ship);
                destroy();
            }
        }

        @Override
        public void updateSecund() {
            super.updateSecund();
            time++;
        }

        @Override
        public void render(Graphics2D g) {
            super.render(g);
            Vector2 pos = getPosition();
            Color c = g.getColor();
            g.setColor(Color.white);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.drawString("Spacer", pos.getX()-60,pos.getY()-20);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
            g.drawString(points!=""?"You got "+points:"",pos.getX()+-60,pos.getY()+0);
            g.drawString("Press space to start",pos.getX()-70,pos.getY()+20);
            g.setColor(c);
        }
    }

}
