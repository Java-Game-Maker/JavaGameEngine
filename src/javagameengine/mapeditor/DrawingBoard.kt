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
    }

    override fun draw(g: Graphics?) {
        super.draw(g)
        g!!.drawRect(spritePosition.x.toInt(), spritePosition.y.toInt(), scale.x.toInt(), scale.y.toInt());
    }
}

class DrawingBoard : Scene(){

    var size : Vector2 = Vector2(50f,50f);

    init {
        for(x in 1 downTo 0 step 1){
            for(y in 1 downTo 0 step 1){
                var rec = Rect();
                rec.position = Vector2(x.toFloat(), y.toFloat()).multiply(size.multiply(2f));
                components.add(rec)
            }
        }
        components.add(GameObject())
        UpdateThread.camera.addChild(CameraMovement(4f))

    }

    override fun update() {
        super.update()
        if(Input.isMouseDown(Keys.LEFTCLICK)){
            for(c in components){
                if(c.isMouseInside){
                    var g = GameObject();
                    Debug.log("click "+c.position)
                    g.position = c.position
                    c.instantiate(g)
                }
            }

        }
        for(c in components){
            if(c.tag == "my"){
            }
        }
    }

}