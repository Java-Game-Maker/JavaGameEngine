package Testing;

import Main.Objects.*;
import Main.Msc.*;
import Main.Objects.Collision.CircleCollider;
import Main.Objects.Object;
import Testing.Main2;

import java.awt.event.KeyEvent;


public class Player extends Object {

    public Player(Vector2 position) {
        super(position);
        setAnimation(new Animation());
        setScale(new Vector2(100,100));
        getAnimation().setPath("/spritesheet.png");
        getAnimation().loadAnimation(new Vector2[]{new Vector2(0,0),new Vector2(0,1)});
        CircleCollider c = new CircleCollider();
        c.setParent(this);
        c.setVisible(true);
        c.setScale(new Vector2(100,100));
        addCollider(c);
    }

    @Override
    public void onCollision(Object collision) {
        super.onCollision(collision);
        //Main2.restart();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if(e.getKeyCode()==Keys.W)
        {
            //setPosition(getPosition().add(new Vector2(0,-1).multiply(50)));
            //setAngle(50);

        }
    }

    @Override
    public void Update() {
        super.Update();
        //setPosition(getPosition().add(new Vector2(0,1).multiply(2))); // down
    }
}
