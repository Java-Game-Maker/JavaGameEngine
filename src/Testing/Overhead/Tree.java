package Testing.Overhead;


import Main.Msc.Vector2;
import Main.Objects.Components.Animation;
import Main.Objects.Components.Collision.ScareCollider;
import Main.Objects.Object;

public class Tree extends Object {

    public Tree() {
        super();
        setPosition(getRandomPos());
        setAnimation(new Animation());
        setScale(new Vector2(100,100));
        getAnimation().setPath("/spritesheet.png");
        getAnimation().setTILE_SIZE(new Vector2(251,250));
        getAnimation().setTimer(20);
        getAnimation().loadAnimation(new Vector2[]{new Vector2(0,2),new Vector2(0,3)});

        ScareCollider collider = new ScareCollider();
        collider.setPosition(getPosition());
        collider.setScale(getScale().multiply(2));
        collider.setParent(this);
        collider.setVisible(true);
        addCollider(collider);

        setTag("Tree");

    }

    private Vector2 getRandomPos()
    {
        float x = random_int(100,900);
        float y = random_int(100,900);
        return new Vector2(x,y);
    }
    public static int random_int(int Min, int Max)
    {
        return (int) (Math.random()*(Max-Min))+Min;
    }
}
