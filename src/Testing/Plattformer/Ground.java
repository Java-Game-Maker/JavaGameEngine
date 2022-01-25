package Testing.Plattformer;

import JGame.Msc.Vector2;
import JGame.Objects.Components.Collision.SquareCollider;
import JGame.Objects.Components.Physics.PhysicsBody;
import JGame.Objects.Components.GameObject;

public class Ground extends GameObject {
    public Ground(Vector2 position, Vector2 scale,String tagname) {
        super(position);
        setTag(tagname);
        setScale(scale);
        //setShape(new Shape(new int[]{1,2,2,1},new int[]{2,2,1,1},4));


        SquareCollider c = new SquareCollider();
        c.setVisible(true);
        addComponent(c);

        PhysicsBody b = new PhysicsBody();
        b.setUseGravity(false);
        addComponent(b);
    }

    public Ground() {
        PhysicsBody b = new PhysicsBody();
        b.setUseGravity(false);
        addComponent(b);

    }
}
