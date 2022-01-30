package JavaGameEngine.Components.Collider;

import JavaGameEngine.Components.Component;

public abstract class Collider extends Component {

    private boolean isTrigger= false;
<<<<<<< HEAD
    private boolean isVisible = true;
=======
    private boolean isVisible = false;
>>>>>>> animation

    public boolean isVisible() {
        return isVisible;
    }
<<<<<<< HEAD
=======

>>>>>>> animation
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }

    public void checkCollision(){
        /*
            implement collider code here (extends)
         */
    }

    public abstract void collisionHandler(Component ob2);

    public Object copy() {
        return this;
    }
}
