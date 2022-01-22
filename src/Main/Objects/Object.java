package Main.Objects;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;

import Main.Msc.*;
import Main.Objects.Collision.Collider;

public class Object {

    private Sprite sprite;
    private Vector2 position;
    private Vector2 scale;
    private Vector2 direction = new Vector2(1,0);

    private String tag="untagged";

    private float angle=180;

    ArrayList<Collider> colliders = new ArrayList<>();

    private Animation animation;

    public Object(Vector2 position) {
        this.position = position;
        setScale(new Vector2(20,20));

        sprite = new Sprite();
        sprite.setPath("/spritesheet.png");
        sprite.loadSprite(new Vector2(0,0));
    }
    public Object(Vector2 position,String path) {
        this.position = position;
        setScale(new Vector2(20,20));
        sprite = new Sprite();
        sprite.setPath(path);
        sprite.loadSprite(new Vector2(0,0));

    }

    public void addCollider(Collider collider)
    {
        colliders.add(collider);
    }

    public void removeCollider(Collider collider)
    {
        colliders.remove(collider);
    }

    public ArrayList<Collider> getColliders() {
        return colliders;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
        animation.setScale(scale);
        animation.setAngle(angle);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
        if(animation!=null)
            getAnimation().setAngle(angle);
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
        if(animation!=null)
            getAnimation().setScale(scale);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /**
     Returns the angle between object position and vector given
     **/
    public double LookAt(Vector2 toLookAt)
    {
        float b = position.getX()-toLookAt.getX();
        float a = position.getY()-toLookAt.getY();
        //a/b=tan v
        //System.out.println("a; "+a+"b: "+b);
        return(Math.toDegrees(Math.atan(a/b)));
    }

    public void Update()
    {
       // getInfoPanel().setLocation(new Point((int) (getPosition().getX()-100), (int) (getPosition().getY()-80)));
        //infoPanel.setData(this);
         //this.setHunger((this.getHunger()-0.01f));
        for(Collider collider : colliders)
        {
            collider.Update();
        }
    }

    public void Die()
    {

    }

    public Sprite getSprite() {
        return sprite;
    }

    public void onCollision(Object collision)
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
        if(animation!=null)
        {
            return animation.getAnimation();
        }
        else
        {
            return sprite.getSpriteImage();
        }
    }
}

