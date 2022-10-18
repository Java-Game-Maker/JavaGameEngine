package testing;


import javagameengine.JavaGameEngine;
import javagameengine.Scene;
import javagameengine.components.*;
import javagameengine.components.Animation.Animation;
import javagameengine.components.Animation.AnimationPoint;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.util.LinkedList;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene();
        scene1.getCamera().add(new CameraMovement());

        scene1.add(new Coin());

        setSelectedScene(scene1);
        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();
    }

    static class Coin extends GameObject{
        Animation a = new Animation();
        public Coin(){

            LinkedList<AnimationPoint> points = new LinkedList<>();
            points.add(new AnimationPoint(0,0,0));
            points.add(new AnimationPoint(0,100,5));
            points.add(new AnimationPoint(0,50,15));
            points.add(new AnimationPoint(0,25,25));
            points.add(new AnimationPoint(0,0,32));


            a.setSelectedPoints(points);

            setScale(new Vector2(10,10));

        }
        int index = 0;
        @Override
        public void update() {
            super.update();
            Vector2 pos = a.getPoint();
            Debug.log(pos);
            Debug.log(a.getPointIndex());
            setPosition(pos);
           // setScale(pos.add(10));


        }
    }

}
