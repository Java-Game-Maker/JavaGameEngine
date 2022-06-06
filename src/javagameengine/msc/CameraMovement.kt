package javagameengine.msc

import javagameengine.backend.UpdateThread
import javagameengine.backend.input.Input
import javagameengine.backend.input.Keys
import javagameengine.components.Component
import java.awt.Graphics

class CameraMovement(speed:Float=1f) : Component() {

    var speed : Float = 1f;
    init {
        this.speed = speed;
    }

    override fun update() {
        super.update();
        if(Input.isKeyDown(Keys.W)){
            parent.position = parent.position.add(Vector2.up.multiply(speed*UpdateThread.deltatime))
        }
        if(Input.isKeyDown(Keys.S)){
            parent.position = parent.position.add(Vector2.down.multiply(speed*UpdateThread.deltatime))
        }
        if(Input.isKeyDown(Keys.A)){
            parent.position = parent.position.add(Vector2.left.multiply(speed*UpdateThread.deltatime))
        }
        if(Input.isKeyDown(Keys.D)){
            parent.position = parent.position.add(Vector2.right.multiply(speed*UpdateThread.deltatime))
        }
        if(Input.isKeyDown(Keys.DOWNARROW)){
            parent.scale = parent.scale.add(-0.001f);
        }
        if(Input.isKeyDown(Keys.UPARROW)){
            parent.scale = parent.scale.add(0.001f);
        }
    }

}