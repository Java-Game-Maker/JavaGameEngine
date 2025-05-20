package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;

/**
 * The PhysicsBody is a component that gived physics to parent component in form of
 * collision and gravity
 */
public class PhysicsBody extends Component{

    private boolean useGravity = false;
    Vector2 massPoint = new Vector2(0,0);
    private float rotationalForce = 0;
    private Vector2 rotationalPoint = new Vector2(1,1);
    public float mass=10;
    private boolean freeze = false;
    public boolean isFreeze() {
        return freeze;
    }
    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }
    public Vector2 velocity = Vector2.zero;

    private float friction = 0.01f;
    public PhysicsBody(){}
    public PhysicsBody(boolean useGravity){
        this.useGravity = useGravity;
    }

    public void setFriction(float friction){
        this.friction = friction;
    }
    public float getFriction() {
        return friction;
    }

    public float getRotationalForce() {
        return rotationalForce;
    }

    public void setRotationalForce(float rotationalForce) {
        this.rotationalForce = rotationalForce;
    }

    public Vector2 getRotationalPoint() {
        return rotationalPoint;
    }

    public void setRotationalPoint(Vector2 rotationalPoint) {
        this.rotationalPoint = rotationalPoint;
    }

    public Vector2 getMassPoint() {
        return massPoint;
    }

    public void setMassPoint(Vector2 massPoint) {
        this.massPoint = massPoint;
    }

    @Override
    public void setPosition(Vector2 position) {
        massPoint = massPoint.add(position.subtract(this.position));
        super.setPosition(position);
    }

    /**
     * @return true if the body gets effected by gravity
     */
    public boolean isUseGravity() {
        return useGravity;
    }

    /**
     * Sets if the physicsbody should be effected by gravity
     * @param useGravity
     */
    public void setUseGravity(boolean useGravity) {
        this.useGravity = useGravity;
    }

    /**
     * add force to the body in form of a vector ((10,0) fo to the right)
     * @param force to be added
     */
    public void addForce(Vector2 force){
        //F=ma
        //a = f/mas
        Vector2 a = force.divide(mass);
        velocity = velocity.add(a);
    }
    public Vector2 getVelocity(){
        return velocity;
    }
    Vector2 velocity0 = Vector2.zero;
    @Override
    public void update() {
        super.update();

        //sets our velocity to our parent
        getParent().translate(velocity.multiply(JavaGameEngine.deltaTime));

        //rotation
        // m = f * l
        getParent().rotate(rotationalForce/10);

        float deltaS = rotationalPoint.subtract(massPoint).getX();

        if(useGravity)
            velocity = velocity.add(JavaGameEngine.g.multiply(JavaGameEngine.deltaTime));

        // friction
        velocity = velocity.subtract(velocity.getNormalized().multiply(friction).multiply(JavaGameEngine.deltaTime));
        // remove rotationalforce
        rotationalForce = rotationalForce-(rotationalForce*0.01f);
        prevPosition = getPosition();
    }

    /**
     * this function is called when a collision happends and is suppose to
     * respond to the collision with physics
     * @param event
     */
    public void response(CollisionEvent event){
        try{

            PhysicsBody me = event.getPhysicsBody1();
            PhysicsBody other = event.getPhysicsBody2();
            if(other == null){

            }
            else{
                float cor = 1;

                Vector2 newb = ((me.getVelocity().multiply(me.mass).add
                        ( other.getVelocity().multiply(other.mass) ).add
                        ( (other.getVelocity().subtract(me.getVelocity()) ).multiply(other.mass))).divide
                        (me.mass+other.mass));

                Vector2 newCb =((me.getVelocity().multiply(me.mass)).add
                        (other.getVelocity().multiply(other.mass).add
                                ((me.getVelocity().subtract(other.getVelocity()).multiply(me.mass)))).divide
                        (me.mass+other.mass));

                 me.velocity    = (!me.isFreeze())    ? newb:me.getVelocity();
                 other.velocity = (!other.isFreeze()) ? newCb:other.getVelocity();
            }
        }catch (NullPointerException e){}
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        Color color = g.getColor();
        g.setColor(Color.red);
        g.setColor(color);
    }

    /**
     * @return current force applied (f=am)
     */
    public Vector2 getForce(){
        Vector2 a = (velocity.subtract(velocity0)).divide((float) JavaGameEngine.deltaTime);
        return a.multiply(mass);
    }

}
