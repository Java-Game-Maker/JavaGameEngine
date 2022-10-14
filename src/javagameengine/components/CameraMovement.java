package javagameengine.components;

import javagameengine.JavaGameEngine;
import javagameengine.input.Input;
import javagameengine.input.Keys;
import javagameengine.msc.Vector2;

public class CameraMovement extends Component{
    float speed = 2;

    @Override
    public void update() {
        super.update();
        if(Input.getMouseEvent() != null){
            Component camera = getParent();
            camera.setScale(camera.getScale().subtract(camera.getScale().divide(10).multiply(Input.getScrollValue())));
            if(Input.isKeyDown(Keys.W)){
                camera.setPosition(camera.getPosition().add(Vector2.down.multiply(speed)));
            }
            if(Input.isKeyDown(Keys.S)){
                camera.setPosition(camera.getPosition().add(Vector2.up.multiply(speed)));
            }
            if(Input.isKeyDown(Keys.D)){
                camera.setPosition(camera.getPosition().add(Vector2.left.multiply(speed)));
            }
            if(Input.isKeyDown(Keys.A)){
                camera.setPosition(camera.getPosition().add(Vector2.right.multiply(speed)));
            }

        }
    }
}
