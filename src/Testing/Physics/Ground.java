package Testing.Physics;

import Main.Objects.Components.Collision.ScareCollider;
import Main.Objects.Object;

public class Ground extends Object {

    public Ground() {
        ScareCollider c = new ScareCollider();
        c.setPosition(getPosition());
        c.setScale(getScale());
        c.setVisible(true);
        c.setParent(this);
        addCollider(c);
    }
}
