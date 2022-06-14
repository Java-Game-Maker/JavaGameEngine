package Testing

import javagameengine.backend.Scene
import javagameengine.backend.input.Input
import javagameengine.components.GameObject
import javagameengine.msc.Debug
import javagameengine.msc.Vector2
import java.awt.Color
import java.util.logging.Level

class Level4 : Scene(){

    var selectedObject : Test? = Test(color = Color.BLUE, level4 = this);

    init {

        Debug.showWhere = true

        var e = Test(color = Color.BLUE, level4 = this);
        e.layer = 0;
        components.add(e)
        var colors = listOf<Color>(Color.black,Color.CYAN, Color.GREEN,Color.red,Color.yellow)
        for(i in 1..3){
            components.add(Test(Vector2(i*50F, 50F),color= colors[i],level4=this))
        }

    }


    open fun setSelected(selected:Test){
        selectedObject!!.layer = 0;
        selected.layer = 10;
        selectedObject = selected
    }

}

class Test(pos : Vector2= Vector2(0f,0f),color: Color=Color.darkGray,level4 : Level4) : GameObject(){

    var main : Level4? = null;
    init {
        this.position = pos
        this.color = color
        main = level4;
    }

    override fun update() {
        super.update()
        if(isMouseInside && Input.isMouseDown()){
            main!!.setSelected(this);
            position = Input.getMousePosition()
        }
    }

    override fun onMouseEntered() {
        super.onMouseEntered()
        setScale(getScale().multiply(1.05f))
    }

    override fun onMouseExit() {
        super.onMouseEntered()
        setScale(getScale().devide(1.05f))
    }
}

