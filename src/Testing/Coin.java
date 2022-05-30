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

    public Vector2 startPos = Vector2.zero;
    public Coin() {

        Sprite sprite = new Sprite();

        sprite.loadAnimation(new Rectangle[]{new Rectangle(0,3*250,230,250)},"/spritesheet.png");
        sprite.setTimer(20);

        setScale(new Vector2(40,40));
        addChild(sprite);
        SquareCollider b = new SquareCollider();

        b.setTrigger(true);
        b.setVisible(true);
        addChild(b);

        setTag("Coin");
        setRotation(new Vector2(0,-1));
        setPosition(new Vector2(100,420));
        startPos=new Vector2(100,420);
        maxUp = startPos.getY()-10;
        mindown = startPos.getY()+25;
    }

    public float maxUp;
    public float mindown;

    @Override
    public void update() {
        super.update();
        maxUp = startPos.getY()-10;
        mindown = startPos.getY()+25;

        if(getPosition().getY()<maxUp){
            setRotation(Vector2.down);
        }
        if(getPosition().getY()>mindown){
            setRotation(Vector2.up);
        }
        setPosition(getPosition().add(getRotation().multiply(0.2f)));

    }
}
