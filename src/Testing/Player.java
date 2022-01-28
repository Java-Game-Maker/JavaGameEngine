package Testing;

import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.Components.Ui.Label;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Player extends GameObject {
    Label speed = new Label();
    public Player() {
        speed.setValue("knas");
        speed.setPosition(new Vector2(200,200));
        GameObject box = new GameObject();
        box.setScale(new Vector2(50,50));
        box.setLocalPosition(new Vector2(0,-100));
        SquareCollider S = new SquareCollider();
        S.setScale(new Vector2(100,100));
        addChild(speed);
        addChild(box);
        addChild(S);
        addChild(new PhysicsBody());
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
