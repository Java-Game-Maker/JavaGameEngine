package javagameengine.mapeditor

import javagameengine.JavaGameEngine

class Main : JavaGameEngine() {

    init {
        setSelectedScene(DrawingBoard())
    }


}

fun main(){
    var m = Main();
    m.init()
    m.start()

}
