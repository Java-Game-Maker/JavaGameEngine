package Testing

import javagameengine.components.Component
import javagameengine.components.GameObject
import javagameengine.components.colliders.SquareCollider
import javagameengine.components.sprites.Sprite
import javagameengine.msc.Debug
import javagameengine.msc.Vector2
import java.awt.Rectangle

class Goal(pos : Vector2 = Vector2(0f,0f)) : GameObject() {

    var sprite = Sprite();
    var squareCollider = SquareCollider();

    init {
        setPosition(pos);
        tag = "Coin";

        squareCollider.localPosition = Vector2(0f,20f);
        squareCollider.isTrigger = true;
        sprite.localScale = Vector2(50f,00f);
        addChild(squareCollider)

        var sprites = Array(1){"/goal.png"}
        sprite.localPosition = Vector2(0f,20f);
        sprite.loadAnimation(sprites)
        addChild(sprite);
    }
    override fun onTrigger(c: Component?) {
        super.onTrigger(c)
    }


}