package javagameengine.components.physics;

import javagameengine.JavaGameEngine;
import javagameengine.msc.Vector2;

/**
 * Orgenises some constends for the physics such as gravity.
 */
public class PhysicsWorld {

    private static Vector2 gravityAcceleration=new Vector2(0,9.82f*JavaGameEngine.DELAY/10000);

    /**
     * This is the acceleration of the gravity. It is a vector so it is simply added to the movement of a physics body
     * @return Vector2 with a down normal
     */
    public static Vector2 getGravityAcceleration() {
        return gravityAcceleration;
    }

    /**
     * Sets the physics acceleration
     * @param gravityAcceleration vector2 with downforce
     */
    public static void setGravityAcceleration(Vector2 gravityAcceleration) {
        PhysicsWorld.gravityAcceleration = gravityAcceleration;
    }
}
