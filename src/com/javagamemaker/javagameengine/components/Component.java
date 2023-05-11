package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This is the first element in the JavaGameEngine
 * it has a lot of functions as update, render positions scale shape
 * the shape of the component is set by a number of points (localVerticies)
 * there is classes in the com.javagamemaker.javagameengine.components.shapes package which
 * are templates (rec, circle)
 */
public class Component {

    protected int layer = 0;
    protected String tag = "";
    protected float angle = 0;
    protected boolean visible = true;
    protected Vector2 position = new Vector2(0,0);
    protected Vector2 parentOffset = new Vector2(0,0);
    protected Vector2 scale = new Vector2(100,100);
    protected ArrayList<Vector2> localVertices = new ArrayList<>();
    protected ArrayList<Vector2> vertices = new ArrayList<>();
    protected ArrayList<Component> children = new ArrayList<>();
    protected Component parent;
    protected Vector2 prevPosition = Vector2.zero;
    protected boolean mouseInside = false;
    protected boolean freezeRotation = false;
    protected boolean colliding = false;

    protected Vector2 lastPosition;

    public Component(ArrayList<Vector2> localVertices){
        this.localVertices = localVertices;
    }
    public Component(){
       localVertices = new Rect(100, 100);

        vertices.add(new Vector2(-50,-50)); // top left
        vertices.add(new Vector2(-50,50)); // bottom left
        vertices.add(new Vector2(50,50)); // bottom right
        vertices.add(new Vector2(50,-50)); // top right

    }

    public Component(Vector2 vector2) {
        setPosition(vector2);
    }

    public void setFreezeRotation(boolean freezeRotation) {
        this.freezeRotation = freezeRotation;
    }

    public boolean isFreezeRotation() {
        return freezeRotation;
    }

    /**
     * This will return the widest and highest from the vertices
     * @return vector2
     */
    public Vector2 getScale() {
        return new Vector2((float) getShape().getBounds().getWidth(), (float) getShape().getBounds().getHeight());
    }

    /**
     * @return the layer the component is
     */
    public int getLayer() {
        return layer;
    }

    /**
     * @param layer the layer the component should be in
     */
    public void setLayer(int layer) {
        JavaGameEngine.getSelectedScene().layerList.add(this);
        this.layer = layer;
    }

