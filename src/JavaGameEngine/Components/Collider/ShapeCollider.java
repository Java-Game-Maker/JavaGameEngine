package JavaGameEngine.Components.Collider;


import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.Components.Component;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class ShapeCollider extends Collider{

    public Polygon shape = new Polygon(new int[]{0,0,90,90},new int[]{0,90,90,0},4);

    public ShapeCollider(){

    }
    public void setShapeScale(Vector2 size){
        Debug.log("asd");
        shape = new Polygon(new int[]{0,0, (int) size.getX(), (int) size.getX()},new int[]{0, (int) size.getY(), (int) size.getY(),0},4);
    }

    @Override
    public void setPosition(Vector2 position) {
        Vector2 dir = position.subtract(this.getPosition());
        super.setPosition(position);

        shape.translate((int) dir.getX(), (int) dir.getY());

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if(this.isVisible()){
            g.drawPolygon(shape);
        }
    }

    @Override
    public void collisionHandler(Component ob2) {

    }

    @Override
    public void collisionHandler() {

    }
}
