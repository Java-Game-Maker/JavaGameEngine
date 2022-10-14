package testing;

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
        Player p = new Player();
        Scene scene1 = new Scene();
        Scene scene2 = new Scene(){
            @Override
            public void update() {
                super.update();
              /*  if(Input.isKeyDown(Keys.UPARROW)) getCamera().setScale(getCamera().getScale().add(getCamera().getScale().divide(100)));
                if(Input.isKeyDown(Keys.DOWNARROW)) getCamera().setScale(getCamera().getScale().subtract(getCamera().getScale().divide(100)));
                */

            if(Input.isKeyDown(Keys.ESCAPE)){
                Debug.log(fpsCap);
                if(Input.getScrollValue()<0) fpsCap+=10;
                if(Input.getScrollValue()>0) fpsCap-=10;
            }

            }
        };



        GameObject pl = new GameObject(new Rect(50,40));
        pl.add(new PhysicsBody());
        pl.add(new PlayerCharacter((PhysicsBody) pl.getChild(new PhysicsBody())));
        pl.add(new Collider(pl.getLocalVertices()));

        scene1.add(pl);
        scene1.getCamera().add(new CameraMovement());
        scene1.add(new GameObject(new Circle(100,100)){
            @Override
            public void start() {
                super.start();
                setPosition(new Vector2(200,0));
                add(new Collider(localVertices));
                add(new PhysicsBody());
            }
        });

        setSelectedScene(scene1);

        JavaGameEngine.size = new Vector2(1920/2,1080/2);

        start();


    }


}
