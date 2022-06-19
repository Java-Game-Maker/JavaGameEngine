package javagameengine.components

import javagameengine.msc.Vector2

class Camera : Component() {

    init {
        tag = "Camera"
        scale = Vector2(1f,1f);
        position = Vector2(0f,0f);
    }

    override fun setPosition(position: Vector2?) {
        this.position = position
    }

    override fun getScale(): Vector2? {
        return scale
    }

}