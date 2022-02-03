package JavaGameEngine.Components.Collider;

import JavaGameEngine.Components.Component;
import JavaGameEngine.msc.Debug;

import java.awt.*;
import java.util.LinkedList;

public class SquareCollider extends Collider{

    private boolean hasCollided = false;

    @Override
    public void collisionHandler(Component ob2)
    {
        if(!hasCollided&&ob2!=null) {
            if(!isTrigger()&& !((Collider) ob2).isTrigger()) {
                getParent().onCollision(ob2.getParent());
                if(!hasCollided) {
                    getParent().onCollisionEnter(ob2.getParent());
                }
            }
            else {
                Debug.log("knas");
                getParent().onTrigger(ob2.getParent());
            }
            hasCollided=true;
        }
        else if(hasCollided&&ob2!=null) {
            getParent().onCollisionExit(ob2.getParent());
            hasCollided = false;
        }
    }

    public static Component isCollision(Collider ob1, Collider parent, LinkedList<Component> objects) {

        class Player{
            float x;
            float y;
            float width;
            float height;
        }

        Player player1 = new Player();
        player1.x = ob1.getPosition().getX()-ob1.getScale().getX()/2;
        player1.y = ob1.getPosition().getY()-ob1.getScale().getY()/2;
        player1.height = ob1.getScale().getY();
        player1.width = ob1.getScale().getX();

        for(Component ob :objects)
        {
            if(ob!=ob1.getParent()&&ob!=parent.getParent()) {
                for (Component ob21 : ob.getChildren(new SquareCollider())) {
                    Collider ob2 = (Collider) ob21;
                    Player player2 = new Player();
                    player2.x = ob2.getPosition().getX();
                    player2.y = ob2.getPosition().getY();
                    player2.height = ob2.getScale().getY();
                    player2.width = ob2.getScale().getX();

                    if (player1.x < player2.x + player2.width &&
                            player1.x + player1.width > player2.x &&
                            player1.y < player2.y + player2.height &&
                            player1.y + player1.height > player2.y) {
//                        Debug.log("collide");
                        if(!ob1.isTrigger()&& !ob2.isTrigger())
                            return ob2;
                        else {
                            ob1.getParent().onTrigger(ob2.getParent());
                            ob2.getParent().onTrigger(ob1.getParent());
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        if(isVisible()){
            g.setColor(Color.GREEN);
            g.drawRect((int) getPosition().getX(), (int) getPosition().getY(), (int) getScale().getX(), (int) getScale().getY());
            g.setColor(Color.darkGray);
        }
        /*
        Point ob11 = new Point();
        ob11.x = (int) (getPosition().getX()+getScale().getX());
        ob11.y = (int) (getPosition().getY());

        Point ob12 = new Point();
        ob12.x = (int) (getPosition().getX());
        ob12.y = (int) (getPosition().getY()
        );

        Point ob13 = new Point();
        ob13.x = (int) (getPosition().getX());
        ob13.y = (int) (getPosition().getY()+getScale().getY());

        Point ob14 = new Point();
        ob14.x = (int) (getPosition().getX()+getScale().getX());
        ob14.y = (int) (getPosition().getY()+getScale().getY());

        g.drawOval((int) ob11.x, (int) ob11.y,5,5);
        g.drawOval((int) ob12.x, (int) ob12.y,5,5);
        g.drawOval((int) ob13.x, (int) ob13.y,5,5);
        g.drawOval((int) ob14.x, (int) ob14.y,5,5);
        */

    }

    public SquareCollider copy()
    {
        SquareCollider c = new SquareCollider();
        c.setLocalPosition(getLocalPosition());
        c.setParent(getParent());
        //c.setVisible(isVisible());
        c.setPosition(getPosition());
        c.setScale(getScale());
        return c;
    }

}
