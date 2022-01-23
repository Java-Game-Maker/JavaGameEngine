package Testing.Plattformer;

import Main.Msc.Vector2;
import Main.Objects.Collision.ScareCollider;
import Main.Objects.Object;

public class Bullet extends Object {

    private float speed = 5;

    public Bullet(Vector2 position,Vector2 direction) {
        super(position);
        setDirection(direction);
        setScale(new Vector2(10,5));
        setTag("Bullet");
        ScareCollider c = new ScareCollider();
        c.setParent(this);
        c.setScale(new Vector2(50,50));
        c.setVisible(false);
        addCollider(c);
    }

    @Override
    public void Update() {
        super.Update();
        setPosition(getPosition().add(getDirection().multiply(speed)));
        if(this.getPosition().getX()>1000)
        {
            this.Destroy();
        }
    }
}
