package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.components.shapes.Rect;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.InputComponent;
import com.javagamemaker.javagameengine.msc.Vector2;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.List;
import java.util.Objects;


/**
 * This is the first element in the JavaGameEngine
 * it has a lot of functions as update, render positions scale shape
 * the shape of the component is set by a number of points (localVerticies)
 * there is classes in the com.javagamemaker.javagameengine.components.shapes package which
 * are templates (rec, circle)
 */
public class Component implements Serializable {

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
    public ArrayList<Component> addedChildren = new ArrayList<>();
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
                                    //onCollisionEnter(event);
                                    onCollisionDown(event);
                                    onCollisionUp(event);
                                    colliding = true;
                                    try{
                                        Vector2 vel = ((PhysicsBody) getChild(new PhysicsBody())).velocity;
                                        ((PhysicsBody) getChild(new PhysicsBody())).response(event);
                                        if(this.<PhysicsBody>getChild(new PhysicsBody()).velocity.getX() == vel.getX()){
                                            //Debug.log("zeor");
                                           this.<PhysicsBody>getChild(new PhysicsBody()).velocity.setX(0);
                                        }
                                    }catch (Exception e){}
                                }
                            }


        for(Collider collider : this.<Collider>getChildrenT()){
            colliding = false;
            if(collider!=null){
                Collider addedX = new Collider();
                addedX.localVertices = collider.getLocalVertices();
                addedX.setPosition(collider.getPosition().add(towards.removeY()));
                addedX.setScale(addedX.getScale().subtract(0));
                addedX.updateVertices();

                Collider addedY = new Collider();
                addedY.localVertices = collider.getLocalVertices();
                addedY.setPosition(collider.getPosition().add(towards.removeX()));
                addedY.setScale(addedY.getScale().subtract(0));
                addedY.updateVertices();

                // all components in the scene
                for ( Component c : JavaGameEngine.getSelectedScene().getComponents1() ){
                    if(c != this && JavaGameEngine.getSelectedScene().inside(c)){ // don't check us
                        for ( Collider otherCollider : c.getAllColliders() ){
                            Rectangle rec1 = collider.getShape().getBounds();
                            Rectangle rec2 = otherCollider.getShape().getBounds();

                            rec1.width += rec1.width;
                            rec1.height += rec1.height;

                            rec2.width += rec2.width;
                            rec2.height += rec2.height;

                            if(rec1.getBounds().intersects (rec2.getBounds()) ||
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
                                        otherCollider.onCollisionEnter(event);
                                        colliding = true;
                                        try{
                                            Vector2 vel = ((PhysicsBody) getChild(new PhysicsBody())).velocity;
                                            ((PhysicsBody) getChild(new PhysicsBody())).response(event);
                                            if(((PhysicsBody) getChild(new PhysicsBody())).velocity.getX() == vel.getX()){
                                                //Debug.log("zeor");
                                                ((PhysicsBody) getChild(new PhysicsBody())).velocity.setX(0);
                                            }
                                        }catch (Exception e){}
                                    }
                                }

                                else{
                                    newPos.setY(0);
                                    CollisionEvent event = new CollisionEvent(collider,otherCollider,null);
                                    //onCollisionEnter(event);
                                    onCollisionDown(event);
                                    onCollisionUp(event);

                                    colliding = true;
                                    try{
                                        Vector2 vel = ((PhysicsBody) getChild(new PhysicsBody())).velocity;
                                        ((PhysicsBody) getChild(new PhysicsBody())).response(event);
                                        if(((PhysicsBody) getChild(new PhysicsBody())).velocity.getY() == vel.getY()){
                                            ((PhysicsBody) getChild(new PhysicsBody())).velocity.setY(0);
                                        }
                                    }catch (Exception e){}


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
        if(!JavaGameEngine.started){
            component.setParent(this);
            component.setPosition(getPosition());
            children.add(component);
        }else{
            addedChildren.add(component);
        }
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
            // add all the new children before the new update
            for(Component component : child.addedChildren) {
                component.setParent(child);
                component.setPosition(child.getPosition());
                child.children.add(component);
            }
            child.addedChildren.clear();
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
    public void checkMouse(){
        Point p = new Point((int) Input.getMousePosition().getX(), (int) Input.getMousePosition().getY());
                /*
                    if mouse is inside, and we have not been we call mouse entered and we say it is entered
                    if mouse is inside, and we have been we don't call mouse entered
                    if mouse is not inside, and we are previously we call mouse left
                 */
        Component prev = JavaGameEngine.getSelectedScene().hasA;
        if(getShape().contains(p) && (prev == null || (prev == this) || getLayer() > prev.getLayer() )  ){
            if(isMouseInside()){
                onMouseInside();
            }
            else{
                onMouseEntered();
            }
            JavaGameEngine.getSelectedScene().hasA = this;

        }
        else if(isMouseInside()){
            setMouseInside(false);
            onMouseLeft();
            JavaGameEngine.getSelectedScene().hasA = null;
        }
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


    public <T>T getChild(){
        for (Component child : this.children){
            try{
                return ((T)child);
            }
            catch (Exception e){}
        }
        return null;
    }

    public <T>ArrayList<T> getChildrenT(){
        ArrayList<T> children = new ArrayList<>();
        for (Component child : this.children){
            try{
                children.add(((T)child));
            }
            catch (Exception e){
                Debug.log("asd");
            }
        }
        return children;
    }
    public ArrayList<Collider> getAllColliders(){
        ArrayList<Collider> colliders = new ArrayList<>();
        if(children.size() > 0){
            for(Component c : children){
                colliders.addAll(c.getAllColliders());
            }
        }
        try{
            colliders.add((Collider)this);
        }catch (Exception E){}
        return colliders;
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
    private void onCollisionUp(CollisionEvent collisionEvent){
        if(getParent()!=null) getParent().onCollisionUp(collisionEvent);

        onCollisionEnter(collisionEvent);
    }
    private void onCollisionDown(CollisionEvent collisionEvent){
        for(Component c : children) c.onCollisionDown(collisionEvent);
        onCollisionEnter(collisionEvent);
    }

    /**
     * class when a collision from a collider is triggered
     * @param collisionEvent information about the collision
     */
    public void onCollisionEnter(CollisionEvent collisionEvent){
        colliding = true;

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

            double radians = Math.toRadians(angle); // turns to radians from angle
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
                    child.rotate((float) (angle),child.parentOffset.multiply(-1));
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
        Vector2 towards = new Vector2(getPosition());
        for(Collider collider : getAllColliders()){
            if(collider!=null){
                Collider addedX = new Collider();
                addedX.localVertices = collider.getLocalVertices();
                addedX.setPosition(getPosition().add(towards.removeY()));
                addedX.setScale(addedX.getScale().subtract(1));
                addedX.updateVertices();

                Collider addedY = new Collider();
                addedY.localVertices = collider.getLocalVertices();
                addedY.setPosition(getPosition().add(towards.removeX()));
                addedY.setScale(addedY.getScale().subtract(1));
                addedY.updateVertices();


                // all components in the scene
                for ( Component c : JavaGameEngine.getSelectedScene().getComponents1() ){
                    if(c != this && JavaGameEngine.getSelectedScene().inside(c)){ // don't check us
                        for ( Collider otherCollider : c.getAllColliders() ){
                            Rectangle rec1 = collider.getShape().getBounds();
                            Rectangle rec2 = otherCollider.getShape().getBounds();

                            rec1.width += rec1.width;
                            rec1.height += rec1.height;

                            rec2.width += rec2.width;
                            rec2.height += rec2.height;

                            g.setColor(Color.WHITE);
                            g.draw(addedX.getShape());
                            g.setColor(Color.RED);
                            g.draw(otherCollider.getShape());
                        }
                    }
                }
            }
        }

        List<Component> list = getChildren();

        for (Component child : list){
            child.render(g);
        }

        if(JavaGameEngine.getSelectedScene().isDebugMode() && JavaGameEngine.getSelectedScene().getSelectedComponent() == this){
            Color color = g.getColor();
            g.setColor(Color.GREEN);
            g.setStroke(new BasicStroke(10));
            g.draw(getShape());
            g.setColor(color);
        }

    }

    protected void onTriggerEnter(CollisionEvent collisionEvent) {
    }

    public void updateSecund(){

    }
    private Vector2 offset = null;
    private Vector2 prev = null;

    public void debugUpdate() {
        this.checkMouse();
        for (final Component c : this.getChildren()) {
            c.debugUpdate();
        }
        if (Input.isMousePressed(1) && this.mouseInside) {
            JavaGameEngine.getSelectedScene().setSelectedComponent(this);
        }
        if (JavaGameEngine.getSelectedScene().getSelectedComponent() == this && Input.isMousePressed(1) && !this.isMouseInside()) {
            JavaGameEngine.getSelectedScene().setSelectedComponent((Component)null);
        }
        if (JavaGameEngine.getSelectedScene().getSelectedComponent() == this && Input.isKeyPressed(67)) {
            JavaGameEngine.getSelectedScene().childSelected = this;
        }
        if (Input.isMouseDown(1) && !Input.isKeyDown(17) && JavaGameEngine.getSelectedScene().getSelectedComponent() == this) {
            if (this.offset == null) {
                this.offset = this.getPosition().subtract(Input.getMousePosition());
            }
            if (this.getParent() == null) {
                float gridCubeWidth = JavaGameEngine.getSelectedScene().gridSnapping.getX(), gridCubeHeight = JavaGameEngine.getSelectedScene().gridSnapping.getY();

                float x = Math.round(Input.getMousePosition().add(this.offset).getX() / gridCubeWidth) * gridCubeWidth;
                float y = Math.round(Input.getMousePosition().add(this.offset).getY() / gridCubeHeight) * gridCubeHeight;
                this.setPosition(new Vector2(x,y));

            }
            else {
                float gridCubeWidth = JavaGameEngine.getSelectedScene().gridSnapping.getX(), gridCubeHeight = JavaGameEngine.getSelectedScene().gridSnapping.getY();
                Vector2 pos = Input.getMousePosition().add(this.offset).subtract(this.getParent().getPosition());

                float x = Math.round(pos.getX() / gridCubeWidth) * gridCubeWidth;
                float y = Math.round(pos.getY() / gridCubeHeight) * gridCubeHeight;

                this.setParentOffset(new Vector2(x,y));
                this.getFirstParent().setPosition(this.getFirstParent().getPosition());
            }
        }
        else if (Input.isMouseDown(1) && Input.isKeyDown(17) && JavaGameEngine.getSelectedScene().getSelectedComponent() == this) {
            if (this.prev != null) {
                this.setScale(this.getScale().subtract(Input.getMousePosition().subtract(this.prev).multiply(new Vector2(-1.0f, 1.0f))));
            }
            this.prev = Input.getMousePosition();
        }
        else {
            this.offset = null;
        }



    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return layer == component.layer && Float.compare(component.angle, angle) == 0 && visible == component.visible && mouseInside == component.mouseInside && Objects.equals(tag, component.tag) && Objects.equals(position, component.position) && Objects.equals(parentOffset, component.parentOffset) && Objects.equals(scale, component.scale) && Objects.equals(localVertices, component.localVertices) && Objects.equals(vertices, component.vertices) && Objects.equals(children, component.children) && Objects.equals(parent, component.parent) && Objects.equals(prevPosition, component.prevPosition) && Objects.equals(lastPosition, component.lastPosition) && Objects.equals(rotOffset, component.rotOffset) && Objects.equals(offset, component.offset) && Objects.equals(prev, component.prev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(layer, tag, angle, visible, position, parentOffset, scale, localVertices, vertices, children, parent, prevPosition, mouseInside, lastPosition, rotOffset, offset, prev);
    }

    @Override
    public Component clone() {
        Component c = new Component();
        c.setPosition(getPosition());
        c.setScale(getScale());
        c.setTag(getTag());
        c.setChildren(getChildren());
        c.setParentOffset(getParentOffset());
        c.setParent(getParent());
        c.setLocalVertices(getLocalVertices());
        c.setVisible(isVisible());
        c.setLayer(layer);
        c.setPrevPosition(getPrevPosition());
        return c;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
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