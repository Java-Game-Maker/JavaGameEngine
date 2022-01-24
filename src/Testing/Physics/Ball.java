package Testing.Physics;

import Main.Msc.Vector2;
import Main.Objects.Components.Collision.ScareCollider;
import Main.Objects.Components.Physics.PhysicsBody;
import Main.Objects.Object;
import Main.Msc.Input.*;

public class Ball extends Object {

    public Ball()
    {
        ScareCollider c = new ScareCollider();
        c.setPosition(getPosition());
        c.setScale(getScale());
        c.setParent(this);
        c.setVisible(true);
        c.setTrigger(false);
        addCollider(c);

        PhysicsBody body = new PhysicsBody();
        body.setScale(getScale());
        body.setParent(this);
        body.setPosition(getPosition());
        body.setUseGravity(false);
        setPhysicsbody(body);
        body.setUseGravity(false);

    }

    @Override
    public void Update() {
        super.Update();
        if(Input.isKeyDown(Keys.W))
        {
            getPhysicsbody().addForce(Vector2.right,100);
            getPhysicsbody().setUseGravity(true);
        }
    }
}
