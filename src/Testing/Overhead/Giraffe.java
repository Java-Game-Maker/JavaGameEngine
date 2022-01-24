package Testing.Overhead;

import Main.Msc.Vector2;
import Main.Objects.Components.Animation;
import Main.Objects.Components.Collision.ScareCollider;
import Main.Objects.Object;

public class Giraffe extends Animal{


    public Giraffe() {
        super();
        setAnimation(new Animation());
        setScale(new Vector2(50,50));
        getAnimation().setPath("/spritesheet.png");
        getAnimation().setTILE_SIZE(new Vector2(251,250));
        getAnimation().setTimer(20);
        getAnimation().loadAnimation(new Vector2[]{new Vector2(1,0),new Vector2(1,1)});

        ScareCollider collider = new ScareCollider();
        collider.setPosition(getPosition());
        collider.setScale(getScale().multiply(2));
        collider.setParent(this);
        collider.setVisible(true);
        addCollider(collider);

        setHunger(50);
        setTag("Giraffe");
    }

    @Override
    public void onCollision(Object collision) {
        super.onCollision(collision);
        if(collision.getTag().equals("Tree"))
        {
            System.out.println("Knas");
            Vector2 toLookAt = collision.getPosition();
            if(getHunger()<60) {
                float angle = (float) LookAt(toLookAt);
                setAngle(angle);
                setDirection(getDirection().getDirection((double) angle));
            }
            if(getPosition().getDistance(toLookAt)<100&&getHunger()<60)
            {
                setCurrentSpeed(0);
                setHunger(getHunger()+1);
            }
        }

    }

    @Override
    public void Update() {
        super.Update();
        if(getState()==AnimalStates.NORMAL)
        {
            setCurrentSpeed(getCruisingSpeed());
        }
        setHunger(getHunger()-0.1f);

        if(getHunger()>10)
            setScale(new Vector2(getHunger(),getHunger()));

    }
}
