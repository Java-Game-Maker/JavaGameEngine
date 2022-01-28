package JavaGameEngine.Components;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject extends Component{

    /**
     * this is the method that draws the GameObject
     *
     */
    public void draw(Graphics g){
        g.drawRect((int) getPosition().getX(), (int) getPosition().getY(), (int) getScale().getX(), (int) getScale().getY());
        for(Component c : getChildren(new GameObject())){
            ((GameObject)c).draw(g);
        }
    }
}
