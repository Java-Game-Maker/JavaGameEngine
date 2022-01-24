package Testing.Overhead;

import Main.Msc.Vector2;
import Main.Objects.Components.Animation;
import Main.Objects.Components.Collision.ScareCollider;
import Main.Objects.Object;

public class Lion extends Animal{



    public Lion() {
        super();
        randomRot();
        setAnimation(new Animation());
        setScale(new Vector2(50,50));
        getAnimation().setPath("/spritesheet.png");
        getAnimation().setTILE_SIZE(new Vector2(251,250));
        getAnimation().setTimer(20);
        getAnimation().loadAnimation(new Vector2[]{new Vector2(0,0),new Vector2(0,1)});
        setHunger(50);
        setPosition(getPosition().add(getDirection().getNormalized().multiply(getCurrentSpeed())));
        setCurrentSpeed(1);

        ScareCollider collider = new ScareCollider();
        collider.setPosition(getPosition());
        collider.setScale(getScale().multiply(2));
        collider.setParent(this);
        collider.setVisible(true);
        addCollider(collider);

    }

    @Override
    public void Update() {
        super.Update();
        setHunger(getHunger()-0.1f);

    }
    @Override
    public void onCollision(Object collision) {
        super.onCollision(collision);
        if(collision.getTag().equals("Giraffe"))
        {
            Vector2 toLookAt = collision.getPosition();
            if(getHunger()<60) {
                float angle = (float) LookAt(toLookAt);
                setAngle(angle);
                setDirection(getDirection().getDirection((double) angle));
                Animal n = (Animal) collision;
                n.setHealth(getHealth()-1);
            }
            if(getPosition().getDistance(toLookAt)<100&&getHunger()<60)
            {
                //(Animal) collision.setCurrentSpeed();
            }
        }
    }
}
