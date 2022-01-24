package JGame.Objects.Components.Collision;

import JGame.Msc.Vector2;
import JGame.Objects.GameObject;

import java.util.LinkedList;

public class SquareCollider extends Collider{

    private boolean hasCollided = false;

    public SquareCollider() {
    }

    public SquareCollider(Vector2 position, Vector2 scale) {
        super(position, scale);
    }

    @Override
    public boolean isColliding(Collider otherCollider) {
        return super.isColliding(otherCollider);
    }

    public void collided1(Collider ob2) {

        class Player{
            float x;
            float y;
            float width;
            float height;
        }

        Player player1 = new Player();
        player1.x = getPosition().getX()-getScale().getX()/2;
        player1.y = getPosition().getY()-getScale().getY()/2;
        player1.height = getScale().getY();
        player1.width = getScale().getX();

        Player player2 = new Player();
        player2.x = ob2.getPosition().getX()-ob2.getScale().getX()/2;
        player2.y = ob2.getPosition().getY()-ob2.getScale().getY()/2;
        player2.height = ob2.getScale().getY();
        player2.width = ob2.getScale().getX();

        if(player1.x < player2.x + player2.width &&
                player1.x + player1.width > player2.x &&
                player1.y < player2.y + player2.height &&
                player1.y + player1.height > player2.y)
        {
            if(!isTrigger())
            {
                getParent().onCollision(ob2.getParent());
                if(!hasCollided)
                {
                    getParent().onCollisionEnter(ob2.getParent());
                }
               
            }

            else
            {
                getParent().onTrigger(ob2.getParent());
            }


            hasCollided=true;
        }
        else if(hasCollided)
        {
            getParent().onCollisionExit(ob2.getParent());
            hasCollided = false;
        }
    }
    @Override
    public void collided(Collider ob2) {

        class Player{
            float x;
            float y;
            float width;
            float height;
        }

        Player player1 = new Player();
        player1.x = getPosition().getX()-getScale().getX()/2;
        player1.y = getPosition().getY()-getScale().getY()/2;
        player1.height = getScale().getY();
        player1.width = getScale().getX();

        Player player2 = new Player();
        player2.x = ob2.getPosition().getX()-ob2.getScale().getX()/2;
        player2.y = ob2.getPosition().getY()-ob2.getScale().getY()/2;
        player2.height = ob2.getScale().getY();
        player2.width = ob2.getScale().getX();

        if(player1.x < player2.x + player2.width &&
                player1.x + player1.width > player2.x &&
                player1.y < player2.y + player2.height &&
                player1.y + player1.height > player2.y)
        {
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
        else if(hasCollided) {
            getParent().onCollisionExit(ob2.getParent());
            hasCollided = false;
        }
    }

    @Override
    public void collisionHandler(Collider ob2)
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

    public static Collider isCollision(Collider ob1, Collider parent, LinkedList<GameObject> objects) {

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

        for(GameObject ob :objects)
        {
            if(ob!=ob1.getParent()&&ob!=parent.getParent()) {
                for (Collider ob2 : ob.getComponents(new SquareCollider())) {

                    Player player2 = new Player();
                    player2.x = ob2.getPosition().getX() - ob2.getScale().getX() / 2;
                    player2.y = ob2.getPosition().getY() - ob2.getScale().getY() / 2;
                    player2.height = ob2.getScale().getY();
                    player2.width = ob2.getScale().getX();

                    if (player1.x < player2.x + player2.width &&
                            player1.x + player1.width > player2.x &&
                            player1.y < player2.y + player2.height &&
                            player1.y + player1.height > player2.y) {
                        //System.out.println(ob1.getParent().getTag()+" collides with "+ob.getTag());
                        return ob2;
                    }
                }
            }
        }
        return null;
    }

    public SquareCollider copy()
    {
        SquareCollider c = new SquareCollider();
        c.setOffset(getOffset());
        c.setParent(getParent());
        c.setVisible(isVisible());
        c.setPosition(getPosition());
        c.setScale(getScale());
        return c;
    }


}
