package com.javagamemaker.javagameengine.components.gamecompnents;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

/**
 * Generic component that will make a object move right to left with the specifed keys
 *
 */
public class PlatformPlayerController extends Component {

    private float maxSpeed = 3;
    private float friction = 0.4f;

    private int right = Keys.D;
    private int left = Keys.A;
    private int space = Keys.SPACE;
    private float jumpForce = 30;
    private String groundTag = "Grounded";
    public PlatformPlayerController(float maxSpeed, float friction) {
        this.maxSpeed = maxSpeed;
        this.friction = friction;
    }

    public PlatformPlayerController(int right, int left, int space) {
        this.right = right;
        this.left = left;
        this.space = space;
    }

    public PlatformPlayerController() {
    }

    public PlatformPlayerController(float maxSpeed, float friction, int right, int left, int space, float jumpForce) {
        this.maxSpeed = maxSpeed;
        this.friction = friction;
        this.right = right;
        this.left = left;
        this.space = space;
        this.jumpForce = jumpForce;
    }

    public float getJumpForce() {
        return jumpForce;
    }

    public void setJumpForce(float jumpForce) {
        this.jumpForce = jumpForce;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getSpace() {
        return space;
    }

    public String getGroundTag() {
        return groundTag;
    }

    public void setGroundTag(String groundTag) {
        this.groundTag = groundTag;
    }

    public void setSpace(int space) {
        this.space = space;
    }
    private boolean grounded = true;

    @Override
    public void start() {
        super.start();
        Component parent = getParent();
        //if physics body does not exist add it
        if(parent.<PhysicsBody>getChild(new PhysicsBody()) == null)
            parent.add(new PhysicsBody(true));
    }

    @Override
    public void update() {
        super.update();
        Component parent = getParent();
       // Debug.log(parent.getChildren().toString());
        PhysicsBody physicsBody = parent.<PhysicsBody>getChild(new PhysicsBody());
        if(physicsBody!= null){

        if(Input.isKeyDown(right)){
            if(physicsBody.velocity.getX() < maxSpeed){
                physicsBody.addForce(Vector2.right);
            }
        }
        if(Input.isKeyDown(left)){
            if(physicsBody.velocity.getX() > -maxSpeed){
                physicsBody.addForce(Vector2.left);
            }
        }

        if(Input.isKeyPressed(space) && grounded){
            physicsBody.addForce(Vector2.up.multiply(jumpForce));
        }

        physicsBody.addForce(physicsBody.velocity.getNormalized().multiply(-friction).removeY());
        grounded = false;
        }
    }

    @Override
    public void onCollisionEnter(CollisionEvent collisionEvent) {
        super.onCollisionEnter(collisionEvent);
        if(collisionEvent.getCollider2().getFirstParent().getTag() == "Ground"){
            grounded = true;
        }
    }

}
