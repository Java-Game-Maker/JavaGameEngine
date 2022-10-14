package testing.car;

import javagameengine.JavaGameEngine;
import javagameengine.Scene;
import javagameengine.components.CameraMovement;
import javagameengine.components.Component;
import javagameengine.components.GameObject;
import javagameengine.components.Sprite;
import javagameengine.components.shapes.Rect;
import javagameengine.input.Input;
import javagameengine.input.Keys;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.awt.image.Kernel;

public class Main extends JavaGameEngine {

    public static void main(String[] args){
        Scene scene = new Scene();
        scene.add(new Car());
        scene.getCamera().add(new CameraMovement());
        setSelectedScene(scene);
        start();

    }

    static class Car extends Component {

        float gas = 1f;
        Sprite sprite = new Sprite();
        Pipe p = new Pipe();

        public Car() {
            setLocalVertices(new Rect(25,20));
            sprite.loadAnimation(new String[]{"/car.png"});
            sprite.setParentOffset(new Vector2(0,0));
            sprite.setScale(new Vector2(100,50));
            add(sprite);
           // p.setColor(Color.green);
            p.setParentOffset(new Vector2(-50,0));
            add(p);


        }

        float time = 0;
        @Override
        public void update() {
            super.update();
            if(time%50==0){
                JavaGameEngine.getSelectedScene().instantiate(new Smoke(p.getBodyPosition()));
            }
            if(Input.isKeyDown(Keys.A))
            {
                rotate(-1*gas);
            } if(Input.isKeyDown(Keys.D))
            {
                rotate(1*gas);
            }

            if(Input.isKeyDown(Keys.W))
            {
                gas+=0.1f;
            } if(Input.isKeyDown(Keys.S))
            {
                gas-=0.1f;
            }
            setPosition(getPosition().add(Vector2.getDirection(angle).multiply(gas)));
         time++;
        }


    }

    static class Pipe extends GameObject{
        public Pipe(){
            localVertices = new Rect(10,10);

        }
    }

    static class Smoke extends Sprite{

        public Smoke(Vector2 pos){
            setPosition(pos);
            loadAnimation(new String[]{"/smoke.png"});
            setScale(new Vector2(40,40));
        }
        float time = 0;
        @Override
        public void update() {
           // super.update();
            if(scale.getX() > 0 && time%50==0)
                setScale(getScale().subtract(2));
            else{

            }
            time++;
        }
    }

}
