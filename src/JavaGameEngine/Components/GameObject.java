package JavaGameEngine.Components;
import JavaGameEngine.Components.Collider.Collider;
import java.awt.*;

public class GameObject extends Component{

    /**
     * this is the method that draws the GameObject
     */
    @Override
    public void draw(Graphics g){
        g.fillRect((int) getPosition().getX(), (int) getPosition().getY(), (int) getScale().getX(), (int) getScale().getY());
        for(Component c : components){
            c.draw(g);
        }
    }
}
