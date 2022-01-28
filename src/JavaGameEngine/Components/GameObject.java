package JavaGameEngine.Components;

import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Components.Collider.Collider;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.msc.Vector2;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

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
