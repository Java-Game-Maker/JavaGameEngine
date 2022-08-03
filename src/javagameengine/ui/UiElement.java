package javagameengine.ui;

import Testing.Main;
import javagameengine.JavaGameEngine;
import javagameengine.backend.GameWorld;
import javagameengine.backend.input.Input;
import javagameengine.components.Component;
import javagameengine.components.GameObject;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.*;

public class UiElement extends GameObject {

    private boolean fixed = true;

    public UiElement() {

        setScale(new Vector2(120,100));
        setLayer(20);
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
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
    @Override
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

        if(getParent()!=null) {

            float x = (getParent().getPosition().getX()+((getParent().getScale().getX()/2)-getScale().getX()/2));
            float y = (getParent().getPosition().getY()+((getParent().getScale().getY()/2)-getScale().getY()/2));

            setPosition(new Vector2(x,y).add(getLocalPosition())); // we get the parents position and we add our localPosition
            // update this in the setScale and setRotation instead
            setRotation(getParent().getRotation().add(getLocalRotation()));
            //setScale(getParent().getScale().add(getLocalScale()));



        }
        if(this.getChildren().size()>0){
            updateChildren(); // updates all the children
        }
        //mouse enter and exit
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
