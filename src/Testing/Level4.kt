package Testing

import javagameengine.backend.GameWorld
import javagameengine.backend.Scene
import javagameengine.components.GameObject
import javagameengine.components.colliders.SquareCollider
import javagameengine.msc.Vector2

class Level4 : Scene(){

    init {
        for (x in 100 downTo 0 step 1) {
            for (y in 100 downTo 0 step 1) {
                var tile = GameObject();


                tile.position = Vector2(x*100f,y*100f);
                var s = SquareCollider()
                s.isVisible = true;
                tile.addChild(s)

                components.add(tile)
            }
        }
        var player = Player(Vector2(50f,-120f));
        components.add(player)

    }

}

