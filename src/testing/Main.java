package testing;


import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.Scene;
import com.javagamemaker.javagameengine.components.CameraMovement;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.msc.Vector2;
import com.javagamemaker.javagameengine.components.Animation.Animation;
import com.javagamemaker.javagameengine.components.Animation.AnimationPoint;

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

    static class Coin extends GameObject {
        Animation a = new Animation();
        public Coin(){
            LinkedList<AnimationPoint> points = new LinkedList<>();
            points.add(new AnimationPoint(0,-45,0));
            points.add(new AnimationPoint(0,45,5));
            points.add(new AnimationPoint(0,-45,10));


            a.setSelectedPoints(points);

            setScale(new Vector2(50,100));
        }
        int index = 0;
        @Override
        public void update() {
            super.update();
            Vector2 pos = a.getPoint();

           // setPosition(pos);
            rotateTo(pos.getY(), new Vector2(0,-50));
            updateVertices();

        }

    }

}
