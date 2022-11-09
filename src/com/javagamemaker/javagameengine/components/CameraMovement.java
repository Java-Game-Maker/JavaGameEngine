package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Vector2;

public class CameraMovement extends Component{

    @Override
    public void update() {
        super.update();
        if(Input.getMouseEvent() != null && Input.getMouseEvent().isControlDown()){
            Component camera = getParent();
            camera.setScale(camera.getScale().subtract(camera.getScale().divide(10).multiply(Input.getScrollValue())));
            if(Input.isKeyDown(Keys.W)){
                camera.setPosition(camera.getPosition().add(Vector2.down));
            }
            if(Input.isKeyDown(Keys.S)){
                camera.setPosition(camera.getPosition().add(Vector2.up));
            }
            if(Input.isKeyDown(Keys.D)){
                camera.setPosition(camera.getPosition().add(Vector2.left));
            }
            if(Input.isKeyDown(Keys.A)){
                camera.setPosition(camera.getPosition().add(Vector2.right));
            }

        }
    }
}
