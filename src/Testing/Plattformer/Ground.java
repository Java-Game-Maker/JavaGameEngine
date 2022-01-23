package Testing.Plattformer;

import Main.Msc.Vector2;
import Main.Objects.Collision.ScareCollider;
import Main.Objects.Object;

public class Ground extends Object {
    public Ground(Vector2 position, Vector2 scale) {
        super(position);
        setScale(scale);

        ScareCollider c = new ScareCollider();
        c.setVisible(false);
        c.setParent(this);
        c.setScale(scale);
        c.setPosition(position);
        addCollider(c);
    }

}
