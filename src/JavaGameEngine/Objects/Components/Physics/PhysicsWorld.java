package JavaGameEngine.Objects.Components.Physics;

import JavaGameEngine.Msc.Vector2;

public class PhysicsWorld {

    public static Vector2 gravityAcceleration=new Vector2(0,0.0982f);


    public static Vector2 getGravityAcceleration() {
        return gravityAcceleration;
    }

    public static void setGravityAcceleration(Vector2 gravityAcceleration) {
        PhysicsWorld.gravityAcceleration = gravityAcceleration;
    }
}
