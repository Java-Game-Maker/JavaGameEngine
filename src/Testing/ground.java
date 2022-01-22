package Testing;

import Main.Msc.Vector2;
import Main.Objects.Object;

public class ground extends Object {

    public ground(Vector2 position) {
        super(position,"/img.png");

        getSprite().loadSprites(new Vector2[]{new Vector2(1,0)});

        //setScale(new Vector2(600,10));
    }
}
