package testing;

import javagameengine.CollisionEvent;
import javagameengine.JavaGameEngine;
import javagameengine.Scene;
import javagameengine.components.*;
import javagameengine.components.Component;
import javagameengine.components.shapes.Circle;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.*;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene();

        GameObject o = new GameObject();

        scene1.add(new PickUp(new Vector2(200,100)));
        scene1.add(new Player());

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();
    }

    static class Player extends GameObject {
        PhysicsBody b = new PhysicsBody();
        @Override
        public void start() {
            super.start();
            b.mass = 100;
            tag = "player";
            add(b);
            Collider c = new Collider();
            c.setTrigger(true);
            add(c);
            add(new PlayerCharacter(b));
        }

        @Override
        public void update() {
            super.update();
            updateVertices();
        }
    }

    static class PickUp extends GameObject{

        public PickUp(Vector2 pos){
            setPosition(pos);
            updateVertices();
        }
        Collider small;
        @Override
        public void start() {
            super.start();
            setScale(new Vector2(10,10));

            Collider big = new Collider(new Circle(100,100));
            big.setTrigger(true);
            big.setVisible(false);

            small = new Collider(getLocalVertices());
            small.setTrigger(true);
            add(big);
            add(small);
            add(new PhysicsBody());
        }
        Component moveTo = null;
        @Override
        protected void onTriggerEnter(CollisionEvent collisionEvent) {
            super.onTriggerEnter(collisionEvent);
            Component p = collisionEvent.getCollider2().getParent();
            if(p.getTag() == "player")
                moveTo = p;

            if(collisionEvent.getCollider1() == small){
                destroy();
            }

        }

        @Override
        public void update() {
            super.update();
            if(moveTo!=null){
                Debug.log(getPosition().lookAt(moveTo.getPosition()));
                setPosition(getPosition().add(Vector2.getDirection(getPosition().lookAt(moveTo.getPosition()))));
                moveTo = null;
            }
        }
    }



}
