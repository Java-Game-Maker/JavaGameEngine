package JavaGameEngine.Components;

import JavaGameEngine.Backend.GameWorld;
import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.UpdateThread;
import JavaGameEngine.msc.Vector2;

import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;

public class Component {

    Vector2 position; // world position
    Vector2 localPosition=Vector2.zero; // local position this is that children to a parent should change to change position
    Vector2 cameraPosition = Vector2.zero; // camera offset

    Vector2 scale; // scale with,height
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
        GameWorld.layerList.add(this);
        this.layer = layer;
    }

    private boolean mouseInside = false;

    boolean isEnabled = true;
    private String tag = "unnamed";


    public Component() {
        this.scale = new Vector2(100,100);
        this.position = new Vector2(200,200);
    }
    public Component(Vector2 pos) {
        this.scale = new Vector2(100,100);
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
    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(Vector2 position) {
        if(!Objects.equals(this.tag, "player")){
            this.position = position;
        }
        else{
            this.position = position;
        }
      //  updateChildren();
    }
    public Vector2 getSpritePosition(){
        float x = (getPosition().subtract(cameraPosition).getX()-((getScale().getX()/2)));
        float y = (getPosition().subtract(cameraPosition).getY()-((getScale().getY()/2)));

        return new Vector2(x,y);
    }

    public Vector2 getLocalPosition() {
        return localPosition;
    }
    public void setLocalPosition(Vector2 localPosition) {
        this.localPosition = localPosition;
    }

    public Vector2 getCameraPosition() {
        return cameraPosition;
    }
    public void setCameraPosition(Vector2 localPosition) {
        this.cameraPosition = localPosition;
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
        if(isParent()){
            return isEnabled;
        }
        else{
            return getParent().isEnabled();
        }
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
            setCameraPosition(parent.getCameraPosition());

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

    public void drawChildren(Graphics g){
        for (Component c: getChildren()) {
            c.draw(g);
        }
    }
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
