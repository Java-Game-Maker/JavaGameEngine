package Testing.MouseTesting;

import JavaGameEngine.Msc.Input.Input;
import JavaGameEngine.Msc.Vector2;
import JavaGameEngine.Objects.Components.Collision.SquareCollider;
import JavaGameEngine.Objects.Components.Physics.PhysicsBody;
import JavaGameEngine.Objects.Components.GameObject;

public class Betty extends GameObject {
    public Betty(Vector2 position) {
        super(position);
        setScale(new Vector2(100,100));
        addComponent(new SquareCollider());
        addComponent(new PhysicsBody());

    }

    @Override
    public void Update() {
        super.Update();
        if(getPosition().getDistance(Input.getMousePosition())<50&&Input.isMouseDown(1)) {
            setPosition(Input.getMousePosition());
            getComponent(new PhysicsBody()).setEnabled(false);
        }
        else
            getComponent(new PhysicsBody()).setEnabled(true);


    }
}
