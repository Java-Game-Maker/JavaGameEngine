package JavaGameEngine.Components;
import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Components.Collider.Collider;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.Components.Sprite.Sprite;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.awt.*;
import java.util.LinkedList;

public class GameObject extends Component{

    private LinkedList<String> ignoreTags = new LinkedList<>();

    public void addIgnoreTags(String tag){
        ignoreTags.add(tag);
    }
    public void setIgnoreTags(LinkedList<String> tags){
        this.ignoreTags = tags;
    }
    public LinkedList<String> getIgnoreTags(){
        return ignoreTags;
    }
    private Color color = Color.darkGray;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }


    /**
     * this is the method that draws the GameObject
     */
    @Override
    public void draw(Graphics g){
        //g.drawString(getPosition().toString(), (int) getSpritePosition().getX(), (int) (getSpritePosition().getY()-50));
       // g.drawString(getSpritePosition().toString(), (int) getSpritePosition().getX(), (int) (getSpritePosition().getY()-20));
        g.setColor(this.color);
        Sprite sprite = (Sprite) getChild(new Sprite());
        if(sprite!=null){
            //g.drawImage(sprite.getAnimation(),(int)sprite.getPosition().getX(),(int)sprite.getPosition().getY(),(int)sprite.getScale().getX(),(int)sprite.getScale().getY(),null);
        }
        else{
            g.fillRect((int) getSpritePosition().getX(), (int) getSpritePosition().getY(), (int) getSpriteScale().getX(), (int) getSpriteScale().getY());
        }

        drawChildren(g);
    }
    /**
     * check if the new position will collide otherwise we set the new position
     * and return the direction we can move
     *
     * @param position the position to test
     * @return returns the directory we can move
     */

    @Override
    public Vector2 movePosition(Vector2 position) {
        /*
        ---Description on what is happening---

        We create temp colliders to se if the next posistion will be a collistion
        We check the different coordinates, this is because if you collide on one
        side you should be able to move on the other
        |----|
        |    |
        |----|
    |----|
    | 1  |
    |----|
       |----|
       |    |
       |----|

        1 is the one we check
        in this case we can't move in the y-axis but we can move in the x

        |----|
        |    |
        |----|
    |----||----||----|
    |    ||  1 ||    |
    |----||----||----|
        |----|
        |    |
        |----|
        1 is the one we check
        in this case we can't move in the y-axis but neither in the x*/
        Vector2 dir = position.subtract(getPosition());
        if(getChild(new PhysicsBody())==null) {
            setPosition(position);
        }
        else {
            if(getChildren(new SquareCollider()).size()>0)
            {
                for(Collider c : getChildren(new SquareCollider())) {
                    if(!c.isTrigger()) {

                        Collider c2=null; //will be the other object we collide with (if)

                        //checks if we can move the object on the y-axis
                        SquareCollider xcolider = (SquareCollider) c.copy();
                        xcolider.setPosition(getPosition().add(dir.removeX()));
                        xcolider.setParent(this);

                        if((SquareCollider.isCollision(xcolider,c,ComponentHandler.getObjects()))!=null) {
                            c2= (Collider) SquareCollider.isCollision(xcolider,c,ComponentHandler.getObjects());
                            dir=(dir.removeY());
                        }

                        //checks if we can move the object on the x-axis
                        SquareCollider ycolider = (SquareCollider) c.copy();
                        ycolider.setPosition(getPosition().add(dir.removeY()));
                        ycolider.setParent(this);

                        if((SquareCollider.isCollision(ycolider,c,ComponentHandler.getObjects()))!=null) {
                            c2= (Collider) SquareCollider.isCollision(ycolider,c,ComponentHandler.getObjects());
                            dir=(dir.removeX());
                        }

                        c.collisionHandler(c2);

                        setPosition(getPosition().add(dir));
                    }
                    else setPosition(position);
                }
            }
            else
                setPosition(position);
        }
        return dir;
    }


}
