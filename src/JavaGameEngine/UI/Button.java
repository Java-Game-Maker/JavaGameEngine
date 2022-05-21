package JavaGameEngine.UI;

import JavaGameEngine.Components.GameObject;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Button extends GameObject {

    String text = "Press me";

    public Button(String text){
        this.text = text;
        init();
    }
    public Button(){init();}

    @Override
    public void onMouseEntered() {
        super.onMouseEntered();
        setLocalScale(getLocalScale().multiply(1.1f));
    }
    @Override
    public void onMouseExit() {
        super.onMouseEntered();
        setLocalScale(getLocalScale().devide(1.1f));
    }
    public void init(){
        setLocalScale(new Vector2(0,-50));
    }

    @Override
    public void onMousePressed() {
        super.onMousePressed();
        onPress();
    }

    public void onPress(){

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.white);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("minectaft",0,30));

        //g2.drawString("This is gona be awesome",70,20);
        g2.drawString(this.text, (int) (getPosition().getX()-(g2.getFont().getSize())), (int) getPosition().getY());
    }
}
