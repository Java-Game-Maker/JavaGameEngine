package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.msc.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;


public class Sprite extends Component implements Serializable {

    transient public ArrayList<BufferedImage[]> animations1 = new ArrayList<>();
    transient public ArrayList<BufferedImage[]> animations = new ArrayList<>();
    transient BufferedImage spriteSheet;
    public int animationIndex = 0;
    private float spriteCounter;
    float timer = 10;
    int currentSprite = 0;
    boolean inverted = false;
    public LinkedList<Rectangle[]> tiles = new LinkedList<>();
    public String spriteSheetString = "";

    public Sprite(){}

    public Sprite(String sprite){
        loadAnimation(new String[]{sprite});
    }

    public boolean isInverted() {
        return inverted;
    }

    public Sprite(Sprite c){
        super();
        animations = c.animations;
        animationIndex = c.animationIndex;
        angle = c.angle;
        timer = c.timer;
    }

    public void setInverted(boolean inverted) {
        if(inverted){

        }
        this.inverted = inverted;
    }

    public ArrayList<BufferedImage[]> getAnimations1() {
        return animations1;
    }

    public void setAnimations1(ArrayList<BufferedImage[]> animations1) {
        this.animations1 = animations1;
    }

    public ArrayList<BufferedImage[]> getAnimations() {
        return animations;
    }

    public void setAnimations(ArrayList<BufferedImage[]> animations) {
        this.animations = animations;
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public void setAnimationIndex(int animationIndex) {
        this.animationIndex = animationIndex;
    }

    public float getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(float spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(int currentSprite) {
        this.currentSprite = currentSprite;
    }

    public float getTimer() {
        return timer;
    }
    public void setTimer(float timer) {
        this.timer = timer;
    }

    /**
     * This function loads in sprites from a sprite sheet
     * @param tiles array of rectangles that is the sprites
     * @param spriteSheetPath the path to the sprites
     */
    public void loadAnimation(Rectangle[] tiles,String spriteSheetPath){
        BufferedImage sprite = null;
        spriteSheetString = spriteSheetPath;
        this.tiles.add(tiles);

        try {
            InputStream in = this.getClass().getResourceAsStream(spriteSheetPath);
            sprite = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        spriteSheet = sprite;

        BufferedImage[] animation = new BufferedImage[tiles.length];
        int i = 0;
        for(Rectangle r : tiles){
            BufferedImage animationSprite = (spriteSheet.getSubimage((int) (r.getX()), (int) (r.getY()), (int) r.getWidth(), (int) r.getHeight()));
            animation[i]=(animationSprite);
            i++;
        }
        animations.add(animation);
        animations1.add(animation);
    }
    /**
     * This function loads in sprites from images
     * @param paths array of paths to images to be loaded
     */
    public void loadAnimation(String[] paths){
        BufferedImage[] images = new BufferedImage[paths.length];
        for(int i = 0;i< paths.length;i++){
            String path = paths[i];
            //read image of file
            BufferedImage sprite = null;
            try {
                InputStream in = this.getClass().getResourceAsStream(path);
                sprite = ImageIO.read(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //add image to temp animation.an
            images[i] = sprite;
        }
        animations.add(images);
        animations1.add(images);

    }
    /**
     * This function loads in all images in side a folder
     * @param folder path to folder where to load in all sprites
     * */
    public void loadAnimation(String folder){
        /*
            how with jar?
         */
    }

    public BufferedImage getAnimation(){
        int spritesLen = animations.get(animationIndex).length;
        //Adds until timer then 0
        spriteCounter = (spriteCounter+1 >=timer) ? 0 : (float) (spriteCounter + 1 );
        //Sets the current sprite index (the image that will be displayed)
        currentSprite = (((currentSprite + 1) >= spritesLen) && (spriteCounter >= (timer- 1))) ?
                0 :
                ((spriteCounter >= (timer - 1)) ?
                        (currentSprite + 1) :
                        currentSprite);
        if(currentSprite == animations.get(animationIndex).length-1) animationDone();

        //Sprite.resize(rotate(angle,sprite),getScale())
        return (animations.get(animationIndex)[(currentSprite)]);
    }

    @Override
    public Vector2 getScale() {
        return scale;
    }

    @Override
    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    @Override
    public void setPosition(Vector2 position) {
        //.subtract(getScale().divide(2))
        super.setPosition(position);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        if(visible){
            AffineTransform backup = g.getTransform();
            AffineTransform trans = new AffineTransform();
            trans.rotate( Math.toRadians(this.angle), getPosition().getX() + pivot.getX(), getPosition().getY() + pivot.getY()); // the points to rotate around (the center in my example, your left side for your problem)
            g.transform( trans );
            g.drawImage((Image) getAnimation(),
                    (int) (getPosition().getX()-getScale().divide(2).getX()),
                    (int) (getPosition().getY()-getScale().divide(2).getY()),
                    (int) getScale().getX(),
                    (int) getScale().getY(),
                    null);  // the actual location of the sprite

            g.setTransform( backup ); // restore previous transform

            //  g.drawImage(getAnimation(), ((int) getPosition().getX()), ((int) getPosition().getY()), (int) getScale().getX()+1, (int) getScale().getY(),null);
        }
    }
    public void animationDone(){

    }
    public static BufferedImage resize(BufferedImage img, Vector2 scale) {
        Image tmp = img.getScaledInstance((int) scale.getX(), (int) scale.getY(), Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage((int) scale.getX(), (int) scale.getY(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    private Vector2 pivot = Vector2.zero;
    @Override
    public void rotate(float angle, Vector2 pivot) {
        this.angle += angle ;
        this.pivot = pivot;
    }

    public BufferedImage rotate(double angle, BufferedImage image) {
        // Getting Dimensions of image
        int width = image.getWidth();
        int height = image.getHeight();
        // Creating a new buffered image
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getWidth(), image.getType());

        // creating Graphics in buffered image
        Graphics2D g2 = newImage.createGraphics();

        // Rotating image by degrees using toradians()
        // method
        // and setting new dimension t it
        g2.rotate(Math.toRadians(angle), width / 2,
                height / 2);

        Color c=new Color(1f,0f,0f,.5f );
        g2.setColor(c);

        g2.drawImage(image, null, 0, 0);
        // Return rotated buffer image
        return((newImage));
    }

    public void setTiles(LinkedList<Rectangle[]> tiles) {
        this.tiles = tiles;
    }

    public LinkedList<Rectangle[]> getTiles() {
        return tiles;
    }

    public String getSpriteSheetString() {
        return spriteSheetString;
    }

    public void setSpriteSheetString(String spriteSheetString) {
        this.spriteSheetString = spriteSheetString;
    }

    public Sprite clone() {
        Sprite c = new Sprite();
        c.setPosition(getPosition());
        c.setScale(getScale());
        c.setTag(getTag());
        c.setChildren(getChildren());
        c.setParentOffset(getParentOffset());
        c.setParent(getParent());
        c.setLocalVertices(getLocalVertices());
        c.setVisible(isVisible());
        c.setLayer(layer);
        c.setPrevPosition(getPrevPosition());
        c.setAnimations(animations);
        c.setTimer(timer);
        c.setAngle(angle);
        c.setAnimationIndex(animationIndex);
        c.setInverted(inverted);
        c.setTiles(tiles);
        c.setSpriteSheetString(spriteSheetString);

        return c;
    }

}
