package Testing;

import Main.Msc.Vector2;
import Main.Objects.Animation;
import Main.Objects.Object;

public class pillor extends Object {

    public pillor(Vector2 position) {
        super(position);
        setAnimation(new Animation());
        setScale(new Vector2(150,150));
        getAnimation().setPath("/spritesheet.png");
        getAnimation().loadAnimation(new Vector2[]{new Vector2(2,0),new Vector2(2,1)});
        setShowHitBox(true);
    }

    @Override
    public void Update() {
        super.Update();
        setPosition(getPosition().add(new Vector2(-1,0).multiply(2)));
        if(getPosition().getX()<-200)
        {
            float y = getPosition().getY();
            setPosition(new Vector2(700,y));
        }
    }
}
