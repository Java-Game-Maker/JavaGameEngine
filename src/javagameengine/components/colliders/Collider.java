package javagameengine.components.colliders;

import javagameengine.components.Component;

public abstract class Collider extends Component {

    private boolean isTrigger= false;

    private boolean isVisible = false;

    /**
     *
     * @return true if the collider is shown
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * set if the collider should be drawn (good for setting scale)
     * @param visible true for displaying
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isTrigger() {
        return isTrigger;
    }

    /**
     * If you set this to true it will no collide with other colliders only triggerd
     * @param trigger true if you only want to now if there has been a collison without stopping movement
     */
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
