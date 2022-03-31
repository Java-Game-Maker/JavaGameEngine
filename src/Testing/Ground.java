package Testing;

import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.Components.Sprite.Sprite;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Ground extends GameObject {

    public Ground() {
        setScale(new Vector2(600,64));
        setPosition(new Vector2(300,500));

        /*
        for(int i = -300;i<50;i++){
            Sprite s = new Sprite();
            s.loadAnimation(new Rectangle[]{new Rectangle(0,0,128,128)},"/Free Platform Game Assets/Platform Game Assets/Tiles/png/128x128/Grass.png");
            s.setLocalScale(new Vector2(-getScale().getX()+64,-getScale().getY()+64));
            s.setLocalPosition(new Vector2(i*64,0));
            addChild(s);
        }
*/
        SquareCollider s = new SquareCollider();
        s.setLocalPosition(new Vector2(0,0));
        //s.setLocalScale(new Vector2(600,0));
        addChild(s);

    }

    @Override
    public void update() {
        super.update();
        //Debug.log(getParent().toString());
    }
}
