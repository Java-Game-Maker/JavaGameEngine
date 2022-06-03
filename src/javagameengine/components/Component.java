package javagameengine.components;

import javagameengine.backend.input.Input;
import javagameengine.backend.UpdateThread;
import javagameengine.JavaGameEngine;
import javagameengine.msc.Vector2;

import java.awt.*;
import java.util.LinkedList;

/**
 * The component class is the default object that can be rendered and updated in the engine.
 * You can build modules of components and add them to your Pbjects for example.
 * You could create a playermovement component and add that to the player object to seperate chnunks of code.
 */
public class Component {

    Vector2 position = new Vector2(200,200); // world position
    Vector2 localPosition=Vector2.zero; // local position this is that children to a parent should change to change position
    Vector2 cameraPosition = Vector2.zero; // camera offset

    Vector2 scale = new Vector2(100,100); // scale with,height
    Vector2 localScale=Vector2.zero; // local scale with,height

    Vector2 rotation=Vector2.zero; // rotation
    Vector2 localRotation=Vector2.zero; // local rotation this is to let children rotate separate to parent

    Component parent = null; // if component has parent it should update with some of the parents data
    LinkedList<Component> components = new LinkedList<>(); // children



    int layer = 0;

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        JavaGameEngine.getScene().layerList.add(this);
        this.layer = layer;
    }

    private boolean mouseInside = false;

    boolean isEnabled = true;
    private String tag = "unnamed";


    public Component() {
    }
    public Component(Vector2 pos) {
        this.position = pos;
    }
    public Component(Vector2 pos,Vector2 scale) {
        this.scale = scale;
        this.position = pos;
    }
    public void setMouseInside(boolean mouseInside) {
        this.mouseInside = mouseInside;
    }

    public boolean isMouseInside() {
        return mouseInside;
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
    public boolean isParent(){
        return getParent()==null;
    }
    public Component getFirstObject(){
        if(!isParent()){
            return getParent().getFirstObject();
        }
        return this;
    }

    /**
     * This is the global position everything counts on this. it is from the left top cornde of the sprite
     * @return current position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * This will only be set if the component isnt a child. This is because if the component
     * is a child it will set its positon to its parent + its local piston
     * @param position vector2 positon
     */
    public void setPosition(Vector2 position) {
        this.position = position;
      //  updateChildren();
    }
    /**
     * This has its origo in the middle of the object.
     * It is also affected by the camera position.
     *
     */
    public Vector2 getSpritePosition(){
        float x = (getPosition().subtract(UpdateThread.camera.getPosition()).getX()-((getScale().getX()/2)));
        float y = (getPosition().subtract(UpdateThread.camera.getPosition()).getY()-((getScale().getY()/2)));

        return new Vector2(x,y);
    }

    public Vector2 getSpriteScale(){
        //return getScale();
        return getScale().subtract(UpdateThread.camera.getScale());
    }

    public Vector2 getLocalPosition() {
        return localPosition;
    }

    /**
     * The localpositon is used for offseting the child from the parent.
     * If parent has pos 1,1 and we want the child to have 1,2 our localpositon should be set 0,1
     * because it is a offset.
     * @param localPosition vector2
     */
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
    /**
     *@return true if the component is enabled and returns parent isEnabled if it is a child
     */
    public boolean isEnabled() {
        if(isParent()){
            return isEnabled;
        }
        else{
            return getParent().isEnabled();
        }
    }

    /**
     * If enabled is false the component will not bwe updated and not bw drawn.
     * @param enabled true to be updated and drawn
     */
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
     *This method will return the first child in the children with the same type as the argument
     * example GameObject a = this.getChild(new GameObject());
     * @param t the class of the child to be retured
     * @param <T> idk
     * @return the first child that is of type T
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
     * example  a = this.getChild(new GameObject());
     * @param t Type of children to return
     * @param <T> idk
     * @return list of children of type T
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
     * example a = this.getChild(new GameObject());
     * @return list of all children
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
        UpdateThread.newObjects.add(c);
    }
    /**
     * This method will destroy this object it will remove it from the component handler
     */
    public void destroy(){
        if(this.parent !=null){
            parent.removeChild(this);
        }
        if(components.size()>0){
            components.clear();
        }
        UpdateThread.delObjects.add(this);
    }
    /***
     * this is the update function. It will be called on every game update
     * it updates all the children.
     */
    public void update() {
            if (insideComp() && isEnabled()) {
                if (!isMouseInside()) {
                    onMouseEntered();
                    setMouseInside(true);
                }

            } else if (isMouseInside() && isEnabled()) {
                onMouseExit();
                setMouseInside(false);
            }
            if (isMouseInside() && Input.isMousePressed() && isEnabled()) {
                onMousePressed();
                if (getParent() != null) getParent().onMousePressed();
            }

        if(parent!=null) {
            float x = (parent.getPosition().getX()-((getScale().getX()/2)));
            float y = (parent.getPosition().getY()-((getScale().getY()/2)));

            setPosition(new Vector2(x,y).add(getLocalPosition())); // we get the parents position and we add our localPosition
            // update this in the setScale and setRotation instead
            setRotation(parent.getRotation().add(getLocalRotation()));
            setScale(parent.getScale().add(getLocalScale()));

        }
        if(components.size()>0){
            updateChildren(); // updates all the children
        }
        //mouse enter and exit

    }
    private boolean insideComp(){
        float width = getScale().getX();
        float height = getScale().getY();

        float xMin = getSpritePosition().getX();
        float xMax = getSpritePosition().getX()+width;

        float yMin = getSpritePosition().getY();
        float yMax = getSpritePosition().getY()+height;

        float mx = Input.getMousePosition().getX();
        float my = Input.getMousePosition().getY();

        return mx > xMin && mx < xMax && my > yMin && my < yMax;
    }

    /**
     * Updates all the children
     */
    public void updateChildren(){
        LinkedList<Component> s =  getChildren();
        for (Component component :s) {
            component.update();
        }
    }

    /**
     * This will be called when a collision is detected if the component has a collider as a child
     * @param c Component collided with
     */
    public void onCollisionEnter(Component c){
    }
    /**
     * This will be called when a collision is no longer happening if the component has a collider as a child
     * @param c Component collided with
     */
    public void onCollisionExit(Component c){
    }
    /**
     * This will be called when a collision is happening if the component has a collider as a child
     * @param c Component collided with
     */
    public void onCollision(Component c){
    }
    public void onTriggerEnter(Component c){
    }
    /**
     * This will be called when a triggered is no longer happening if the component has a collider as a child
     * @param c Component triggered with
     */
    public void onTriggerExit(Component c){
    }
    /**
     * This will be called when a triggered is happening if the component has a collider as a child
     * @param c Component triggered with
     */
    public void onTrigger(Component c){
    }

    /**
     * Draws all components children
     * If this is not called in compnets children will not be drawn.
     * If you dont use super in the draw you have to call this fucntion so the children gets drawn
     * @param g the Grapcis object you get from the draw function
     */
    public void drawChildren(Graphics g){
        for (Component c: getChildren()) {
            c.draw(g);
        }
    }

    /**
     * This is where the component will be drawn (rendered)
     * @param g Graphics that can be used for draing
     */
    public void draw(Graphics g) {
        drawChildren(g);
    }

    /**
     * Runs after gameworld has been initilized
     */
    public void start(){

    }

    public Vector2 movePosition(Vector2 add) {
        return add;
    }

    public void onMousePressed() {
    }

    public void onMouseEntered() {
    }

    public void onMouseExit() {
    }
}
