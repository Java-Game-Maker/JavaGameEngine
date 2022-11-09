package com.javagamemaker.javagameengine;

import com.javagamemaker.javagameengine.components.Collider;
import com.javagamemaker.javagameengine.components.PhysicsBody;
import com.javagamemaker.javagameengine.msc.Vector2;

public class CollisionEvent {

    Collider collider1;
    Collider collider2;
    PhysicsBody physicsBody1;
    PhysicsBody physicsBody2;
    Vector2 collisionPoint;

    public CollisionEvent(Collider collider1, Collider collider2, Vector2 collisionPoint) {
        this.collider1 = collider1;
        this.collider2 = collider2;
        try{this.physicsBody1 = (PhysicsBody) collider1.getParent().getChild(new PhysicsBody());}catch (IndexOutOfBoundsException e){}
        try{this.physicsBody2 = (PhysicsBody) collider2.getParent().getChild(new PhysicsBody());}catch (IndexOutOfBoundsException e){}
        this.collisionPoint = collisionPoint;
    }

    public Collider getCollider1() {
        return collider1;
    }

    public void setCollider1(Collider collider1) {
        this.collider1 = collider1;
    }

    public Collider getCollider2() {
        return collider2;
    }

    public void setCollider2(Collider collider2) {
        this.collider2 = collider2;
    }

    public PhysicsBody getPhysicsBody1() {
        return physicsBody1;
    }

    public void setPhysicsBody1(PhysicsBody physicsBody1) {
        this.physicsBody1 = physicsBody1;
    }

    public PhysicsBody getPhysicsBody2() {
        return physicsBody2;
    }

    public void setPhysicsBody2(PhysicsBody physicsBody2) {
        this.physicsBody2 = physicsBody2;
    }

    public Vector2 getCollisionPoint() {
        return collisionPoint;
    }

    public void setCollisionPoint(Vector2 collisionPoint) {
        this.collisionPoint = collisionPoint;
    }
}
