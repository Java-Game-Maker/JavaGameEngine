package JavaGameEngine.Components.Sprite;

import JavaGameEngine.Components.Component;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;
import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class Sprite extends Component {

    LinkedList<LinkedList<Rectangle>> animations1 = new LinkedList<>();
    LinkedList<LinkedList<BufferedImage>> animations = new LinkedList<>();
    BufferedImage spriteSheet;
    private int animationIndex = 0;
    private int spriteCounter;
    float timer = 10;
    int currentSprite = 0;

    /**
     * This function loads in sprites from a sprite sheet
     * @param tiles array of rectangles that is the sprites
     */
    public void loadAnimation(Rectangle[] tiles,String spriteSheetPath){
        BufferedImage sprite = null;

        try {
            InputStream in = this.getClass().getResourceAsStream(spriteSheetPath);
            sprite = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (spriteSheet == null) {
            spriteSheet = sprite;
        }
        LinkedList<BufferedImage> animation = new LinkedList<>();
        for(Rectangle r : tiles){
            BufferedImage animationSprite = (spriteSheet.getSubimage((int) (r.getX()), (int) (r.getY()), (int) r.getWidth(), (int) r.getHeight()));
            animation.add(animationSprite);
        }
        animations.add(animation);

    }
    /**
     * This function loads in sprites from images
     * @param paths array of paths to images to be loaded
     */
    public void loadAnimation(String[] paths){

    }
    /**
     * This function loads in all images in side a folder
     * @param folder path to folder where to load in all sprites
     */
    public void loadAnimation(String folder){

    }

    public BufferedImage getAnimation(){
        int spritesLen = animations.get(animationIndex).size();
        //Adds until timer then 0
        spriteCounter = (spriteCounter+1>=timer) ? 0 :spriteCounter+1;
        //Sets the current sprite index
        currentSprite = (((currentSprite + 1) >= spritesLen) && (spriteCounter >= (timer - 1))) ? 0 :
                ((spriteCounter >= (timer - 1)) ? (currentSprite + 1) :
                        currentSprite);

        BufferedImage sprite = animations.get(animationIndex).get(currentSprite);
        float angle = getRotation().getAngle();

        return (Sprite.resize(rotate(angle,sprite),getScale()));
    }

    public static BufferedImage resize(BufferedImage img, Vector2 scale) {
        Image tmp = img.getScaledInstance((int) scale.getX(), (int) scale.getY(), Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage((int) scale.getX(), (int) scale.getY(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public BufferedImage rotate(double angle,BufferedImage image)
    {

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
