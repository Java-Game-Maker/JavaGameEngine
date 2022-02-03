package Testing;

import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.msc.Vector2;

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
