package JavaGameEngine.Components;

import JavaGameEngine.Backend.ComponentHandler;
import JavaGameEngine.Backend.UpdateThread;
import JavaGameEngine.Components.Collider.Collider;
import JavaGameEngine.Components.Collider.SquareCollider;
import JavaGameEngine.Components.Physics.PhysicsBody;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import java.awt.*;
import java.util.LinkedList;

public class Component {

    Vector2 position=Vector2.zero; // world position
    Vector2 localPosition=Vector2.zero; // local position this is that children to a parent should change to change position

    Vector2 scale=Vector2.zero; // scale with,height
    Vector2 localScale=Vector2.zero; // local scale with,height

    Vector2 rotation=Vector2.zero; // rotation
    Vector2 localRotation=Vector2.zero; // local rotation this is to let children rotate separate to parent

    Component parent = null; // if component has parent it should update with some of the parents data
    LinkedList<Component> components = new LinkedList<>(); // children

    boolean isEnabled = true;
    private String tag = "unnamed";


    public Component() {
        this.scale = new Vector2(100,100);
        this.position = new Vector2(200,200);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

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
      //  updateChildren();
    }
    public Vector2 getSpritePosition(){
        float x = (getPosition().getX()-((getScale().getX()/2)));
        float y = (getPosition().getY()-((getScale().getY()/2)));

        return new Vector2(x,y);
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

    public Vector2 getLocalScale() {
        return localScale;
    }
    public void setLocalScale(Vector2 localScale) {
        this.localScale = localScale;
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

    /**
     * This function adds a child to the parent
     * it could be any JavaGameEngine component GameObject, Physics-body and so on
     * @param c the child you want to add
     */
    public void addChild(Component c){
        c.setParent(this);
        //Debug.log("added "+c);
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
    public <T extends Component> LinkedList<T> getChildren(T t){
        LinkedList<T> childrenToRet = new LinkedList<>();
        for(Component c : components){
            if(c.getClass().equals(t.getClass())){
                childrenToRet.add((T) c);
            }
        }
        return childrenToRet;
    }
    /**
     * This method will return the all children in the children with the same type as the argument
     * example LinkedList<GameObjects> a = this.getChild(new GameObject());
     */
    public LinkedList<Component> getChildren(){
        return components;
    }
    public void removeChild(Component child){
        components.remove(child);
    }
    /**
     * This method will add the component to the component handler
     * this means that you have created a new parent
     * @param c the object to instantiate
     */
    public void instantiate(Component c){
        //UpdateThread.newObjects.add(c);
    }
    /**
     * This method will destroy this object it will remove it from the component handler
     */
    public void destroy(){
        if(this.parent !=null){
            //parent.removeChild(this);
        }
        if(components.size()>0){
           // components.clear();
        }
        UpdateThread.delObjects.add(this);
    }
    /***
     * this is the update function. It will be called on every game update
     */
    public void update() {

       // if(this instanceof Collider)
         //   Debug.log(this);

        if(parent!=null) {
            float x = (parent.getPosition().getX()-((getScale().getX()/2)));
            float y = (parent.getPosition().getY()-((getScale().getY()/2)));

            setPosition(new Vector2(x,y).add(getLocalPosition())); // we get the parents position and we add our localPosition

            setRotation(parent.getRotation().add(getLocalRotation()));
            setScale(parent.getScale().add(getLocalScale()));
        }
        if(components.size()>0){
            updateChildren(); // updates all the children
        }
    }

    public void updateChildren(){
        LinkedList<Component> s =  getChildren();
        for (Component component :s) {
            component.update();
        }
    }

    /**
     * This will be called when a collision is detected if the component has a collider as a child
     */
    public void onCollisionEnter(Component c){
    }
    /**
     * This will be called when a collision is no longer happening if the component has a collider as a child
     */
    public void onCollisionExit(Component c){
    }
    /**
     * This will be called when a collision is happening if the component has a collider as a child
     */
    public void onCollision(Component c){
    }
    public void onTriggerEnter(Component c){
    }
    /**
     * This will be called when a collision is no longer happening if the component has a collider as a child
     */
    public void onTriggerExit(Component c){
    }
    /**
     * This will be called when a collision is happening if the component has a collider as a child
     */
    public void onTrigger(Component c){
    }

    public void draw(Graphics g) {
        for (Component c: getChildren()) {
            c.draw(g);
        }
    }

    public Vector2 movePosition(Vector2 add) {
        return add;
    }
}
