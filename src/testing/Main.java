package testing;

import javagameengine.CollisionEvent;
import javagameengine.JavaGameEngine;
import javagameengine.Scene;
import javagameengine.components.*;
import javagameengine.components.Component;
import javagameengine.components.shapes.Circle;
import javagameengine.components.shapes.Rect;
import javagameengine.input.Input;
import javagameengine.input.Keys;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.*;

public class Main extends JavaGameEngine {

    public static void main(String[] args){

        Scene scene1 = new Scene();
        scene1.getCamera().add(new CameraMovement());

        GameObject o = new GameObject();


        GameObject floor = new GameObject(new Rect(500,100));
        floor.rotateTo(20,Vector2.zero);
        floor.add(new Collider(floor.getLocalVertices()));


        GameObject floor1 = new GameObject(new Rect(500,100));
        floor1.setPosition(new Vector2(500,0));
        floor1.add(new Collider(floor1.getLocalVertices()));
        scene1.add(floor1);

        GameObject p = new GameObject(new Rect(50,50)){
            @Override
            public void update() {
                super.update();
                PhysicsBody b = ((PhysicsBody)getChild(new PhysicsBody()));
                if(Input.isKeyDown(Keys.D))
                    //setPosition(getPosition().add(Vector2.right));
                    b.addForce(Vector2.right.multiply(0.1f));
                if(Input.isKeyDown(Keys.A))
                    //dd a  setPosition(getPosition().add(Vector2.left));
                    b.addForce(Vector2.left.multiply(0.1f));
                if(Input.isKeyPressed(Keys.SPACE)){
                    b.addForce(Vector2.up.multiply(20));
                }
            }

            @Override
            public void render(Graphics2D g) {
                super.render(g);
                g.drawString(((PhysicsBody )getChild(new PhysicsBody())).velocity.toString(),100,100);
                g.drawString(String.valueOf(((PhysicsBody)getChild(new PhysicsBody())).getRotationalForce()),100,120);
            }
        };
        p.setPosition(new Vector2(0,-200));
        p.add(new PhysicsBody(true));
        p.add(new Collider(p.getLocalVertices()));




        scene1.add(p);
        scene1.add(floor);

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
