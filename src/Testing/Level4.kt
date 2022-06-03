package Testing

import javagameengine.backend.Scene
import javagameengine.msc.Vector2

class Level4 : Scene(){

    init {
        var player = Player(Vector2(400F,400F))
        components.add(player)
    }

}