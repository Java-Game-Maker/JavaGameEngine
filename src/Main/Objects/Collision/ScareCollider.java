package Main.Objects.Collision;

import Main.Msc.Vector2;

public class ScareCollider extends Collider{

    public ScareCollider() {
    }

    public ScareCollider(Vector2 position, Vector2 scale) {
        super(position, scale);
    }

    @Override
    public boolean isColliding(Collider otherCollider) {
        return super.isColliding(otherCollider);
    }

    @Override
    public void collided(Collider otherCollider) {
        ScareCollider obj = this;
        if(obj.getPosition().getDistance(otherCollider.getPosition())<=getScale().getX()/2+otherCollider.getScale().getX()/2&&!obj.equals(otherCollider))
        {
            obj.getParent().onCollision(otherCollider.getParent());
            //otherCollider.getParent().onCollision(getParent());
        }
    }

}
