package JGame.Objects.Components.Collision;

import JGame.Msc.Vector2;

public class CircleCollider extends Collider{

    private float radius = 1;

    public CircleCollider() {
    }

    public CircleCollider(Vector2 position, Vector2 scale) {
        super(position, scale);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void collided(Collider otherCollider) {
        CircleCollider obj = this;
        if(obj.getPosition().getDistance(otherCollider.getPosition())<=getScale().getX()/2+otherCollider.getScale().getX()/2&&!obj.equals(otherCollider))
        {
            if(!isTrigger())
                obj.getParent().onCollision(otherCollider.getParent());
            else
                obj.getParent().onTrigger(otherCollider.getParent());

            //otherCollider.getParent().onCollision(getParent());
        }
    }
}
