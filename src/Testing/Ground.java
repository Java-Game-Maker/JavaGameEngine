package Testing;

import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.Components.Sprite.Sprite;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Ground extends GameObject {

    public Ground() {
        setScale(new Vector2(600,100));
        setPosition(new Vector2(300,500));
        addChild(new SquareCollider());

    }

    @Override
    public void update() {
        super.update();
        //Debug.log(getParent().toString());
    }
}
