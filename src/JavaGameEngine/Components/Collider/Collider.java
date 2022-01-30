package JavaGameEngine.Components.Collider;

import JavaGameEngine.Components.Component;

public abstract class Collider extends Component {

    private boolean isTrigger= false;
    private boolean isVisible = false;

    public boolean isVisible() {
        return isVisible;
    }

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
