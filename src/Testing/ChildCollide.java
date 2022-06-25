package Testing;

import javagameengine.backend.Scene;
import javagameengine.components.GameObject;
import javagameengine.components.colliders.SquareCollider;
import javagameengine.components.physics.PhysicsBody;
import javagameengine.msc.Vector2;

public class ChildCollide extends Scene {


    public ChildCollide(){

        GameObject parent = new GameObject();

        GameObject child = new GameObject();

        child.addChild(new SquareCollider(true));
        child.addChild(new PhysicsBody(false));

        parent.addChild(new PhysicsBody());
        parent.addChild(child);

        GameObject parent2 = new GameObject();
        parent2.setPosition(Main.origin.add(new Vector2(0,300)));

        GameObject child2 = new GameObject();

        child2.addChild(new SquareCollider(true));
        child2.addChild(new PhysicsBody(false));


        parent2.addChild(child2);

        components.add(parent);
        components.add(parent2);


    }

}
