package JavaGameEngine.Objects.Components;

import JavaGameEngine.Display.CalcThread;
import JavaGameEngine.Msc.Vector2;
import JavaGameEngine.Objects.Components.Visual.Shape_idk;

public class Component extends Node{

    public static Shape_idk square = new Shape_idk(new int[]{1,2,2,1},new int[]{2,2,1,1},4,null);
    public static Shape_idk circle = new Shape_idk(new int[]{1,2,2,1},new int[]{2,2,1,1},4,null);

    private Vector2 position=new Vector2(200,200);
    private Vector2 offset=new Vector2(0,0);
    private Vector2 direction = new Vector2(0,0);

    private Vector2 scale=new Vector2(100,100);
    private boolean visible = false;

    private boolean enabled= true;

    private Shape_idk shape = new Shape_idk();

    private int id;


    public Component(){

    }

    public Shape_idk getShape() {
        return shape;
    }

    public void setShape(Shape_idk shape) {
        this.shape = shape;
    }

    public Component(Shape_idk shape) {
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

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position.add(getOffset());
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
        System.out.println("set scale "+getClass());
        this.scale = scale;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Vector2 movePosition(Vector2 position){

        return null;
    }

    public void Update()
    {

        UpdateComponents();
        //p.translate((int) (-this.position.getX()), (int) (this.position.getY()));
    }
    public void UpdateComponents()
    {
        if(getParent()!=null)
        {
            setPosition(getParent().getPosition().add(getOffset()));
        }
        for(Component c : getChildren(new Component()))
        {
            // System.out.println(c.toString());
            Component comp = (Component) c;
            if(comp.isEnabled())
            {
                comp.Update();
            }
        }
    }
    public void movePosition() {}

    @Override
    public String toString() {
        return "Component " + getClass().getName()+"{"+
                "position=" + position +
                ", offset=" + offset +
                ", scale=" + scale +
                ", visible=" + visible +
                ", parent=" + getParent() +
                '}';
    }


    public Component copy()
    {

        return this;
    }

    public void Destroy()
    {
        CalcThread.delObjects.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void onCollision(Component collision)
    {
        //setPosition(getPosition());
        //PhysicsBody b = getPhysicsbody();

    }
    public void onCollisionExit(Component collision)
    {

        //setColliding(false);
    }
    public void onCollisionEnter(Component parent) {
        //setColliding(true);

    }
    public void onTrigger(Component collision)
    {

    }

    @Override
    public void addChild(Node newNode) {
        super.addChild(newNode);
    }
}
