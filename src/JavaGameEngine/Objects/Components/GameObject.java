package JavaGameEngine.Objects.Components;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import JavaGameEngine.Msc.*;
import JavaGameEngine.Objects.Components.Collision.Collider;
import JavaGameEngine.Objects.Components.Collision.SquareCollider;
import JavaGameEngine.Objects.Components.Physics.PhysicsBody;
import JavaGameEngine.Objects.Components.Visual.Animation;
import JavaGameEngine.Objects.Components.Visual.Shape_idk;
import JavaGameEngine.Objects.Components.Visual.Sprite;

public class GameObject extends Component {

    private Sprite sprite;

    private boolean isColliding = false;

    private String tag="untagged";

    private float angle=180;

    ArrayList<Component> components = new ArrayList<>();
    private java.lang.Object parent;

    public GameObject(Vector2 position) {
        setPosition(position);
        setScale(new Vector2(20,20));
        setShape(new Shape_idk(Component.square,this));
        getShape().setParent(this);
        sprite = new Sprite();
    }
    public GameObject(Vector2 position, String path) {
        setPosition(position);
        setScale(new Vector2(20,20));
        sprite = new Sprite();
        setShape(new Shape_idk(Component.square,this));
        getShape().setParent(this);

    }
    public GameObject() {
        sprite = new Sprite();
        setScale(new Vector2(100,100));
        setShape(new Shape_idk(Component.square,this));
        getShape().setParent(this);

    }

    public boolean isColliding() {
        return isColliding;
    }

    public void setColliding(boolean colliding) {
        isColliding = colliding;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public Vector2 getSpritePosition(){
        float x = (getPosition().getX()-((getScale().getX()/2)));
        float y = (getPosition().getY()-((getScale().getY()/2)));

        return new Vector2(x,y);
    }

    @Override
    public void setScale(Vector2 scale) {
        super.setScale(scale);

        if(getChild(new Animation())!=null)
            getChild(new Animation()).setScale(scale);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
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

            if(getChildren(new SquareCollider()).size()>0) {
                for(Collider c : getChildren(new SquareCollider())) {

                    if(!c.isTrigger()) {
                        Component c2=null; //will be the other object we collide with (if)

                        //checks if we can move the object on the y-axis
                        SquareCollider xcolider = (SquareCollider) c.copy();
                        xcolider.setPosition(getPosition().add(dir.removeX()));

                        if((SquareCollider.isCollision(xcolider,c,ObjectHandler.getObjects()))!=null) {
                            c2= SquareCollider.isCollision(xcolider,c,ObjectHandler.getObjects());
                            dir=(dir.removeY());
                        }

                        //checks if we can move the object on the x-axis
                        SquareCollider ycolider = (SquareCollider) c.copy();
                        ycolider.setPosition(getPosition().add(dir.removeY()));

                        if((SquareCollider.isCollision(ycolider,c,ObjectHandler.getObjects()))!=null) {
                            c2= SquareCollider.isCollision(ycolider,c,ObjectHandler.getObjects());
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
    /**
     Returns the angle between object position and vector given
     @param toLookAt the vector to look at
     **/
    public double LookAt(Vector2 toLookAt)
    {
        float b = getPosition().getX()-toLookAt.getX();
        float a = getPosition().getY()-toLookAt.getY();
        //a/b=tan v
        //System.out.println("a; "+a+"b: "+b);
        return(Math.toDegrees(Math.atan(a/b)));
    }
    /**
        Main update for objects
     **/
    @Override
    public void Update()
    {
        super.Update();

    }
    /**
     * Updates all components inside the object*/


    public Sprite getSprite() {
        return sprite;
    }

    public BufferedImage Display()
    {
        Animation a = (Animation) getChild(new Animation());
        if(a!=null)
        {
            return a.getAnimation();
        }
        else if (sprite.getSpriteImage()!=null)
        {
            return sprite.getSpriteImage();
        }
        else return null;
    }

    /**
     * adds components to object
     * @param component component to add to you object
     * */
    public void addComponent(Component component)
    {
        component.setParent(this);

        component.setPosition(getPosition());
        addChild(component);
    }




}

