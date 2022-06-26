package javagameengine.components;
import Testing.Main;
import javagameengine.backend.UpdateThread;
import javagameengine.components.colliders.Collider;
import javagameengine.components.colliders.SquareCollider;
import javagameengine.components.physics.PhysicsBody;
import javagameengine.components.sprites.Sprite;
import javagameengine.JavaGameEngine;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;


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
        if(getPosition().getDistance(Main.getScene().getCamera().getPosition())<5000){
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

    @Override
    public void onCollision(Component c) {
        super.onCollision(c);
   //     Debug.log("Collision with "+c.getTag());
    }

    public ArrayList<SquareCollider> myColliders(){
        ArrayList<SquareCollider> myColliders = new ArrayList<>();
        for(Component child : getChildren()){
            if(child instanceof SquareCollider){
                myColliders.add((SquareCollider) child);
            }
            else if(child instanceof GameObject){
                myColliders.addAll(((GameObject)child).myColliders());
            }
        }
        return myColliders;
    }

    public ArrayList<SquareCollider> getWorldColliders(){
        ArrayList<SquareCollider> worldColliders = new ArrayList<>();
        for(Component parent : Main.getScene().components){
            if(parent!=this){
                for(Component child : parent.getChildren()){

                    if(child instanceof SquareCollider){
                        worldColliders.add((SquareCollider) child);
                    }
                    else if(child instanceof GameObject){
                        worldColliders.addAll(((GameObject)child).myColliders());
                    }
                }
            }
        }


        return worldColliders;
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

        /*

            We create a new collider and move it first in the x axis and check if it collides
            and if so we remove the x movment from the new position
             then we create a new collider and move it in the y axios and check if it collides
             and if so we remove the y movment


         */
        Vector2 dir = position.subtract(getPosition());

        ArrayList<SquareCollider> myColliders = myColliders();

        ArrayList<SquareCollider> worldColliders = getWorldColliders();

        //check collison
        for(SquareCollider myCollider : myColliders){
            for(SquareCollider worldCollider : worldColliders){

                SquareCollider xMyCollider = myCollider.copy();
                xMyCollider.setPosition(myCollider.getPosition().add(dir.removeX()));
                xMyCollider.shape.x = (int) myCollider.getPosition().add(dir.removeX()).getX();
                xMyCollider.shape.y = (int) myCollider.getPosition().add(dir.removeX()).getY();
                xMyCollider.shape.width = (int) myCollider.getScale().getX();
                xMyCollider.shape.height = (int) myCollider.getScale().getY();

                xMyCollider.setParent(this);
                Component collisionComponent = SquareCollider.hasCollided(xMyCollider,worldCollider);



                if(collisionComponent!=null){

                    this.onCollision(collisionComponent);
                    collisionComponent.onCollision(this);

                    dir = (dir.removeY());
                }

                SquareCollider yMyCollider = myCollider.copy();
                yMyCollider.setPosition(getPosition().add(dir.removeY()));
                yMyCollider.shape.x = (int) myCollider.getPosition().add(dir.removeY()).getX();
                yMyCollider.shape.y = (int) myCollider.getPosition().add(dir.removeY()).getY();
                yMyCollider.shape.width = (int) myCollider.getScale().getX();
                yMyCollider.shape.height = (int) myCollider.getScale().getY();
                yMyCollider.setParent(this);

                collisionComponent = SquareCollider.hasCollided(yMyCollider,worldCollider);

                if(collisionComponent!=null){
                    this.onCollision(collisionComponent);
                    collisionComponent.onCollision(this);

                    dir = (dir.removeX());
                }

            }
        }

        setPosition(getPosition().add(dir));

        return dir;
    }

}
