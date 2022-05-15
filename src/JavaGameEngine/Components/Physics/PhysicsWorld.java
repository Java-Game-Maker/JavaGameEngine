package JavaGameEngine.Components.Physics;

import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Vector2;

public class PhysicsWorld {

    public static Vector2 gravityAcceleration=new Vector2(0,9.82f*JavaGameEngine.DELAY/10000);


    public static Vector2 getGravityAcceleration() {
        return gravityAcceleration;
    }

    public static void setGravityAcceleration(Vector2 gravityAcceleration) {
        PhysicsWorld.gravityAcceleration = gravityAcceleration;
    }
}