    /**
     * this methods will be called when the game starts and gamewindow has inilized
     *
     */
    public void start(){

        for(Component c : children) c.start();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return true if it is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible should be visable or not
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Vector2 getPrevPosition() {
        return prevPosition;
    }

    public void setPrevPosition(Vector2 prevPosition) {
        this.prevPosition = prevPosition;
    }

    public Vector2 getParentOffset() {
        return parentOffset;
    }

    /**
     * @param parentOffset the offset a child has to its parent
     */
    public void setParentOffset(Vector2 parentOffset) {
        if(getParent()!=null){
            this.parentOffset = parentOffset;

        }else{
            this.parentOffset = parentOffset;
        }
        for(Component c : getChildren()){
            c.setParentOffset(parentOffset);
        }
        updateVertices();
    }

    /**
     *
     * @param scale new scale
     */
    public void setScale(Vector2 scale) {
        Vector2 d = scale.divide(getScale());
        ArrayList<Vector2> newVertices =new ArrayList<>();
        for(Vector2 vertex : localVertices){
            Vector2 newV = vertex.multiply(d);
            newVertices.add(newV);
        }
        for(Component c : children){
            ArrayList<Vector2> newVertices1 =new ArrayList<>();
            for(Vector2 vertex : c.localVertices){
                Vector2 newV = vertex.multiply(d);
                newVertices1.add(newV);
            }
            c.setParentOffset(c.getParentOffset().multiply(d));
            setPosition(getPosition());
            c.localVertices = newVertices1;
            c.updateVertices();
        }
        localVertices = newVertices;
        updateVertices();
    }


    public Vector2 getPosition() {
        return position;
    }

    /**
     * This will move the object if the object is not colliding (if it has a collision component)
     * if the component collides it will not continue translating
     *
     * We check all the components
     * Does not check all children's colliders however
     *
     * @param towards amount to move
     */
    public void translate(Vector2 towards){

        Collider collider = ((Collider) getChild(new Collider()));
        Vector2 newPos = new Vector2(towards.getX(),towards.getY());
        boolean temp = colliding;
        colliding = false;
        if(collider!=null){
            Collider addedX = new Collider();
            addedX.localVertices = collider.getLocalVertices();
            addedX.setPosition(collider.getPosition().add(towards.removeY()));
            addedX.setScale(addedX.getScale().subtract(1));
            addedX.updateVertices();

            Collider addedY = new Collider();
            addedY.localVertices = collider.getLocalVertices();
            addedY.setPosition(collider.getPosition().add(towards.removeX()));
            addedY.setScale(addedY.getScale().subtract(1));
            addedY.updateVertices();

            // all components in the scene
            for ( Component c : JavaGameEngine.getSelectedScene().getComponents1() ){
                if(c.getPosition().getZ() != getPosition().getZ()) continue;

                if(c != this && JavaGameEngine.getSelectedScene().inside(c)){ // don't check us
                    for ( Component cc : c.getChildren(new Collider()) ){
                        Collider otherCollider = (Collider) cc;
                        Rectangle rec1 = collider.getShape().getBounds();
                        Rectangle rec2 = otherCollider.getShape().getBounds();

                        rec1.width += rec1.width;
                        rec1.height += rec1.height;

                        rec2.width += rec2.width;
                        rec2.height += rec2.height;

                        if(     rec1.getBounds().intersects (rec2.getBounds()) ||
                                rec1.getBounds().intersects(rec1.getBounds())  ){

                            if((addedX.collision(otherCollider)) != null ){
                                if(collider.isTrigger()){
                                    onTriggerEnter(new CollisionEvent(collider,otherCollider,null));
                                    otherCollider.onTriggerEnter(new CollisionEvent(otherCollider,collider,null));
                                }
                                else if(otherCollider.isTrigger()){
                                    onTriggerEnter(new CollisionEvent(otherCollider,collider,null));
                                    otherCollider.onTriggerEnter(new CollisionEvent(otherCollider,collider,null));
                                }
                                else{
                                    newPos.setX(0);

                                    // Create collision event
                                    CollisionEvent event = new CollisionEvent(collider,otherCollider,null);
                                    onCollisionEnter(event);
                                    colliding = true;
                                    try{
                                        Vector2 vel = ((PhysicsBody) getChild(new PhysicsBody())).velocity;
                                        ((PhysicsBody) getChild(new PhysicsBody())).response(event);
                                        if(this.<PhysicsBody>getChild().velocity.getX() == vel.getX()){
                                            //Debug.log("zeor");
                                           this.<PhysicsBody>getChild().velocity.setX(0);
                                        }
                                    }catch (Exception e){}
                                }
                            }

                            if((addedY.collision(otherCollider)) !=null ){
                                if(collider.isTrigger()){
                                    onTriggerEnter(new CollisionEvent(collider,otherCollider,null));
                                    otherCollider.onTriggerEnter(new CollisionEvent(otherCollider,collider,null));
                                }
                                else if(otherCollider.isTrigger()){
                                    onTriggerEnter(new CollisionEvent(collider,otherCollider,null));
                                    otherCollider.onTriggerEnter(new CollisionEvent(otherCollider,collider,null));
                                }
                                else{
                                    newPos.setY(0);
                                    CollisionEvent event = new CollisionEvent(collider,otherCollider,null);
                                    onCollisionEnter(event);
                                    colliding = true;
                                    try{
                                        Vector2 vel = ((PhysicsBody) getChild(new PhysicsBody())).velocity;
                                        ((PhysicsBody) getChild(new PhysicsBody())).response(event);
                                        if(((PhysicsBody) getChild(new PhysicsBody())).velocity.getY() == vel.getY()){
                                            ((PhysicsBody) getChild(new PhysicsBody())).velocity.setY(0);
                                        }
                                    }catch (Exception e){}
                                }
                            }
                        }
                    }
                }
            }
        }
        if(temp && !colliding){
           onCollisionLeft();
        }
        //Debug.log(newPos);
        setPosition(getPosition().add(newPos));
    }


    /**
     * if component is a child it will add its parent offset to the position
     * it will update all its childrens positions aswell
     * @param position new position not translate
     */
    public void setPosition(Vector2 position) {
        //this.lastPosition = this.position;

        if(getParent() != null){
            this.position = position.add(parentOffset).add(rotOffset);

        }else{
            prevPosition = this.position;
            this.position = position;
        }
        for(Component c : getChildren()){
            c.setPosition(position);
        }
        updateVertices();

    }

    public ArrayList<Component> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Component> children) {
        this.children = children;
    }

    public Component getParent() {
        return parent;
    }

    public void setParent(Component parent) {
        this.parent = parent;
    }

