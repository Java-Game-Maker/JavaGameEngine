package Main.Objects.Collision;

import Main.Msc.Vector2;
import Main.Objects.Object;

public class Collider {

    private Vector2 position;
    private Vector2 scale;
    private boolean visible = false;

    private Object parent;

    public Collider() {
    }

    public Collider(Vector2 position, Vector2 scale) {
        this.position = position;
        this.scale = scale;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public boolean isColliding(Collider otherCollider)
    {
        return false;
    }
    public void collided(Collider otherCollider)
    {

    }

    public void Update()
    {
        setPosition(parent.getPosition());
    }

}
