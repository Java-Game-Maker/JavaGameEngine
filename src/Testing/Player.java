package Testing;

import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.msc.Vector2;

public class Player extends GameObject {

    @Override
    public void update() {
        super.update();
        if(Input.isKeyPressed(Keys.SPACE))
            setPosition(getPosition().add(new Vector2(0,10)));
    }
}
