package Testing;

import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

public class Ground extends GameObject {

    public Ground() {
        setScale(new Vector2(600,100));
        setPosition(new Vector2(100,500));
        SquareCollider squareCollider = new SquareCollider();
        squareCollider.setScale(getScale());
        //squareCollider.setPosition(getPosition());
        addChild(squareCollider);
    }

    @Override
    public void update() {
        super.update();
        //Debug.log(getParent().toString());
    }
}
