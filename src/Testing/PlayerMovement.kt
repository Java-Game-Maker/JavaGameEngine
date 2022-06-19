package Testing

import javagameengine.JavaGameEngine
import javagameengine.backend.UpdateThread
import javagameengine.backend.input.Input
import javagameengine.backend.input.Keys
import javagameengine.components.Component
import javagameengine.components.physics.PhysicsBody
import javagameengine.components.sprites.Sprite
import javagameengine.msc.Debug
import javagameengine.msc.Vector2

class PlayerMovement : Component() {

    var offset = Vector2.zero;
    override fun update() {
        super.update()

        if(Input.isKeyDown(Keys.DOWNARROW)){
            Main.getScene().camera.scale= Main.getScene().camera.scale?.add(0.01F);
        }
        if(Input.isKeyDown(Keys.UPARROW)){
            Main.getScene().camera.scale=Main.getScene().camera.scale?.subtract(Vector2(0.01f,0.01f));
        }

        try {
            var sprite:Sprite = parent.getChildren()[0] as Sprite
            var physicsBody: PhysicsBody = parent.getChildren()[1] as PhysicsBody
            var parent : Player = parent as Player;
            if (Input.isKeyDown(Keys.D)) {
                parent.movePosition(parent.position.add(Vector2.right.multiply(3f*UpdateThread.deltatime)))
                parent.sprite.animationIndex = 0
            }
            else if (Input.isKeyDown(Keys.A)) {
                parent.movePosition(parent.position.add(Vector2.left.multiply(3f*UpdateThread.deltatime)))
                parent.sprite.animationIndex = 1

            } else {
                sprite.animationIndex = 2
            }
            if (Input.isKeyPressed(Keys.SPACE)) {
                physicsBody.addForce(Vector2.up, 120f*UpdateThread.deltatime)
            }
            Main.getScene().camera.position = Main.getScene().camera.position.add(parent.position.subtract(Main.getWindowSize().devide(2)))
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

}