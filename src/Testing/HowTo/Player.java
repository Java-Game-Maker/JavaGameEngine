package Testing.HowTo;

import JavaGameEngine.Msc.Input.Input;
import JavaGameEngine.Msc.Input.Keys;
import JavaGameEngine.Msc.Vector2;
import JavaGameEngine.Objects.Components.Collision.SquareCollider;
import JavaGameEngine.Objects.Components.GameObject;
import JavaGameEngine.Objects.Components.Physics.PhysicsBody;

public class Player extends GameObject {

    public Player()
    {
        setScale(new Vector2(50,100));

        PhysicsBody physicsBody = new PhysicsBody();
        physicsBody.setMass(100);
        addComponent(physicsBody);

        SquareCollider squareCollider = new SquareCollider();
        squareCollider.setVisible(true); // this is so we can se the collider (in green)
        addComponent(squareCollider);
    }

    @Override
    public void Update() {
        super.Update();
        if(Input.isKeyDown(Keys.D))
        {
            setPosition(getPosition().add(Vector2.right.multiply(2)));
        }
    }
}
