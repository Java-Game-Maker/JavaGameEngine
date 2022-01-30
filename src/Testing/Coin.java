package Testing;

import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.Component;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.Components.Sprite.Sprite;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Coin extends GameObject {

    public Coin() {

        Sprite sprite = new Sprite();
        sprite.loadAnimation(new Rectangle[]{new Rectangle(0,0,10,10)},"/sprites/animated/Coin.png");
        setScale(new Vector2(20,20));
        addChild(sprite);
        SquareCollider b = new SquareCollider();
        b.setTrigger(true);
        addChild(b);

        setTag("Coin");

        setPosition(new Vector2(100,420));
    }


}
