package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * The sprite class is a component that is rendering an animation specified in loadAnimation
 * The sprite has a list of animation which are a list of images
 */
public class Sprite extends Component {

    ArrayList<BufferedImage[]> animations1 = new ArrayList<>();
    public ArrayList<BufferedImage[]> animations = new ArrayList<>();
    BufferedImage spriteSheet;
    public int animationIndex = 0;
    private float spriteCounter;
    float timer = 10;
    int currentSprite = 0;
    boolean inverted = false;

    public Sprite(){}

    public Sprite(String sprite){
        loadAnimation(new String[]{sprite});
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        if(inverted){

        }
        this.inverted = inverted;
    }

    public float getTimer() {
        return timer;
    }

    /**
     *
     * @param timer how long to next animation change
     */
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
            //add image to temp animation
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
    boolean animationsDone = false;
    /**
     *
     * @return the current image in the selected animation
     */
    public BufferedImage getAnimation(){
        int spritesLen = animations.get(animationIndex).length;
        //Adds until timer then 0
        spriteCounter = (spriteCounter + 1 >= timer) ? 0 : (spriteCounter + 1);
        //Sets the current sprite index (the image that will be displayed)
        currentSprite = (((currentSprite + 1) >= spritesLen) && (spriteCounter >= (timer- 1))) ?
                0 :
                ((spriteCounter >= (timer - 1)) ?
                        (currentSprite + 1) :
                        currentSprite);
        if(currentSprite == animations.get(animationIndex).length-1){
            if(!animationsDone) animationDone();

            animationsDone = true;
        }
        else{
            animationsDone = false;
        }

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
    public void render(Graphics2D g) {
        if(visible){
            super.render(g);
            AffineTransform backup = g.getTransform();
            AffineTransform trans = new AffineTransform();
            trans.rotate( Math.toRadians(this.angle), getPosition().getX() + pivot.getX(), getPosition().getY() + pivot.getY()); // the points to rotate around (the center in my example, your left side for your problem)
            g.transform( trans );
            g.drawImage(getAnimation(),
                    (int) (getPosition().getX() - getScale().divide(2).getX()*(isInverted()?-1:1)),
                    (int) (getPosition().getY() - getScale().divide(2).getY()),
                    (int) getScale().getX()*(isInverted()?-1:1),
                    (int) getScale().getY(),
                    null);  // the actual location of the sprite

            g.setTransform( backup ); // restore previous transform
            //  g.drawImage(getAnimation(), ((int) getPosition().getX()), ((int) getPosition().getY()), (int) getScale().getX()+1, (int) getScale().getY(),null);
        }
    }

    /**
     * triggers then a animation loop is done playing
     */
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
        this.angle += angle;
        this.pivot = pivot;
        for(Component child : children){
            child.rotate((float) (angle ),child.parentOffset.multiply(-1));
        }
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
}
