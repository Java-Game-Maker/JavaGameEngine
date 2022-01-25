package JavaGameEngine.Objects.Components;

import JavaGameEngine.Msc.Vector2;
import JavaGameEngine.Objects.Components.Visual.Shape;

public class Component {
    public static Shape square = new Shape(new int[]{0,50,30,0},new int[]{20,20,0,0},4);
    public static Shape circle = new Shape(new int[]{1,2,2,1},new int[]{2,2,1,1},4);

    private Vector2 position=new Vector2(0,0);
    private Vector2 offset=new Vector2(0,0);
    private Vector2 direction = new Vector2(0,0);

    private Vector2 scale;
    private boolean visible = false;
    private GameObject parent;
    private boolean enabled= true;

    private Shape shape = new Shape();

    public Component(){
    }

    public Component(Shape shape) {
        this.shape = shape;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
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
