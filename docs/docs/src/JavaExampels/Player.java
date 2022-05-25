import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.msc.Vector2;

import java.awt.*;

public class Player extends GameObject {
    PhysicsBody physicsBody = new PhysicsBody(); // create new physicsbody
    SquareCollider collider = new SquareCollider(); // create new collider

    public Player(){
        setPosition(new Vector2(300,300)); // change spawn position of our player
        setScale(new Vector2(50,50)); // change spawn scale to 50*50 units
        addChild(physicsBody); //add physicsbody component
        addChild(collider); //add collider component
    }

    // draw function draws every update
    // u can use the graphics as usal here
    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void update() { // update function, runs every update
        super.update();
        if(Input.isKeyDown(Keys.D)){ //Check if D key is down
            movePosition(getPosition().add(Vector2.right)); // move player position right if so
        }
    }

}
