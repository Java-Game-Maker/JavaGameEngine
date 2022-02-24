package Testing;

import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Shape;
import JavaGameEngine.Components.Ui.Label;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Vector2;
import com.sun.corba.se.spi.orbutil.fsm.InputImpl;

import javax.swing.*;
import java.awt.*;

public class Main extends JavaGameEngine{

    public static void main(String[] args){

        init();

        ComponentHandler.addObject(new Player1());

        start();
    }

    static class Player1 extends GameObject{

        public Player1() {

            shape.setScale(getScale());
            addChild(shape);
            shape = new Shape();
            shape.setScale(new Vector2(200,200));
        }

        @Override
        public void draw(Graphics g) {
            super.draw(g);
            //g.drawPolygon(shape.getPolygon());
        }

        @Override
        public void update() {
            super.update();
            if(Input.isKeyDown(Keys.D)){
                setPosition(getPosition().add(Vector2.right));
            }
            if(Input.isKeyDown(Keys.A)){
                setPosition(getPosition().add(Vector2.left));
            }
            if(Input.isKeyDown(Keys.S)){
                setPosition(getPosition().add(Vector2.down));
            }
            if(Input.isKeyPressed(Keys.SPACE)){
                shape.rotate(45);
            }

            //shape.setPosition(getPosition());
        }
    }

}
