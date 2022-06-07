package javagameengine.components;
import javagameengine.backend.UpdateThread;
import javagameengine.components.colliders.Collider;
import javagameengine.components.colliders.SquareCollider;
import javagameengine.components.physics.PhysicsBody;
import javagameengine.components.sprites.Sprite;
import javagameengine.JavaGameEngine;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.util.LinkedList;


/**
 * Extend this to create GameObjects
 * GameObject has a render and the with the movePosition can check for collisons
 */

public class GameObject extends Component{

    public GameObject(){}
    public GameObject(Vector2 pos){this.position = pos;}
    private LinkedList<String> ignoreTags = new LinkedList<>();

    /**
     * The tags added here will the collider ignore
     * @param tag tagname to ignore in collision
     */
    public void addIgnoreTags(String tag){
        ignoreTags.add(tag);
    }

    /**
     * Set a list of tags that the cllider should ignore
     * @param tags list of tags to ignore
     */
    public void setIgnoreTags(LinkedList<String> tags){
        this.ignoreTags = tags;
    }

    /**
     *
     * @return list of tags the collider ignores
     */
    public LinkedList<String> getIgnoreTags(){
        return ignoreTags;
    }

    private Color color = Color.darkGray;

    /**
     * Set the color the gameobject will be render ind
     * @param color to render with
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *
     * @return the render color
     */
    public Color getColor() {
        return color;
    }


    /**
     * this is the method that draws the GameObject
     */
    @Override
    public void draw(Graphics g){
        if(getPosition().getDistance(UpdateThread.camera.getPosition())<5000){
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
    }
    /**
     * This method is used to setPostion but with collison. It will check if the gamobject can be set/moved tho the new positon
     * and if so it will move it there else it will not.
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
        LinkedList<Component> components = new LinkedList<>();
        for(Component ob : JavaGameEngine.getScene().components){
            if(getPosition().getDistance(ob.getPosition())<500){
                components.add(ob);
            }
        }
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

                        if((SquareCollider.isCollision(xcolider,c, components))!=null) {
                            c2= (Collider) SquareCollider.isCollision(xcolider,c,components);
                            dir=(dir.removeY());
                        }

                        //checks if we can move the object on the x-axis
                        SquareCollider ycolider = (SquareCollider) c.copy();
                        ycolider.setPosition(getPosition().add(dir.removeY()));
                        ycolider.setParent(this);

                        if((SquareCollider.isCollision(ycolider,c,components))!=null) {
                            c2= (Collider) SquareCollider.isCollision(ycolider,c,components);
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
