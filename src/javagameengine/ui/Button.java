package javagameengine.ui;

import javagameengine.components.GameObject;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Button extends UiElement{

    public Button() {
        String string = "Click me";
        Text text = new Text(string);
        text.setColor(Color.BLACK);
        text.setLocalPosition(new Vector2(scale.getX()/2-(string.length()*4),scale.getY()/2-10));

        Background b = new Background();
        b.setColor(Color.lightGray);
        addChild(b);
        addChild(text);

    }


    @Override
    public void draw(Graphics g) {

        drawChildren(g);
    }

    class Background extends UiElement {
        public Background() {
            setLayer(100);
        }

        @Override
        public void onMouseEntered() {
            super.onMouseEntered();
            setScale(getScale().add(4));
            setLocalPosition(getLocalPosition().add(2));
            Debug.log(String.valueOf(isEnabled()));

        }
        @Override
        public void onMouseExit() {
            super.onMouseExit();
            setScale(getScale().subtract(4));
            setLocalPosition(getLocalPosition().subtract(2));
        }

        @Override
        public void onMousePressed() {
            Debug.log("pressed motherfucker");
        }

        @Override
        public void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(getColor());
            Graphics2D graphics2 = (Graphics2D) g;
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(getSpritePosition().getX(), getSpritePosition().getY(), getSpriteScale().getX(), getSpriteScale().getY(), 10, 10);
            graphics2.fill(roundedRectangle);
            g.setColor(c);
        }
    }


}
