package Testing;

import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.Component;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.Components.Sprite.Sprite;
import JavaGameEngine.Components.Ui.Label;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Player extends GameObject {
    Label speed = new Label();
    public Player(Vector2 pos) {
        Sprite sprite = new Sprite();//,new Rectangle(0,250,250,250)
        sprite.loadAnimation(new Rectangle[]{new Rectangle(0,0,250,250),new Rectangle(0,250,250,250)},"/spritesheet.png");
        sprite.setLocalPosition(new Vector2(0,10));
        addChild(sprite);

        setScale(new Vector2(100,100));

        addChild(new PhysicsBody());

        SquareCollider s = new SquareCollider();
        s.setLocalScale(new Vector2(0,-40));
        s.setVisible(true);
        addChild(s);
    }
    @Override
    public void onCollision(Component c) {
        super.onCollision(c);
        if(c.getTag().equals("Coin")){
            c.destroy();
        }
    }
    @Override
    public void update() {
        super.update();
        if(Input.isKeyPressed(Keys.SPACE))
            ((PhysicsBody)this.getChild(new PhysicsBody())).addForce(Vector2.up,500);
        if(Input.isKeyDown((Keys.D))){
            movePosition(getPosition().add(Vector2.right.multiply(2)));
        }
        if(Input.isKeyDown((Keys.A))){
            movePosition(getPosition().add(Vector2.left.multiply(2)));
        }
        speed.setValue(String.valueOf(((PhysicsBody)getChild(new PhysicsBody())).getVelocity()));
    }
}
