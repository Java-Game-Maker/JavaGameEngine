package Testing.Plattformer;

import JGame.Msc.Vector2;
import JGame.Objects.Components.Animation;
import JGame.Objects.Components.Collision.SquareCollider;
import JGame.Objects.Components.Physics.PhysicsBody;
import JGame.Objects.GameObject;

public class Item extends GameObject {


    public Item(boolean goDown) {

        super();
        //setScale(new Vector2(100,100));

        Animation animation = new Animation();
        animation.setPath("/spritesheet.png");
        animation.setTILE_SIZE(new Vector2(200,200));
        animation.loadAnimation(new Vector2[]{new Vector2(0,0),new Vector2(1,0)});
        animation.setTimer(50);
        animation.setAngle(90);
        addComponent(animation);

        SquareCollider collider = new SquareCollider();
        addComponent(collider);

        PhysicsBody b = new PhysicsBody();
        addComponent(b);
    }

    @Override
    public void Update() {
        super.Update();
    }
}
