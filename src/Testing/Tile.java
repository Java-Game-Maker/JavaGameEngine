package Testing;

import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Sprite.Sprite;
import JavaGameEngine.msc.Vector2;

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
