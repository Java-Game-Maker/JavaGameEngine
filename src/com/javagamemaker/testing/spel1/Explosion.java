package testing.spel1;

import javagameengine.components.Sprite;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Explosion extends Sprite {

    public Explosion(Vector2 pos, ArrayList<BufferedImage[]> animations){

        this.animations = animations;
        setPosition(pos);
    }

    @Override
    public void animationDone() {
        super.animationDone();
        destroy();
    }
}