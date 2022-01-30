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
        setScale(new Vector2(1000,64));
        setPosition(new Vector2(300,500));
        for(int i = 0;i<500;i++){
           /* Sprite sprite  = new Sprite();
            sprite.loadAnimation(new Rectangle[]{new Rectangle(0,0,250,250),new Rectangle(0,250,250,250)},"/spritesheet.png");
            sprite.setLocalScale(new Vector2(-getScale().getX()+64,0));
            sprite.setLocalPosition(new Vector2(i*sprite.getScale().getX(),0));
            addChild(sprite);
            Tile tile = new Tile("/sprites/Tiles/Tile_02.png");
            tile.setPosition(new Vector2(i*64,485));
            instantiate(tile);*/
            Sprite s = new Sprite();
            s.loadAnimation(new Rectangle[]{new Rectangle(0,0,32,32)},"/sprites/Tiles/Tile_02.png");
            s.setLocalScale(new Vector2(-getScale().getX()+64,-getScale().getY()+64));
            s.setLocalPosition(new Vector2(i*64,0));
            addChild(s);

        }

        SquareCollider s = new SquareCollider();
        s.setLocalPosition(new Vector2(1000,0));
        s.setLocalScale(new Vector2(1000*64,0));
        addChild(s);

    }

    @Override
    public void update() {
        super.update();
        //Debug.log(getParent().toString());
    }
}
