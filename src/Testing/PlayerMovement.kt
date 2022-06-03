package Testing

import javagameengine.backend.UpdateThread
import javagameengine.backend.input.Input
import javagameengine.backend.input.Keys
import javagameengine.components.Component
import javagameengine.components.physics.PhysicsBody
import javagameengine.components.sprites.Sprite
import javagameengine.msc.Vector2

class PlayerMovement : Component() {


    override fun update() {
        super.update()
        try {
            var sprite:Sprite = parent.getChildren()[0] as Sprite
            var physicsBody: PhysicsBody = parent.getChildren()[1] as PhysicsBody
            var parent : Player = parent as Player;
            if (Input.isKeyDown(Keys.D)) {
                parent.movePosition(parent.position.add(Vector2.right.multiply(1.2f)))
                parent.sprite.animationIndex = 0
            } else if (Input.isKeyDown(Keys.A)) {
                parent.movePosition(parent.position.add(Vector2.left.multiply(1.2f)))
                parent.sprite.animationIndex = 1

            } else {
                sprite.animationIndex = 2
            }
            if (Input.isKeyPressed(Keys.SPACE)) {
                physicsBody.addForce(Vector2.up, 120f)
            }
            UpdateThread.camera.position = parent.position.subtract(Main.getWindowSize().devide(2f))
        }
        catch (e: Exception){
            e.printStackTrace()
        }

    }

}