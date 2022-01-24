package JGame.Objects.Components;

import JGame.Msc.Vector2;
import JGame.Objects.GameObject;

public class Component {

    private Vector2 position=new Vector2(0,0);
    private Vector2 offset=new Vector2(0,0);
    private Vector2 scale;
    private boolean visible = false;
    private GameObject parent;
    private boolean enabled= true;

    public Component(){
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {

        this.position = position.add(offset);
    }

    public Vector2 getOffset() {
        return offset;
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        //System.out.println((getPosition().getX()-scale.getX()));

        this.scale = scale;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void Update()
    {
        //p.translate((int) (-this.position.getX()), (int) (this.position.getY()));
    }

    @Override
    public String toString() {
        return "Component " + getClass().getName()+"{"+
                "position=" + position +
                ", offset=" + offset +
                ", scale=" + scale +
                ", visible=" + visible +
                ", parent=" + parent +
                '}';
    }

    public Component copy()
    {

        return this;
    }

}
