package Testing.Plattformer;

import JavaGameEngine.Msc.Vector2;
import JavaGameEngine.Objects.Components.Collision.SquareCollider;
import JavaGameEngine.Objects.Components.Physics.PhysicsBody;
import JavaGameEngine.Objects.Components.GameObject;

public class Ground extends GameObject {
    public Ground(Vector2 position, Vector2 scale,String tagname) {
        super(position);
        setTag(tagname);
        setScale(scale);
        //setShape(new Shape(new int[]{1,2,2,1},new int[]{2,2,1,1},4));


        SquareCollider c = new SquareCollider();
        c.setVisible(true);
        c.setScale(getScale());
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
