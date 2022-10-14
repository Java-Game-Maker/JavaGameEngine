package javagameengine.components;

import javagameengine.CollisionEvent;
import javagameengine.JavaGameEngine;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.*;

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

    public void addForce(Vector2 force){
        //F=ma
        //a = f/mas
        Vector2 a = force.divide(mass);
        velocity = velocity.add(a);
    }
    Vector2 velocity0 = Vector2.zero;
    @Override
    public void update() {
        super.update();

        //sets our velocity to our parent
        getFirstParent().setPosition(getFirstParent().getPosition().add(velocity.multiply(JavaGameEngine.deltaTime)));

        //rotation
        // m = f * l
        getParent().rotate((float) (getParent().getPosition().getDistance(rotationalPoint) * rotationalForce/100));

        if(useGravity)
            velocity = velocity.add(JavaGameEngine.g.multiply(JavaGameEngine.deltaTime));

        // friction
        velocity = velocity.subtract(velocity.getNormalized().multiply(friction).multiply(JavaGameEngine.deltaTime));
        // remove rotationalforce
        rotationalForce = rotationalForce-(rotationalForce*0.01f);


    }

    public void response(CollisionEvent event){
        try{
            PhysicsBody me = event.getPhysicsBody1();
            PhysicsBody other = event.getPhysicsBody2();

            float cor = 1;

            Vector2 newb = ((me.velocity.multiply(me.mass).add
                    ( other.velocity.multiply(other.mass) ).add
                    ( (other.velocity.subtract(me.velocity) ).multiply(other.mass))).divide
                    (me.mass+other.mass));

            Vector2 newCb =((me.velocity.multiply(me.mass)).add
                    (other.velocity.multiply(other.mass).add
                            ((me.velocity.subtract(other.velocity).multiply(me.mass)))).divide
                    (me.mass+other.mass));


        /*
            float f1 = newb.getX() - newb.getY();
            float f2 = newCb.getX() - newCb.getY();

            me.setRotationalForce(f1);
            me.setRotationalPoint(event.getCollisionPoint());


            other.setRotationalForce(f2);
            other.setRotationalPoint(event.getCollisionPoint());
        */

            me.velocity = (!me.isFreeze())?newb:me.velocity;
            other.velocity = (!other.isFreeze())?newCb:other.velocity;

        }catch (NullPointerException e){
        }


    }

    public Vector2 getForce(){

        Vector2 a = (velocity.subtract(velocity0)).divide((float) JavaGameEngine.deltaTime);

        return a.multiply(mass);

    }

}
