package JGame.Objects.Components.Physics;

import JGame.Msc.Vector2;
import JGame.Objects.Components.Component;

public class PhysicsBody extends Component {

    private boolean useGravity= true;
    private float mass=10;//kg
    private Vector2 velocity = new Vector2(0,0);
    private Vector2 force = new Vector2(0,0);
    private float frictionAmount = 0.1f;

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

    public Vector2 getForce() {
        return force;
    }

    public void setForce(Vector2 force) {
        this.force = force;
    }

    public float getFrictionAmount() {
        return frictionAmount;
    }

    public void setFrictionAmount(float frictionAmount) {
        this.frictionAmount = frictionAmount;
    }

    public void addForce(Vector2 direction, float force)
    {
        Vector2 direction1 = direction.multiply(force/100);
        setVelocity(getVelocity().add(direction1));

        //getParent().setPosition(getParent().getPosition().add(direction1));
    }

    float updateTimer = 0;

    @Override
    public void Update()
    {
        if(useGravity)
        {
            Vector2 g = PhysicsWorld.getGravityAcceleration();

            setVelocity(getVelocity().add(g));
        }
        setVelocity(getParent().movePosition(getParent().getPosition().add(getVelocity())));
    }



}
