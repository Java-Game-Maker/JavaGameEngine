package Testing;
import Main.Msc.Keys;
import Main.Msc.Vector2;
import Main.Objects.Object;

import java.awt.event.KeyEvent;

public class Player extends Object{
    public Player(Vector2 position) {
        super(position);
        getSprite().loadSprites(new Vector2[]{new Vector2(2,0),new Vector2(2,1)});

    }

    @Override
    public void keyDown(KeyEvent e)
    {
        if(e.getKeyCode() == Keys.W)
        {
            setDirection(new Vector2(0,-1));
        }
        if(e.getKeyCode() == Keys.S)
        {
            setDirection(new Vector2(0,1));
        }
        if(e.getKeyCode() == Keys.A)
        {
            setDirection(new Vector2(-1,0));
        }
        if(e.getKeyCode() == Keys.D)
        {
            setDirection(new Vector2(1,0));
        }
        setPosition(getPosition().add(getDirection()));

    }

    @Override
    public void Update() {
        super.Update();
    }
}
