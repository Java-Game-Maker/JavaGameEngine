package Testing.HowTo;

import JavaGameEngine.Msc.Vector2;
import JavaGameEngine.Objects.Components.Collision.SquareCollider;
import JavaGameEngine.Objects.Components.GameObject;
import JavaGameEngine.Objects.Components.Physics.PhysicsBody;

public class Ground extends GameObject {

    public Ground()
    {
        setScale(new Vector2(600,50));
        setPosition(new Vector2(0,500));

        SquareCollider squareCollider = new SquareCollider();
        squareCollider.setScale(getScale());
        squareCollider.setVisible(true); // this is so we can see the collider (in green)
        addComponent(squareCollider);
    }
}
