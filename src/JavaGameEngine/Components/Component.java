package JavaGameEngine.Components;

import JavaGameEngine.msc.Vector2;

import java.util.LinkedList;

public class Component {

    Vector2 position=Vector2.zero; // world position
    Vector2 localPosition=Vector2.zero; // local position this is that children to a parent should change to change position
    Vector2 scale=Vector2.zero; // scale with,height
    Vector2 rotation=Vector2.zero; // rotation
    Vector2 localRotation=Vector2.zero; // local rotation this is to let children rotate separate to parent

    Component parent = null; // if component has parent it should update with some of the parents data
    LinkedList<Component> components = new LinkedList<>(); // children

    boolean isEnabled = true;


    public Vector2 getLocalRotation() {
        return localRotation;
    }
    public void setLocalRotation(Vector2 localRotation) {
        this.localRotation = localRotation;
    }

    public Component getParent() {
        return parent;
    }
    public void setParent(Component parent) {
        this.parent = parent;
    }

    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(Vector2 position) {
        this.position = position;
        updateChildren();
    }

    public Vector2 getLocalPosition() {
        return localPosition;
    }
    public void setLocalPosition(Vector2 localPosition) {
        this.localPosition = localPosition;
    }

    public Vector2 getScale() {
        return scale;
    }
    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public Vector2 getRotation() {
        return rotation;
    }
    public void setRotation(Vector2 rotation) {
        this.rotation = rotation;
        updateChildren();
    }

    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }


    public Component() {
        this.scale = new Vector2(100,100);
        this.position = new Vector2(200,200);
    }

    /**
     * This function adds a child to the parent
     * it could be any JavaGameEngine component GameObject, Physics-body and so on
     * @param c the child you want to add
     */
    public void addChild(Component c){
        c.setParent(this);
        this.components.add(c);
    }

    /**
     * This method will return the first child in the children with the same type as the argument
     * example GameObject a = this.getChild(new GameObject());
     * @param t the type of component to return
     */
    public <T extends Component> Component getChild(T t){
        for(Component c : components){
            if(c.getClass().equals(t.getClass())){
                return c;
            }
        }
        return null;
    }

    /**
     * This method will return the all children in the children with the same type as the argument
     * example LinkedList<GameObjects> a = this.getChild(new GameObject());
     * @param t the type of component to return
     */
    public <T extends Component> LinkedList<Component> getChildren(T t){
        LinkedList<Component> childrenToRet = new LinkedList<>();
        for(Component c : components){
            if(c.getClass().equals(t.getClass())){
                childrenToRet.add(c);
            }
        }
        return childrenToRet;
    }

    /**
     * This method will add the component to the component handler
     * this means that you have created a new parent
     * @param c the object to instantiate
     */
    public void instantiate(Component c){

    }

    /**
     * This method will destroy this object it will remove it from the component handler
     */
    public void destroy(){
    }

    /***
     * this is the update function. It will be called on every game update
     */
    public void update() {
        if(parent!=null) {
            setPosition(parent.getPosition().add(getLocalPosition())); // we get the parents position and we add our localPosition
            setRotation(parent.getRotation().add(getLocalRotation()));
        }
        if(components.size()>0){
            updateChildren(); // updates all the children
        }
    }

    protected void updateChildren(){

        for (Component component : getChildren(new GameObject())) {
            component.update();
        }
    }

    /**
     * This will be called when a collision is detected if the component has a collider as a child
     */
    public void onCollisionEnter(){
    }
    /**
     * This will be called when a collision is no longer happening if the component has a collider as a child
     */
    public void onCollisionExit(){
    }
    /**
     * This will be called when a collision is happening if the component has a collider as a child
     */
    public void onCollision(){
    }


}
