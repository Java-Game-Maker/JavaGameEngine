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
        String[] paths = new String[16];
        for(int i = 0;i< 16; i++){
            paths[i] = ("/Free Platform Game Assets/Platform Game Assets/Coin Animation/png/2x/image "+(i+1)+".png");
        }
        sprite.loadAnimation(paths);
        sprite.setTimer(20);

        setScale(new Vector2(5,5));
        addChild(sprite);
        SquareCollider b = new SquareCollider();
        b.setTrigger(true);
        b.setVisible(true);
        addChild(b);

        setTag("Coin");
        setRotation(new Vector2(0,-1));
        setPosition(new Vector2(100,420));
    }

    public float maxUp = 420-10;
    public float mindown =420+25;

    @Override
    public void update() {
        super.update();

        if(getPosition().getY()<maxUp){
            setRotation(Vector2.down);
        }
        if(getPosition().getY()>mindown){
            setRotation(Vector2.up);
        }
        setPosition(getPosition().add(getRotation().multiply(0.2f)));

    }
}
