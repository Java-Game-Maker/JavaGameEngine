package Testing;

import javagameengine.components.colliders.SquareCollider;
import javagameengine.components.GameObject;
import javagameengine.msc.Vector2;

public class Ground extends GameObject {

    public Ground() {
        setScale(new Vector2(600,64));
        setPosition(new Vector2(300,500));

        SquareCollider s = new SquareCollider();
        s.setVisible(true);
        addChild(s);
    }
    public Ground(Vector2 pos) {
        setScale(new Vector2(600,64));
        setPosition(pos);

        SquareCollider s = new SquareCollider();
        s.setVisible(true);
        addChild(s);
    }



    @Override
    public void update() {
        super.update();
        //Debug.log(getParent().toString());
    }
}
