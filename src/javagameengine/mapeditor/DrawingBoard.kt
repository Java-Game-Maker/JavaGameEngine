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
        g!!.drawRect(spritePosition.x.toInt(), spritePosition.y.toInt(), scale.x.toInt(), scale.y.toInt());
    }
}

class DrawingBoard : Scene(){

    var size : Vector2 = Vector2(50f,50f);

    init {
        components.add(GameObject())
        UpdateThread.camera.addChild(CameraMovement(4f))
    }

    override fun paintComponents(g: Graphics?) {
        super.paintComponents(g)
        for(x in 10 downTo 0){
            for(y in 10 downTo 0){
                g!!.drawRect(x*size.x.toInt(),y*size.y.toInt(),size.x.toInt(),size.y.toInt())
            }
        }
    }
    override fun update() {
        super.update()

    }

}