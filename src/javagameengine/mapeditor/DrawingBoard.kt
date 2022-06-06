package javagameengine.mapeditor

import javagameengine.JavaGameEngine
import javagameengine.backend.GameWorld
import javagameengine.backend.Scene
import javagameengine.backend.UpdateThread
import javagameengine.backend.input.Input
import javagameengine.backend.input.Keys
import javagameengine.components.Component
import javagameengine.components.GameObject
import javagameengine.msc.CameraMovement
import javagameengine.msc.Debug
import javagameengine.msc.Vector2
import java.awt.Graphics

class Rect(pos : Vector2 = Vector2.zero) : Component() {
    init {
        this.position = pos;
    }

    override fun draw(g: Graphics?) {
        g!!.drawRect(position.x.toInt(), position.y.toInt(), scale.x.toInt(), scale.y.toInt());
    }
}

class DrawingBoard : Scene(){

    var size : Vector2 = Vector2(50f,50f);

    init {
        for(x in 100 downTo 0 step 1){
            for(y in 10 downTo 0 step 1){
                components.add(Rect(Vector2(x.toFloat(), y.toFloat()).multiply(size)))
            }
        }
        UpdateThread.camera.addChild(CameraMovement(4f))
    }

    override fun update() {
        super.update()
        if(Input.isMouseDown(Keys.LEFTCLICK)){
            for(c in components){
                if(c.isMouseInside){
                    Debug.log(c.position)
                    var g = GameObject();
                    g.position = c.position
                    g.scale = c.scale
                    c.instantiate(g)
                }
            }

        }
    }

}