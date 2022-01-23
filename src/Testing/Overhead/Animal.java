package Testing.Overhead;

import Main.Msc.Vector2;
import Main.Objects.Object;

import java.util.Random;

public class Animal extends Object {

    private float topSpeed = 2;
    private float cruisingSpeed=0.5f;
    private float currentSpeed=0.5f;

    private float health=100; //full health
    private float hunger=100; // no hunger
    private float thirst=100; // no thirst
    private float stamina=100; // full stamina

    private AnimalStates state=AnimalStates.NORMAL;
    
    private int ranTimer = 0;
    Random ran = new Random();


    public Animal()
    {
        setPosition(getRandomPos());
    }

    public Animal(Vector2 position) {
        super(position);
    }

    private Vector2 getRandomPos()
    {
        float x = random_int(100,900);
        float y = random_int(100,900);
        return new Vector2(x,y);
    }

    @Override
    public void Update() {
        super.Update();
        randomRot();
        setPosition(getPosition().add(getDirection().getNormalized().multiply(getCurrentSpeed())));
        if(health<0)
        {this.Die();}
    }


    public AnimalStates getState() {
        return state;
    }

    public void setState(AnimalStates state) {
        this.state = state;
    }

    public static int random_int(int Min, int Max)
    {
        return (int) (Math.random()*(Max-Min))+Min;
    }

    public void Die() {
        this.Destroy();
    }

    public void randomRot()
    {
        if(ranTimer>random_int(100,500))
        {
            int angle = ran.nextInt(360);
            //System.out.println(angle);
            setAngle(angle);
            setDirection(getDirection().getDirection((double)angle));

            ranTimer = 0;

        }
        ranTimer+=1;
    }

    public float getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(float topSpeed) {
        this.topSpeed = topSpeed;
    }

    public float getCruisingSpeed() {
        return cruisingSpeed;
    }

    public void setCruisingSpeed(float cruisingSpeed) {
        this.cruisingSpeed = cruisingSpeed;
    }

    public float getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getHunger() {
        return hunger;
    }

    public void setHunger(float hunger) {
        this.hunger = hunger;
    }

    public float getThirst() {
        return thirst;
    }

    public void setThirst(float thirst) {
        this.thirst = thirst;
    }

    public float getStamina() {
        return stamina;
    }

    public void setStamina(float stamina) {
        this.stamina = stamina;
    }
}
