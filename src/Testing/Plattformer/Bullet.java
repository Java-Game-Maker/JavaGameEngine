package Testing.Plattformer;

import JGame.Msc.Vector2;
import JGame.Objects.Components.Collision.SquareCollider;
import JGame.Objects.Components.GameObject;

public class Bullet extends GameObject {

    private float speed = 10;

    public Bullet(Vector2 position,Vector2 direction) {
        super(position);
        setDirection(direction);
        setScale(new Vector2(10,5));
        setTag("Bullet");
        SquareCollider c = new SquareCollider();
        addComponent(c);
    }

    @Override
    public void Update() {
        super.Update();
        setPosition(getPosition().add(getDirection().multiply(speed)));
        if(this.getPosition().getX()>600)
        {
            this.Destroy();
        }
    }
}
