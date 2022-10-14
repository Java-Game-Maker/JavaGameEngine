package javagameengine.components;

import javagameengine.JavaGameEngine;
import javagameengine.msc.Vector2;

public class Camera extends Component{


    @Override
    public void start() {
        super.start();
        setPosition(new Vector2(JavaGameEngine.getWindowSize().getX()/2,JavaGameEngine.getWindowSize().getY()/2));
    }

    @Override
    public Vector2 getScale() {
        return scale;
    }
}
