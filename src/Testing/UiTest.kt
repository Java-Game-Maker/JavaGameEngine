package Testing

import javagameengine.backend.Scene
import javagameengine.backend.input.Input
import javagameengine.backend.input.Keys
import javagameengine.components.Camera
import javagameengine.components.GameObject
import javagameengine.msc.CameraMovement
import javagameengine.msc.Debug
import javagameengine.msc.Vector2
import javagameengine.ui.Button
import javagameengine.ui.HorizontalLayout
import javagameengine.ui.Panel
import javagameengine.ui.Text
import javagameengine.ui.UiElement
import javagameengine.ui.VerticalLayout
import java.awt.Color
import javax.swing.DebugGraphics
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class UiTest : Scene() {

    init {
        var p = Panel()
        p.isFixed = false
        p.layout = HorizontalLayout()

        p.addChild(Button())
        p.addChild(Button())

        p.addChild(Button())

        var p2 = Panel()
        p2.color = Color.GREEN
        p2.layout = VerticalLayout()
        p2.addChild(Button())
        p2.addChild(Button())
        p2.addChild(Button())

        p.addChild(p2)
        var b = UiElement();
        b.scale = Vector2(300f,120f)
        //p.addChild(b)

        add(p)

        camera.addChild(CameraMovement(5f))
    }

}