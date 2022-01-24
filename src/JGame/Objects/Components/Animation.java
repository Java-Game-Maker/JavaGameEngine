package JGame.Objects.Components;

import JGame.Msc.Sprite;
import JGame.Msc.Vector2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Animation extends Component{


    private BufferedImage[] sprites;
    private ArrayList<BufferedImage[]> animations = new ArrayList<>();

    private int animationIndex=0;

    private BufferedImage spriteSheet;

    private String path;

    private Vector2 TILE_SIZE = new Vector2(16,16);

    private int spriteCounter=0;
    private int currentSprite=0;

    private Sprite sprite= new Sprite();
    private float timer=10;
    private float angle=0;

    public BufferedImage getAnimation()
    {

        int spritesLen = animations.get(animationIndex).length;
        //Adds until timer then 0
        spriteCounter = (spriteCounter+1>=timer) ? 0 :spriteCounter+1;
        //Sets the current sprite index
        currentSprite = (((currentSprite + 1) >= spritesLen) && (spriteCounter >= (timer - 1))) ? 0 :
                ((spriteCounter >= (timer - 1)) ? (currentSprite + 1) :
                        currentSprite);
        //Sprite.resize(getSprite().getSpriteImage(),new Vector2(100,100));
        //sprite.setSpriteImage(sprite.getSprites()[currentSprite]);

        sprite.setSpriteImage(animations.get(animationIndex)[currentSprite]);
        return (Sprite.resize(sprite.rotate(angle),getScale()));
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public void setAnimationIndex(int animationIndex) {
        this.animationIndex = animationIndex;
    }


    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public BufferedImage[] getSprites() {
        return sprites;
    }

    public void setSprites(BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Vector2 getTILE_SIZE() {
        return TILE_SIZE;
    }

    public void setTILE_SIZE(Vector2 TILE_SIZE) {
        this.TILE_SIZE = TILE_SIZE;
    }

    private BufferedImage getSprite(Vector2 grid) {

        BufferedImage sprite = null;


        try {
            InputStream in = this.getClass().getResourceAsStream(path);
            sprite = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (spriteSheet == null) {
            spriteSheet = sprite;
        }
        return (spriteSheet.getSubimage((int) (grid.getX() * TILE_SIZE.getX()), (int) (grid.getY() * TILE_SIZE.getY()), (int) TILE_SIZE.getX(), (int) TILE_SIZE.getY()));
    }

    public void loadAnimation(Vector2[] grids)
    {
        //sprites = new Vector2[(to.getX()-from.getY())+1];
        sprites = new BufferedImage[grids.length];
        int i=0;
        for(Vector2 grid : grids)
        {
            sprites[i] = getSprite(grid);
            i++;
        }
        animations.add(sprites.clone());
    }

    public void loadSprites(Vector2[] grids)
    {
        sprites = new BufferedImage[grids.length];
        int i=0;
        for(Vector2 grid : grids)
        {
            sprites[i] = getSprite(grid);
            i++;
        }
    }


}
