package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

public class Grabber extends Component{

    Component parent;
    private Vector2 offset;

    public Grabber(Component parent){
        this.parent = parent;
    }
    Vector2 gridSnapping = new Vector2(10,10);
    @Override
    public void update() {
        super.update();
        if (Input.isMouseDown(1) && parent.isMouseInside()) {
            Debug.log("asd");
            if (this.offset == null) {
                this.offset = parent.getPosition().subtract(Input.getMousePosition());
            }

            if (parent.getParent() == null) {
                float gridCubeWidth = gridSnapping.getX(), gridCubeHeight = gridSnapping.getY();

                float x = Math.round(Input.getMousePosition().add(this.offset).getX() / gridCubeWidth) * gridCubeWidth;
                float y = Math.round(Input.getMousePosition().add(this.offset).getY() / gridCubeHeight) * gridCubeHeight;
                parent.setPosition(new Vector2(x,y));

            }
            else {
                float gridCubeWidth = gridSnapping.getX(), gridCubeHeight = gridSnapping.getY();
                Vector2 pos = Input.getMousePosition().add(this.offset).subtract(parent.getParent().getPosition());

                float x = Math.round(pos.getX() / gridCubeWidth) * gridCubeWidth;
                float y = Math.round(pos.getY() / gridCubeHeight) * gridCubeHeight;

                parent.setParentOffset(new Vector2(x,y));
                parent.getFirstParent().setPosition(parent.getFirstParent().getPosition());
            }
        }
    }
}
