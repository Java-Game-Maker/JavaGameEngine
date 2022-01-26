package Testing.Children;

import JavaGameEngine.Msc.Input.Input;
import JavaGameEngine.Msc.Input.Keys;
import JavaGameEngine.Msc.Vector2;
import JavaGameEngine.Objects.Components.GameObject;

public class Cube extends GameObject {

    public boolean isMain =false;

    public Cube(Vector2 pos)
    {
        super();
        setOffset(pos);
    }

    @Override
    public void Update() {
        if(isMain)
        {
            if(Input.isKeyDown(Keys.D))
            {
                setDirection(Vector2.right);
            }
            else if(Input.isKeyDown(Keys.A))
            {
                setDirection(Vector2.left);
            }
            else if(Input.isKeyDown(Keys.S))
            {
                setDirection(Vector2.down);
            }
            else if(Input.isKeyDown(Keys.W))
            {
                setDirection(Vector2.up);
            }
            else
                setDirection(Vector2.zero);
            setPosition(getPosition().add(getDirection().multiply(2)));
        }
        UpdateComponents();
    }
}
