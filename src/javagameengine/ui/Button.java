package javagameengine.ui;

import javagameengine.components.shapes.Rect;
import javagameengine.msc.Debug;
import javagameengine.msc.Padding;
import javagameengine.msc.Vector2;
import testing.Main;

import java.awt.*;

public class Button extends UiFillElement{

    Text text = new Text("");
    public Button(String text){
        this.localVertices = new Rect(100,50);
        updateVertices();
        //setScale(new Vector2(100,50));
        this.text = new Text(text);
        add(this.text);
    }

    @Override
    public void mouseEntered() {
        super.mouseEntered();
        setScale(getScale().add(10));
    }

    @Override
    public void mouseLeft() {
        super.mouseLeft();
        setScale(getScale().subtract(10));
    }

    @Override
    public void update() {
        super.update();
        try{
          //  Debug.log(Main.getSelectedScene().hasA.toString());
        }catch (Exception e){}

    }

    @Override
    public void onClick() {
        super.onClick();
    }
}