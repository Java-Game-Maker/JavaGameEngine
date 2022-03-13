package Testing;

import JavaGameEngine.Components.Collider.ShapeCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.msc.Vector2;

public class Test extends GameObject{

    public Test() {
        ShapeCollider s = new ShapeCollider();
        s.setLocalPosition(new Vector2(100,100));
        addChild(s);

    }

}

