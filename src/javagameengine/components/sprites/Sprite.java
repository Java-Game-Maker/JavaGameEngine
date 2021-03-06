package javagameengine.components.sprites;

import javagameengine.backend.UpdateThread;
import javagameengine.components.Component;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Sprite extends Component {

    ArrayList<BufferedImage[]> animations1 = new ArrayList<>();
    public ArrayList<BufferedImage[]> animations = new ArrayList<>();
    BufferedImage spriteSheet;
    public int animationIndex = 0;
    private float spriteCounter;
    float timer = 10;
    int currentSprite = 0;
    float angle;

    public void setAngle(float angle) {
        ArrayList<BufferedImage[]> newAnimations = new ArrayList<>();
        for(BufferedImage[] animation : animations1){
            BufferedImage[] newAnimation = new BufferedImage[animation.length];
            int i = 0;
            for(BufferedImage image : animation){
                double rotationRequired = Math.toRadians (angle);
                double locationX = image.getWidth() / 2;
                double locationY = image.getHeight() / 2;
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);

                newAnimation[i] = op.filter(image, null);
                i+=1;
            }
            newAnimations.add(newAnimation);
        }
        animations = newAnimations;

    }

    public float getAngle() {
        return angle;
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

    public BufferedImage getAnimation(){
        int spritesLen = animations.get(animationIndex).length;
        //Adds until timer then 0
        spriteCounter = (spriteCounter+1>=timer) ? 0 :spriteCounter+1* UpdateThread.deltatime;
        //Sets the current sprite index (the image that will be displayed)
        currentSprite = (((currentSprite + 1) >= spritesLen) && (spriteCounter >= (timer - 1))) ?
                0 :
                ((spriteCounter >= (timer - 1)) ?
                        (currentSprite + 1) :
                        currentSprite);

        BufferedImage sprite = animations.get(animationIndex)[(currentSprite)];
        float angle = getRotation().getAngle();
        //Sprite.resize(rotate(angle,sprite),getScale())
        return (sprite);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage((Image) getAnimation(), (int) ((int) getSpritePosition().getX()+getScale().getX()/2), (int) ((int) getSpritePosition().getY()+getScale().getY()/2), (int) getScale().getX(), (int) getScale().getY(),null);
    }

    public static BufferedImage resize(BufferedImage img, Vector2 scale) {
        Image tmp = img.getScaledInstance((int) scale.getX(), (int) scale.getY(), Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage((int) scale.getX(), (int) scale.getY(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public BufferedImage rotate(double angle,BufferedImage image) {
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
        g2.drawImage(image, null, 0, 0);

        // Return rotated buffer image
        return((newImage));
    }
}
