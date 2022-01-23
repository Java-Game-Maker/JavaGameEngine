package Main.Objects.Collision;

import Main.Msc.Vector2;

import java.awt.*;

public class ScareCollider extends Collider{

    public ScareCollider() {
    }

    public ScareCollider(Vector2 position, Vector2 scale) {
        super(position, scale);
    }

    @Override
    public boolean isColliding(Collider otherCollider) {
        return super.isColliding(otherCollider);
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
            if(!isTrgger())
                getParent().onCollision(ob2.getParent());
            else
                getParent().onTrigger(ob2.getParent());
        }
    }

}
