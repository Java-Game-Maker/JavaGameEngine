package javagameengine.ui;

import javagameengine.components.shapes.Rect;
import javagameengine.msc.Debug;
import javagameengine.msc.Padding;
import javagameengine.msc.Vector2;

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
        Debug.log("asdsad");
        setPadding(new Padding(10));
    }

    @Override
    public void mouseLeft() {
        super.mouseLeft();
        setPadding(new Padding(0));
    }

    @Override
    public void onClick() {
        super.onClick();
        Debug.log("clicked");
    }
}