package javagameengine.ui;

import Testing.Main;
import javagameengine.JavaGameEngine;
import javagameengine.backend.input.Input;
import javagameengine.backend.input.Keys;
import javagameengine.components.Component;
import javagameengine.components.GameObject;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import static javagameengine.backend.input.Input.getMouseWorldPosition;

public class Panel extends GameObject {
    /*
        The idea is to
        Layouts

     */
    private int spacing = 10;

    private boolean fixed = true;
    private Vector2 padding = new Vector2(5,5);
    private Vector2 scale = new Vector2(0,0);

    private Layout layout = new HorizontalLayout();
    public Panel() {
        setColor(Color.gray);
        setLayer(10);
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public Vector2 getPadding() {
        return padding;
    }

    public void setPadding(Vector2 padding) {
        this.padding = padding;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }


    public void setLayout(Layout layout){
        layout.parent = this;
        this.layout = layout;
    }
    public Layout getLayout(){
        return this.layout;
    }

    @Override
    public Vector2 getSpritePosition(){
        float x;
        float y;
        if(fixed){
            x = (getPosition().getX()-((getScale().getX()/2)));
            y = (getPosition().getY()-((getScale().getY()/2)));
        }else{
            x = (getPosition().subtract(Main.getScene().getCamera().getPosition()).getX()-((getScale().getX()/2)));
            y = (getPosition().subtract(Main.getScene().getCamera().getPosition()).getY()-((getScale().getY()/2)));
        }

        return new Vector2(x,y);
    }
    /**
     * This will only be set if the component isnt a child. This is because if the component
     * is a child it will set its positon to its parent + its local piston
     * @param position vector2 positon
     */
    public void setPosition(Vector2 position) {
        // position = new Vector2(position.getX(),-position.getY());
        if(isParent() && !fixed){

            if(position!=null){

                this.position = position.add(JavaGameEngine.origin.subtract(localOrigin));
            }
            else{
                this.position = position.add(JavaGameEngine.origin);
            }
        }
        else{
            this.position = position;
        }
        //  updateChildren();
    }
    @Override
    public void updateChildren(){

        for (Component component : getChildren()) {
            component.update();
            try {
                ((UiElement)component).setFixed(fixed);
            }catch (Exception e){}
            try {
                ((Panel)component).setFixed(fixed);
            }catch (Exception e){}

        }
    }
    /***
     * this is the update function. It will be called on every game update
     * it updates all the children.
     */
    public void update() {
        //Updating relative to the middle
        if(!JavaGameEngine.origin.equals(localOrigin)){
            setPosition(getPosition());
            localOrigin = JavaGameEngine.origin;
        }
        if (insideComp() && isEnabled()) {
            if (!isMouseInside()) {
                onMouseEntered();
                setMouseInside(true);
            }

        } else if (isMouseInside() && isEnabled()) {
            onMouseExit();
            setMouseInside(false);
        }
        if (isMouseInside() && Input.isMousePressed() && isEnabled()) {
            onMousePressed();
            if (getParent() != null) getParent().onMousePressed();
        }

        if(getParent()!=null && !fixed) {
            float x = (getParent().getPosition().getX())-scale.getX()/2;
            float y = (getParent().getPosition().getY())-scale.getY()/2;

            setPosition(new Vector2(x,y).add(getLocalPosition())); // we get the parents position and we add our localPosition
            // update this in the setScale and setRotation instead
            setRotation(getParent().getRotation().add(getLocalRotation()));
            setScale(getParent().getScale().add(getLocalScale()));

        }
        if(getChildren().size()>0){
            updateChildren(); // updates all the children
        }
        //mouse enter and exit

    }
    @Override
    public Vector2 getScale() {
        return layout.getScale();
    }

    @Override
    public void draw(Graphics g) {
        setChildren(layout.sort());
       super.draw(g);

    }
}