    public ArrayList<Vector2> getVertices() {
        return vertices;
    }

    public ArrayList<Vector2> getLocalVertices() {
        return localVertices;
    }

    public void setLocalVertices(ArrayList<Vector2> localVertices) {
        this.localVertices = localVertices;
    }

    public void setVertices(ArrayList<Vector2> vertices) {
        this.vertices = vertices;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    /**
     * update all shape points based on position
     */
    public void updateVertices(){
        ArrayList<Vector2> ver = new ArrayList<>();
        for(Vector2 vertex : localVertices){
            ver.add(vertex.add(position.subtract(rotOffset)));
        }
        vertices = ver;
    }

    /**
     * Use this function to add children. Do not add to the list itself
     * @param component the new children
     */
    public void add(Component component){

        component.setParent(this);
        component.setPosition(getPosition());
        children.add(component);
    }

    /**
     *
     * @return the first component in the tree
     */
    public Component getFirstParent(){
        if(getParent()!=null){
            return getParent().getFirstParent();
        }
        return this;
    }

    /**
     * updates every millisecond
     */
    public void updateMili(){
    }
    /**
     * This method updates all the values to the component
     */
    public void update(){
        Point p = new Point((int) Input.getMousePosition().getX(), (int) Input.getMousePosition().getY());
        /*
            if mouse is inside, and we have not been we call mouse entered and we say it is entered
            if mouse is inside, and we have been we don't call mouse entered
            if mouse is not inside, and we are previously we call mouse left
        */
        Component prev = JavaGameEngine.getSelectedScene().hasA;
        if (getShape().contains(p) && (prev == null || (prev == this) || getLayer() > prev.getLayer())) {
            if (isMouseInside()) {
                onMouseInside();
            } else {
                onMouseEntered();
            }
            JavaGameEngine.getSelectedScene().hasA = this;

        } else if (isMouseInside()) {
            setMouseInside(false);
            onMouseLeft();
            JavaGameEngine.getSelectedScene().hasA = null;
        }
        for(Component child : children){
            child.update();
        }
    }
    /**
     * @return polygon based on components vertices
     */
    public Shape getShape(){
        int[] x = new int[vertices.size()];
        int[] y = new int[vertices.size()];
        int i = 0;

        for(Vector2 point : vertices){
            x[i] = (int) point.getX();
            y[i] = (int) point.getY();
            i++;
        }

        return new Polygon(x,y,vertices.size());
    }
    /**
     *
     * @param type the specified type of the children to be returned
     * @return if type is (new PhysicsBody()) it will return the children that is a physicsBody as LinkedList<Component>
     */
    public LinkedList<Component> getChildren(Component type){
        LinkedList<Component> children = new LinkedList<>();
        for (Component child : this.children){
            if(child.getClass() == type.getClass()){
                children.add(child);
            }
        }
        return children;
    }

    /**
     *
     * @param type the specified type of the children to be returned
     * @return if type is (new PhysicsBody()) it will return the all children and all children's children that is a physicsBody as LinkedList<Component>
     */
    public LinkedList<Component> getAllChildren(Component type){
        LinkedList<Component> children = new LinkedList<>();
        for (Component child : this.children){
            children.addAll(child.getAllChildren(type));
            if(child.getClass() == type.getClass()){
                children.add(child);
            }
        }

        return children;
    }
    /**
     *
     * @param type the specified type of the children to be returned
     * @return if type is (new PhysicsBody()) it will return the first child that is a physicsBody as Component
     * @param <T> the type that will be returned (use this instead of casting)
     */
    public <T extends Component> T getChild(Component type) {

        for (Component child : this.children){
            if(child.getClass() == type.getClass()){
                return (T) child;
            }
        }
        return null;
    }
    public Vector2 getBodyPosition(){
        return new Vector2(getShape().getBounds().x, getShape().getBounds().y);
    }
    /**
     * class when a collision from a collider is triggered
     * @param collisionEvent information about the collision
     */
    public void onCollisionEnter(CollisionEvent collisionEvent){
        colliding = true;
        if(getParent()!=null) getParent().onCollisionEnter(collisionEvent);

    }

    /**
     * calls on when component leaves a collision
     */
    public void onCollisionLeft() {
        colliding = false;
        if(getParent()!=null) getParent().onCollisionLeft();
    }
    /**
     * rotate to specified angle
     * @param angle angle to rotate to
     * @param pivot the povit to rotate around
     */
    public void rotateTo(float angle, Vector2 pivot){
        if(!isFreezeRotation())
            rotate(angle-this.angle,pivot);
    }
    /**
     * rotates the local vertices to by the angle
     * @param angle the angle to rate the vertices with
     */
    public void rotate(float angle){

        if(!isFreezeRotation()) {
            this.angle += angle * JavaGameEngine.deltaTime;

            double radians = Math.toRadians(angle * JavaGameEngine.deltaTime); // turns to radians from angle
            ArrayList<Vector2> vertices1 = new ArrayList<>(); // new vertices
            for (int i = 0; i < localVertices.size(); i++) {
                Vector2 vertex = localVertices.get(i);
                // matrix rotation
                float[] matrix = {
                        (float) (vertex.getX() * Math.cos(radians) - vertex.getY() * Math.sin(radians)),
                        (float) (vertex.getX() * Math.sin(radians) + vertex.getY() * Math.cos(radians))};

                vertices1.add(new Vector2(matrix[0], matrix[1]));
            }
            // set the localvertice to our new rotated matrix


            for(Component child : children){
                if(!child.isFreezeRotation())
                    child.rotate((float) (angle*JavaGameEngine.deltaTime),child.parentOffset.multiply(-1));
            }

            this.localVertices = vertices1;
        }

    }
    Vector2 rotOffset = Vector2.zero;
    /**
     * rotates the localc vertices to by the angle
     * @param angle the angle to rate the vertice with
     */
    public void rotate(float angle,Vector2 pivot){
        //Vector2 pivot1 = new Vector2(-50,0);
        if(!isFreezeRotation()) {
            this.angle += angle;

            double radians = Math.toRadians(angle); // turns to radians from angle
            ArrayList<Vector2> vertices1 = new ArrayList<>(); // new vertices

            for (int i = 0; i < localVertices.size(); i++) {
                Vector2 vertex = localVertices.get(i).subtract(pivot);

                float[] matrix = {
                        (float) (vertex.getX() * Math.cos(radians) - vertex.getY() * Math.sin(radians)),
                        (float) (vertex.getX() * Math.sin(radians) + vertex.getY() * Math.cos(radians))};

                vertices1.add(new Vector2(matrix[0], matrix[1]).add(pivot));
            }
            rotateChildren(angle, pivot);
            this.localVertices = vertices1;
        }
    }

    /**
     * rotate children with an angle and around a poivot
     * @param angle angle to rotate
     * @param pivot rotate around
     */
    public void rotateChildren(float angle,Vector2 pivot){
        for(Component child : children){
            if(!child.isFreezeRotation())
                child.rotate(angle,pivot);
        }

    }

    /**
     * @return true if mouse is inside
     */
    public boolean isMouseInside() {
        return mouseInside;
    }

    /**
     * sets if the mouse is inside
     * @param mouseInside the value of mouse is inside
     */
    public void setMouseInside(boolean mouseInside) {
        this.mouseInside = mouseInside;
        for(Component c : children){

        }
    }

    /**
     * Triggers when mouse is inside
     */
    public void onMouseInside(){
    }

    /**
     * triggers when mouse enters component
     */
    public void onMouseEntered(){
        this.mouseInside = true;
    }

    /**
     * triggers when mouse left component
     */
    public void onMouseLeft(){
    }

    /**
     * removes component from parent or from scene
     */
    public void destroy(){
        if(getParent()==null)
            JavaGameEngine.getSelectedScene().destroy(this);
        else{
            getParent().children.remove(this);
        }
    }
    public void renderChildren(Graphics2D g){
        for (Component child : getChildren()){
            child.render(g);
        }
    }
    /**
     * Renders the component
     * @param g what graphics to render to
     */
    public void render(Graphics2D g){
        renderChildren(g);
        //g.dispose();
    }

    /**
     * triggers then a collider is a trigger and  collides with another trigger
     * @param collisionEvent holds information about the collision
     */
    protected void onTriggerEnter(CollisionEvent collisionEvent) {
        if(getParent()!=null) getParent().onTriggerEnter(collisionEvent);
    }

    /**
     * updates every second
     */
    public void updateSecond(){
    }

    /**
     * save the component to a file (serlizeable) linked list
     */
    public void save(){
    }
    public void load(){

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "{position : "+position.toString()+",\n" +
                "scale:"+getScale().toString()+",\n" +
                "children: ["+getChildren()+"]}";
    }

    public void onCameraEnter() {
        for(Component c : getChildren())
            c.onCameraEnter();
    }

    public void onCameraLeft() {
        for(Component c : getChildren())
            c.onCameraLeft();
    }
}