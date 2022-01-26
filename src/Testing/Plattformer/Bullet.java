package Testing.Plattformer;

import JGame.Msc.Vector2;
import JGame.Objects.Components.Collision.SquareCollider;
import JGame.Objects.Components.GameObject;
import JGame.Objects.Components.Physics.PhysicsBody;

public class Bullet extends GameObject {

    private float speed = 10;

    public Bullet(Vector2 position,Vector2 direction) {
        super(position);
        setDirection(direction);
        setScale(new Vector2(50,5));
        setTag("Bullet");
        SquareCollider c = new SquareCollider();
        addComponent(c);
        PhysicsBody b = new PhysicsBody();
        addComponent(b);
    }

    @Override
    public void Update() {
        super.Update();
        setPosition(getPosition().add(getDirection().multiply(speed)));
        if(this.getPosition().getX()>10000)
        {
            this.Destroy();
        }
    }
}
