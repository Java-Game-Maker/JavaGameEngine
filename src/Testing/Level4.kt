package Testing

import javagameengine.backend.Scene
import javagameengine.msc.Debug
import javagameengine.msc.Vector2

class Level4 : Scene(){

    init {
        Debug.showWhere = true

        //UpdateThread.camera.addChild(CameraMovement(4f))

        var player = Player(Vector2(0f,0f));
        components.add(player)
        components.add(Ground(Vector2(0f,100f)))

    }

}

