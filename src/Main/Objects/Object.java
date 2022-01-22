package Main.Objects;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import Main.Msc.*;

public class Object {

    private Sprite sprite;
    private Vector2 position;
    private Vector2 scale;
    private Vector2 direction = new Vector2(1,0);

    private String tag="untagged";

    private float angle=180;

    private int spriteCounter;
    private int currentSprite;
    private float radius = 10;

    private float timer = 30;

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Object(Vector2 position) {
        this.position = position;
        sprite = new Sprite();
        sprite.setPath("/spritesheet.png");
        sprite.loadSprites(new Vector2[]{new Vector2(0,0),new Vector2(0,1)});
        setScale(new Vector2(20,20));

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public float getRadius() {
        return radius* scale.getX();
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public Vector2 getSpritePosition(){
        float x = (getPosition().getX()-((sprite.getTILE_SIZE()/4)));
        float y = (getPosition().getY()-((sprite.getTILE_SIZE()/4)));

        return new Vector2(x,y);
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(int currentSprite) {
        this.currentSprite = currentSprite;
    }


    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public Sprite getSprite() {
        return sprite;
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

    }

    public void Die()
    {

    }

    public BufferedImage getAnimation()
    {

        int spritesLen = getSprite().getSprites().length;
        //System.out.println(spritesLen);
        //Adds until timer then 0
        spriteCounter = (spriteCounter+1>=timer) ? 0 :spriteCounter+1;
        //Sets the current sprite index
        currentSprite = (currentSprite+1>=spritesLen&&spriteCounter>=timer-1) ?0:(spriteCounter>=timer-1)? (currentSprite+1): currentSprite;
        //Sprite.resize(getSprite().getSpriteImage(),new Vector2(100,100));
        getSprite().setSpriteImage(getSprite().getSprites()[currentSprite]);

        return (getSprite().rotate(angle));
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
}

