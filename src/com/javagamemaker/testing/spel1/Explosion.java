package testing.spel1;

import com.javagamemaker.javagameengine.components.Sprite;
import com.javagamemaker.javagameengine.msc.Vector2;

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