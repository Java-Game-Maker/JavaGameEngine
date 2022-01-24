package JGame.Objects.Components.Collision;

import JGame.Msc.Vector2;
import JGame.Objects.Components.Component;

public class Collider extends Component  {


    private boolean isTrigger = false;


    public Collider() {
    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }

    public Collider(Vector2 position, Vector2 scale) {
        setPosition(position);
        setScale(scale);
    }

    public void collisionHandler(Collider ob2)
    {}

    public boolean isColliding(Collider otherCollider)
    {
        return false;
    }

    public void collided(Collider otherCollider)
    {

    }
    @Override
    public void Update()
    {
        setPosition(getParent().getPosition().add(getOffset()));
    }

}
