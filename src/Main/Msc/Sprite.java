package Main.Msc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Sprite {

    private BufferedImage spriteImage;
    private BufferedImage spriteSheet;

    private String path;
    private Vector2 grid;
    private int TILE_SIZE = 255;
    private Vector2 scale = new Vector2(150,150);


    private BufferedImage[] sprites;

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public int getTILE_SIZE() {
        return TILE_SIZE;
    }

    public void setTILE_SIZE(int TILE_SIZE) {
        this.TILE_SIZE = TILE_SIZE;
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
        System.out.println("setting path "+path);
        this.path = path;
    }

    public Vector2 getGrid() {
        return grid;
    }

    public void setGrid(Vector2 grid) {
        this.grid = grid;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public void setTileSize(int tileSize) {
        TILE_SIZE = tileSize;
    }

    public Sprite(String path) {
        this.path = path;
    }

    public void loadSprite() {

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

        spriteImage = Sprite.resize(spriteSheet.getSubimage((int) (grid.getX() * TILE_SIZE), (int) (grid.getY() * TILE_SIZE), TILE_SIZE, TILE_SIZE),scale);
    }

    public BufferedImage getSprite(Vector2 grid) {

        System.out.println("loading "+path);
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

        return Sprite.resize(spriteSheet.getSubimage((int) (grid.getX() * TILE_SIZE), (int) (grid.getY() * TILE_SIZE), TILE_SIZE, TILE_SIZE),scale);
    }

    public BufferedImage[] getSprites() {
        return sprites;
    }

    public void setSprites(BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public void loadSprites(Vector2[] grids)
    {
        //sprites = new Vector2[(to.getX()-from.getY())+1];
        sprites = new BufferedImage[grids.length];
        int i=0;
        for(Vector2 grid : grids)
        {
            sprites[i] = getSprite(grid);
            i++;
        }
    }

    public BufferedImage getSpriteImage() {
        return spriteImage;
    }

    public void setSpriteImage(BufferedImage spriteImage) {
        this.spriteImage = spriteImage;
    }

    public Sprite() {
    }
    public BufferedImage rotate(double angle)
    {

        // Getting Dimensions of image
        int width = getSpriteImage().getWidth();
        int height = getSpriteImage().getHeight();

        // Creating a new buffered image
        BufferedImage newImage = new BufferedImage(
                getSpriteImage().getWidth(), getSpriteImage().getWidth(), getSpriteImage().getType());

        // creating Graphics in buffered image
        Graphics2D g2 = newImage.createGraphics();

        // Rotating image by degrees using toradians()
        // method
        // and setting new dimension t it
        g2.rotate(Math.toRadians(angle), width / 2,
                height / 2);
        g2.drawImage(getSpriteImage(), null, 0, 0);

        // Return rotated buffer image
        return((newImage));
    }
    public static BufferedImage resize(BufferedImage img,Vector2 scale) {
        Image tmp = img.getScaledInstance((int) scale.getX(), (int) scale.getY(), Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage((int) scale.getX(), (int) scale.getY(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
