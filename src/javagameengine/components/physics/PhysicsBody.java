package javagameengine.components.physics;

import javagameengine.JavaGameEngine;
import javagameengine.backend.UpdateThread;
import javagameengine.components.Component;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

/**
 * Physicsbody is a component that handels physics,gravity and forces.
 */

public class PhysicsBody extends Component {

    private boolean useGravity=true;
    private float mass=10;
    private Vector2 velocity = Vector2.zero;
    private float friction = 0;

    public PhysicsBody(){}
    public PhysicsBody(boolean useGravity){
        this.useGravity = useGravity;
    }
    /**
     *
     * @return true if the compnent is effected by gravity
     */
    public boolean isUseGravity() {
        return useGravity;
    }

    /**
     * Set if the component should be effected by gravity or not
     * @param useGravity true to be effected
     */
    public void setUseGravity(boolean useGravity) {
        this.useGravity = useGravity;
    }

    /**
     * returns the mass
     * @return the mass of the body
     */
    public float getMass() {
        return mass;
    }

    /**
     * sets the mass
     * @param mass the mass in kg
     */
    public void setMass(float mass) {
        this.mass = mass;
    }

    /**
     *
     * @return the velocity of te body
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * set the velocity
     * @param velocity vector2
     */
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    /**
     *
     * @return bodys friction
     */
    public float getFriction() {
        return friction;
    }

    /**
     * set bodys friction
     * @param friction friction
     */
    public void setFriction(float friction) {
        this.friction = friction;
    }

    /**This will add a force to the physicsbody
     * @param direction the force is applied in
     * @param force the force amount
     */
    public void addForce(Vector2 direction, float force)
    {
        Vector2 direction1 = direction.multiply(force/50);
        setVelocity(getVelocity().add(direction1.multiply(1)));

        //getParent().setPosition(getParent().getPosition().add(direction1));
    }

    /**
     * the update will add force down simulateing the gravity (if useGravity is true)
     */
    @Override
    public void update()
    {
        if(useGravity)
        {
            Vector2 g = PhysicsWorld.getGravityAcceleration();
            setVelocity(getVelocity().add(g.multiply(UpdateThread.deltatime)));

        }
        setVelocity(getParent().movePosition(getParent().getPosition().add(getVelocity()))); // this is so we reset the velocity in collision
    }
}
