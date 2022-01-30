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
            if(!isTrigger()) {
                getParent().onCollision(ob2.getParent());
                if(!hasCollided) {
                    getParent().onCollisionEnter(ob2.getParent());
                }
            }
            else {
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
                    player2.x = ob2.getPosition().getX() - ob2.getScale().getX() / 2;
                    player2.y = ob2.getPosition().getY() - ob2.getScale().getY() / 2;
                    player2.height = ob2.getScale().getY();
                    player2.width = ob2.getScale().getX();

                    if (player1.x < player2.x + player2.width &&
                            player1.x + player1.width > player2.x &&
                            player1.y < player2.y + player2.height &&
                            player1.y + player1.height > player2.y) {
//                        Debug.log("collide");
                        return ob2;
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
