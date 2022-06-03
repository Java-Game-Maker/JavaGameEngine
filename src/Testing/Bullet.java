package Testing;

import javagameengine.components.colliders.SquareCollider;
import javagameengine.components.GameObject;
import javagameengine.components.physics.PhysicsBody;
import javagameengine.msc.Vector2;

public class Bullet extends GameObject {

    public Bullet(Vector2 pos, Vector2 rot){
        setPosition(pos);
        setRotation(rot);
        setScale(new Vector2(20,10));
        addChild(new SquareCollider());
        addChild(new PhysicsBody());
    }

    @Override
    public void update() {
        super.update();

        setPosition(getPosition().add(getRotation().multiply(5)));

    }
}
