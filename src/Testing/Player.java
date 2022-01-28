package Testing;

import JavaGameEngine.Components.GameObject;
import JavaGameEngine.msc.Vector2;

public class Player extends GameObject {

    @Override
    public void update() {
        super.update();
        setPosition(getPosition().add(new Vector2(1,0)));
    }
}
