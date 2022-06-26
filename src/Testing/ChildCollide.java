package Testing;

import javagameengine.backend.Scene;
import javagameengine.backend.input.Input;
import javagameengine.backend.input.Keys;
import javagameengine.components.Component;
import javagameengine.components.GameObject;
import javagameengine.components.colliders.SquareCollider;
import javagameengine.components.physics.PhysicsBody;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.util.ArrayList;

/*
 GameObject parent = new GameObject();
         parent.setTag("parent1");

         GameObject child = new GameObject();
         child.setTag("child1");

         child.addChild(new SquareCollider(true));
         child.addChild(new PhysicsBody(false));

         parent.addChild(new PhysicsBody());
         parent.addChild(child);

         GameObject parent2 = new GameObject();
         parent.setTag("parent2");

         parent2.setPosition(Main.origin.add(new Vector2(0,300)));

         GameObject child2 = new GameObject();
         child2.setTag("child2");

         child2.addChild(new SquareCollider(true));
         child2.addChild(new PhysicsBody(false));


         parent2.addChild(child2);

         components.add(parent);
         components.add(parent2);
         */

public class ChildCollide extends Scene {
    public ChildCollide(){

//        Debug.showWhere = true;

        GameObject parent = new GameObject();

        parent.setTag("parent1");

        GameObject child = new GameObject();
        child.setTag("child1");

        child.addChild(new SquareCollider(true));
    //    child.addChild(new PhysicsBody(false));

        parent.addChild(new PhysicsBody());
        parent.addChild(child);

        GameObject parent2 = new GameObject();
        parent.setTag("parent2");

        parent2.setPosition(Main.origin.add(new Vector2(0,300)));

        GameObject child2 = new GameObject();
        child2.setTag("child2");

        child2.addChild(new SquareCollider(true));
        //child2.addChild(new PhysicsBody(false));


        parent2.addChild(child2);

        components.add(parent);
        components.add(parent2);

    }
    @Override
    public void update() {
        super.update();
        if(Input.isKeyPressed(Keys.A)){
            Debug.log("A");
        }
        if(Input.isKeyPressed(Keys.D)){
            Debug.log("D");
        }

    }
}
