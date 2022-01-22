//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Testing;

import Main.Msc.Vector2;
import Main.Objects.Animation;
import Main.Objects.Collision.CircleCollider;
import Main.Objects.Object;
import java.awt.event.KeyEvent;

public class Lion extends Object {
    private float speed = 2.0F;
    private float gravity = 9.82F;

    public Lion(Vector2 position) {
        super(position);
        setAnimation(new Animation());
        getAnimation().setPath("/spritesheet.png");
        setScale(new Vector2(200,200));
        getAnimation().loadAnimation(new Vector2[]{new Vector2(0,0),new Vector2(0,1)});
        CircleCollider c = new CircleCollider();
        c.setVisible(true);
        c.setParent(this);
        c.setScale(new Vector2(200,200));
        addCollider(c);


    }

    @Override
    public void onCollision(Object collision) {
        super.onCollision(collision);
        //System.out.println(collision.getPosition());
    }

    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }

    public void keyDown(KeyEvent e) {
        if (e.getKeyCode() == 87) {
            this.setDirection(new Vector2(0.0F, -1.0F));
            this.setAngle(this.getDirection().getAngle());
            this.setPosition(this.getPosition().add(this.getDirection().multiply(this.speed)));
        }

        if (e.getKeyCode() == 83) {
            this.setDirection(new Vector2(0.0F, 1.0F));
            this.setAngle(this.getDirection().getAngle());
            this.setPosition(this.getPosition().add(this.getDirection().multiply(this.speed)));
        }

        if (e.getKeyCode() == 65) {
            this.setDirection(new Vector2(-1.0F, 0.0F));
            this.setAngle(this.getDirection().getAngle());
            this.setPosition(this.getPosition().add(this.getDirection().multiply(this.speed)));
        }

        if (e.getKeyCode() == 68) {
            this.setDirection(new Vector2(1.0F, 0.0F));
            this.setAngle(this.getDirection().getAngle());
            this.setPosition(this.getPosition().add(this.getDirection().multiply(this.speed)));
        }

    }

}
