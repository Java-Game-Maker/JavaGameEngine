package Testing;

import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.Components.Sprite.Sprite;
import JavaGameEngine.Components.Ui.Label;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Player extends GameObject {
    Label speed = new Label();
    public Player(Vector2 pos) {
        Sprite sprite = new Sprite();//,new Rectangle(0,250,250,250)
        sprite.loadAnimation(new Rectangle[]{new Rectangle(0,0,250,250),new Rectangle(0,250,250,250)},"/spritesheet.png");
        //sprite.setLocalRotation(Vector2.up);
        sprite.setLocalPosition(new Vector2(-20,0));

        addChild(sprite);

        setScale(new Vector2(100,100));
        addChild(new PhysicsBody());
        SquareCollider s = new SquareCollider();
<<<<<<< HEAD
        s.setLocalScale(new Vector2(-10,-10));
=======

        s.setVisible(false);
>>>>>>> animation
        addChild(s);
        s.setScale(new Vector2(50,getScale().getY()));
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
