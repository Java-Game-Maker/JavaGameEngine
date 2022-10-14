package javagameengine.ui;

import javagameengine.components.Component;
import javagameengine.components.GameObject;
import javagameengine.components.shapes.Circle;
import javagameengine.components.shapes.Rect;
import javagameengine.input.Input;
import javagameengine.input.Keys;
import javagameengine.msc.Debug;
import javagameengine.msc.Padding;

import java.awt.*;

public class UiElement extends Component {

    private Color color = Color.darkGray;
    private Padding padding = new Padding();
    public UiElement(){}

    @Override
    public void mouseEntered() {
        super.mouseEntered();
    }

    @Override
    public void mouseLeft() {
        super.mouseLeft();
    }
    public void onClick(){

    }
    @Override
    public void update() {
        super.update();
        if(Input.isMouseDown(Keys.LEFTCLICK) && mouseInside){
            onClick();
        }
    }

    public Padding getPadding() {
        return padding;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}