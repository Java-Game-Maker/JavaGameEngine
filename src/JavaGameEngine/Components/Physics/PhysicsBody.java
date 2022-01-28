package JavaGameEngine.Components.Physics;

import JavaGameEngine.Components.Component;
import JavaGameEngine.msc.Vector2;

public class PhysicsBody extends Component {

    private boolean useGravity=true;
    private float mass=10;
    private Vector2 velocity = Vector2.zero;
    private float friction = 0;

    public boolean isUseGravity() {
        return useGravity;
    }
    public void setUseGravity(boolean useGravity) {
        this.useGravity = useGravity;
    }

    public float getMass() {
        return mass;
    }
    public void setMass(float mass) {
        this.mass = mass;
    }

    public Vector2 getVelocity() {
        return velocity;
    }
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getFriction() {
        return friction;
    }
    public void setFriction(float friction) {
        this.friction = friction;
    }

    public void addForce(Vector2 direction, float force)
    {
        Vector2 direction1 = direction.multiply(force/100);
        setVelocity(getVelocity().add(direction1));

        //getParent().setPosition(getParent().getPosition().add(direction1));
    }
    @Override
    public void update()
    {
        if(useGravity)
        {
            Vector2 g = PhysicsWorld.getGravityAcceleration();

            setVelocity(getVelocity().add(g));
        }
        setVelocity(getParent().movePosition(getParent().getPosition().add(getVelocity())));
    }
}
