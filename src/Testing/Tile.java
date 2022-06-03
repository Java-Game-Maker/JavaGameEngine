package Testing;

import javagameengine.components.colliders.SquareCollider;
import javagameengine.components.GameObject;
import javagameengine.components.sprites.Sprite;
import javagameengine.msc.Vector2;

import java.awt.*;

public class Tile extends GameObject {

    public Tile(String path) {

        setScale(new Vector2(64,64));
        Sprite s = new Sprite();
        s.loadAnimation(new Rectangle[]{new Rectangle(0,0,32,32)},path);
        addChild(s);
        addChild(new SquareCollider());
        //addChild(new PhysicsBody());

    }
}
