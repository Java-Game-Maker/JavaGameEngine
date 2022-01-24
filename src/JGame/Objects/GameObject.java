package JGame.Objects;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import JGame.Display.CalcThread;
import JGame.Display.GameWorld;
import JGame.Msc.*;
import JGame.Objects.Components.Animation;
import JGame.Objects.Components.Collision.Collider;
import JGame.Objects.Components.Collision.SquareCollider;
import JGame.Objects.Components.Component;
import JGame.Objects.Components.Physics.PhysicsBody;

public class GameObject {

    private Sprite sprite;
    private Vector2 position=new Vector2(200,200);
    private Vector2 scale=new Vector2(100,100);
    private Vector2 direction = new Vector2(0,0);
    private int id;

    private boolean isColliding = false;

    private String tag="untagged";

    private float angle=180;

    ArrayList<Component> components = new ArrayList<>();
    private java.lang.Object parent;

    public GameObject(Vector2 position) {
        this.position = position;
        setScale(new Vector2(20,20));

        sprite = new Sprite();
    }
    public GameObject(Vector2 position, String path) {
        this.position = position;
        setScale(new Vector2(20,20));
        sprite = new Sprite();

    }
    public GameObject() {
        sprite = new Sprite();
        setScale(new Vector2(100,100));

    }



    public boolean isColliding() {
        return isColliding;
    }

    public void setColliding(boolean colliding) {
        isColliding = colliding;
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

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public Vector2 getSpritePosition(){
        float x = (getPosition().getX()-((getScale().getX()/2)));
        float y = (getPosition().getY()-((getScale().getY()/2)));

        return new Vector2(x,y);
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
        if(getComponent(new Animation())!=null)
            getComponent(new Animation()).setScale(scale);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position)
    {

        this.position = position;
    }

    /**
     * check if the new position will collide otherwise we set the new position
     * and return the direction we can move
     *
     * @param position the position to test
     * @return returns the directory we can move
     */

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
        if(getComponent(new PhysicsBody())==null) {
            this.position = position;
        }
        else {
            if(getComponents(new SquareCollider()).size()>0)
            {
                for(Collider c : getComponents(new SquareCollider())) {

                    if(!c.isTrigger()) {

                        Collider c2=null; //will be the other object we collide with (if)

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
        float b = position.getX()-toLookAt.getX();
        float a = position.getY()-toLookAt.getY();
        //a/b=tan v
        //System.out.println("a; "+a+"b: "+b);
        return(Math.toDegrees(Math.atan(a/b)));
    }
    /**
        Main update for objects
     **/
    public void Update()
    {
        UpdateComponents();
       // getInfoPanel().setLocation(new Point((int) (getPosition().getX()-100), (int) (getPosition().getY()-80)));
        //infoPanel.setData(this);
         //this.setHunger((this.getHunger()-0.01f));
    }
    /**
     * Updates all components inside the object*/
    public void UpdateComponents()
    {
        for(Component c : components)
        {
           // System.out.println(c.toString());
            if(c.isEnabled())
                c.Update();
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void onCollision(GameObject collision)
    {
        //setPosition(getPosition());
        //PhysicsBody b = getPhysicsbody();

    }

    public void onCollisionExit(GameObject collision)
    {

        setColliding(false);
    }


    public void onCollisionEnter(GameObject parent) {
        setColliding(true);

    }

    public void onTrigger(GameObject collision)
    {

    }


    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyDown(KeyEvent e) {
    }



    public BufferedImage Display()
    {
        Animation a = (Animation) getComponent(new Animation());
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

    public void Destroy()
    {
        CalcThread.delObjects.add(this);
    }
    /**
     * adds components to object
     * @param component component to add to you object
     * */
    public void addComponent(Component component)
    {
        component.setParent(this);
        component.setScale(getScale());
        component.setPosition(getPosition());
        components.add(component);
    }

    public <T extends Component> Component getComponent(T component)
    {
        for(Component c : components)
        {
            if(c.getClass().equals(component.getClass())){
                T xtemp = (T) c;
                return xtemp;
            }

        }
        return null;
    }
    public <T extends Component> ArrayList<T> getComponents(T component)
    {
        ArrayList<T> components = new ArrayList<>();
        for(Component c : this.components)
        {
            if(c.getClass().equals(component.getClass()))
                components.add((T) c);
        }

        return components;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

