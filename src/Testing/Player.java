package Testing;

import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Components.Collider.ShapeCollider;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.Component;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.Components.Sprite.Sprite;
import JavaGameEngine.Components.Ui.Label;
import JavaGameEngine.JavaGameEngine;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.awt.*;
import java.util.Observer;

public class Player extends GameObject {
    Label speed = new Label();
    ShapeCollider sc = new ShapeCollider();
    public Player(Vector2 pos) {

        Sprite sprite = new Sprite();//,new Rectangle(0,250,250,250)
        sprite.loadAnimation(new Rectangle[]{new Rectangle(0,0,250,250),new Rectangle(0,250,250,250)},"/spritesheet.png");
        //sprite.loadAnimation(new String[]{"/spritesheet.png"});
        sprite.setLocalPosition(new Vector2(0,10));
        addChild(sprite);

        setScale(new Vector2(100,100));

        addChild(new PhysicsBody());

        SquareCollider s = new SquareCollider();
        s.setLocalScale(new Vector2(0,-40));
        s.setVisible(true);
        addChild(s);
        addChild(sc);
    }
    @Override
    public void onTrigger(Component c) {
        super.onTrigger(c);

        if(c.getTag().equals("Coin")){
            c.destroy();
            instantiate(new Bullet(getPosition().add(new Vector2(20,0)),Vector2.right));

        }
    }
    @Override
    public void update() {
        super.update();
        if(Input.isKeyDown((Keys.D))){
            movePosition(getPosition().add(Vector2.right.multiply(2)));
        }
        if(Input.isKeyDown((Keys.A))){
            movePosition(getPosition().add(Vector2.left.multiply(2)));
        }
        if(Input.isKeyPressed(Keys.SPACE)){
            movePosition(getPosition().add(Vector2.up.multiply(100)));
        }
    }
}
