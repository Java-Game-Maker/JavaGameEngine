package Testing;

import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Backend.UpdateThread;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.Component;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Test extends JavaGameEngine {

    public static void main(String[] args){
        init();
        ComponentHandler.addObject(new Player());
        GameObject g = new GameObject();
        g.setScale(new Vector2(500,10));
        g.addChild(new SquareCollider());
        PhysicsBody b = new PhysicsBody();
        g.setPosition(new Vector2(0,500));
        b.setUseGravity(false);
        g.addChild(b);
        g.setLayer(0);
        ComponentHandler.addObject(g);
        start();
    }
    static class Player extends GameObject{

        public Player(){

            setColor(Color.CYAN);
            setLayer(10);
            setTag("player");
            PhysicsBody b = new PhysicsBody();
            setPosition(new Vector2(400,300));
            b.setUseGravity(true);
            addChild(b);
            SquareCollider s = new SquareCollider();
            s.setVisible(true);
            addChild(s);
        }

        @Override
        public void update() {
            super.update();
            if(Input.isKeyDown(Keys.D)){
                movePosition(getPosition().add(Vector2.right));
            }
            if(Input.isKeyDown(Keys.A)){
                movePosition(getPosition().add(Vector2.left));
            }
            if(Input.isKeyDown(Keys.W)){
                movePosition(getPosition().add(Vector2.up));
            }
            if(Input.isKeyDown(Keys.S)){
                movePosition(getPosition().add(Vector2.down));
            }
            //movePosition(Input.getMousePosition());
            UpdateThread.camera.setPosition(getPosition().subtract(new Vector2(Test.GAMEWORLD.getWidth()/2,Test.GAMEWORLD.getHeight()/2)));
        }

        @Override
        public void draw(Graphics g) {
            super.draw(g);
            g.drawString(UpdateThread.camera.getPosition().toString(),50,50);
            g.drawString(getPosition().toString(),50,100);
        }
    }

}
