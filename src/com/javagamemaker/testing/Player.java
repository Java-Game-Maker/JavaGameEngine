package com.javagamemaker.testing;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.GameObject;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.components.shapes.Circle;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;

public class Player extends GameObject {

    PhysicsBody body = new PhysicsBody();

    public Player() {
        super();
        /*

        localVertices.clear();
        localVertices.add(new Vector2(-50,-20)); // top left
        localVertices.add(new Vector2(-50, 20)); // bottom left
        localVertices.add(new Vector2( 50, 20)); // bottom right
        localVertices.add(new Vector2(  70,0)); // top right

        localVertices.add(new Vector2( 50,-20)); // top right

        updateVertices();
*/
        localVertices = new Circle(10,8);
        //setPosition(new Vector2(100,200));

        Collider col = new Collider(localVertices);

        col.setVertices(vertices);
        body.mass = 200;
        add(col);
        add(body);
        GameObject g = new GameObject();
        g.add(new Collider());
        g.add(new PhysicsBody());
        g.setParentOffset(new Vector2(200,0));
        
        //add(g);

    }
    @Override
    public void update() {
        super.update();

      //  setPosition(Input.getMousePosition());
        if(Input.isKeyDown(Keys.A)){
            rotate(-1);            //body.setRotationalPoint(new Vector2(0,0));

        }
        if(Input.isKeyDown(Keys.D)){
            //body.setRotationalForce(0.9f);
            rotate(1);
         //   body.setRotationalPoint(new Vector2(0,0));
        }

        if(Input.isKeyDown(Keys.SPACE)){
            body.addForce(Vector2.getDirection(angle).multiply(5*2));

        }
        if(Input.isKeyDown(Keys.SHIFT)){
            body.addForce(Vector2.getDirection(angle).multiply(-5*2));
        }

        JavaGameEngine.getSelectedScene().getCamera().setPosition(getPosition().multiply(-1).add(JavaGameEngine.getWindowSize().divide(2)));

    }
    @Override
    public void render(Graphics2D g) {
        super.render(g);

    }
}
