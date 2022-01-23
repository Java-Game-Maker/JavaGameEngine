package Testing.Plattformer;

import Main.Main;
import Main.Msc.Input.Input;
import Main.Msc.Input.Keys;
import Main.Msc.Vector2;
import Main.Objects.Animation;
import Main.Objects.Collision.ScareCollider;
import Main.Objects.Object;

public class Player extends Object {

    private float speed =2;
    private float gravity=0;
    private float jump=4;
    private boolean isGrounded = false;
    private boolean jumping=false;

    private float shootTimer = 10;

    public Player(Vector2 vector2) {
        super(vector2);
        setAnimation(new Animation());
        setScale(new Vector2(100,100));
        getAnimation().setPath("/playerSheet.png");
        setAngle(0);
        getAnimation().setTILE_SIZE(new Vector2(128,150));
        getAnimation().setTimer(5);
        getAnimation().loadAnimation(new Vector2[]{new Vector2(0,0)});
        getAnimation().loadAnimation(new Vector2[]{new Vector2(0,0),new Vector2(1,0),new Vector2(2,0),new Vector2(3,0),new Vector2(4,0),new Vector2(5,0)
        ,new Vector2(6,0),new Vector2(0,1),new Vector2(1,1),new Vector2(2,1),new Vector2(3,1),new Vector2(3,1),
                new Vector2(4,1),new Vector2(5,1)
        ,new Vector2(6,1),new Vector2(0,2),new Vector2(1,2),new Vector2(2,2),new Vector2(4,2),new Vector2(5,2),new Vector2(6,2),});

        ScareCollider c = new ScareCollider();
        c.setScale(new Vector2(100,100));
        c.setParent(this);
        c.setOffset(new Vector2(0,-10));
        c.setVisible(false);
        addCollider(c);

    }

    private void shoot()
    {
        Main.instantiate(new Bullet(this.getPosition(),this.getDirection()));

    }

    @Override
    public void onCollision(Object collision) {
        super.onCollision(collision);
        //Main.isPlaying=false;
        gravity = 0;
        jumping=false;
        isGrounded=true;
    }
    private void jump()
    {

        jumping = true;
        jump=2;
    }

    private void move()
    {
        if(isGrounded&&!jumping)
        {
            setPosition(getPosition().add(getDirection().multiply(speed)));
        }
        else
        {
            setPosition(getPosition().add(getDirection().multiply(speed)));

        }
    }

    @Override
    public void Update() {
        super.Update();
        setPosition(getPosition().add(new Vector2(0,1).multiply(gravity)));
        gravity +=0.02;
        if(Input.isKeyDown(Keys.W)&&isGrounded)
        {
            jump();
        }
        if(Input.isKeyDown(Keys.D))
        {
            setDirection(new Vector2(1,0));
            move();
            getAnimation().setAnimationIndex(1);

        }
        else if(Input.isKeyDown(Keys.A))
        {
            setDirection(new Vector2(-1,0));
            move();
            if(getAnimation().getAnimationIndex()==0)
                getAnimation().setAnimationIndex(1);

        }

        else{
            if(getAnimation().getAnimationIndex()==1)
                getAnimation().setAnimationIndex(0);

        }
        if(Input.isKeyDown(32)&&shootTimer<=0)
        {
            shoot();
            shootTimer = 10;
        }

        if(jumping)
        {
            setDirection(new Vector2(0,-1));
            setPosition(getPosition().add(getDirection().multiply(jump)));
        }
        jump-=0.01;
        shootTimer-=1;
        isGrounded=false;

    }
}
