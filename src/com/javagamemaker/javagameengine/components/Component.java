package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.CollisionEvent;
import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;
import com.javagamemaker.testing.Main;
import com.javagamemaker.javagameengine.components.shapes.Rect;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

public class Component implements Serializable {

    protected int layer = 10;
    protected String tag = "";
    protected float angle = 0;
    protected boolean visible = true;
    protected Vector2 position = new Vector2(0,0);
    protected Vector2 parentOffset = new Vector2(0,0);
    protected Vector2 scale = new Vector2(100,100);
    protected LinkedList<Vector2> localVertices = new LinkedList<>();
    protected LinkedList<Vector2> vertices = new LinkedList<>();
    protected LinkedList<Component> children = new LinkedList<>();
    protected Component parent;
    protected Vector2 prevPosition = Vector2.zero;
    protected boolean mouseInside = false;

    protected Vector2 lastPosition;

    public Component(LinkedList<Vector2> localVertices){
        this.localVertices = localVertices;
    }
    public Component(){
       localVertices = new Rect(100,100);

        vertices.add(new Vector2(-50,-50)); // top left
        vertices.add(new Vector2(-50,50)); // bottom left
        vertices.add(new Vector2(50,50)); // bottom right
        vertices.add(new Vector2(50,-50)); // top right

    }

    /**
     * This will return the widest and highest from the vertices
     * @return vector2
     */
    public Vector2 getScale() {

        return new Vector2((float) getPolygon().getBounds().getWidth(), (float) getPolygon().getBounds().getHeight());
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        JavaGameEngine.getSelectedScene().layerList.add(this);
        this.layer = layer;
    }

    /**
     * this methods will be called when the game starts
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

    public boolean isVisible() {
        return visible;
    }

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

    public void setParentOffset(Vector2 parentOffset) {
        Vector2 deltaOffset = this.parentOffset.subtract(parentOffset);
        this.parentOffset = parentOffset;
    }

    public void setScale(Vector2 scale) {
        Vector2 d = scale.divide(getScale());
        LinkedList<Vector2> newVertices =new LinkedList<>();
        for(Vector2 vertex : localVertices){
            Vector2 newV = vertex.multiply(d);
            newVertices.add(newV);
        }
        for(Component c : children){
            LinkedList<Vector2> newVertices1 =new LinkedList<>();
            for(Vector2 vertex : c.localVertices){
                Vector2 newV = vertex.multiply(d);
                newVertices1.add(newV);
            }
            Debug.log(scale);
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

    public void setPosition(Vector2 position) {
        //this.lastPosition = this.position;

        if(getParent()!=null){
            this.position = position.add(parentOffset).add(rotOffset);
            for(Component c : getChildren()){
                c.setPosition(this.position);
            }

        }else{
            prevPosition = this.position;
            this.position = position;
            for(Component c : getChildren()){
                c.setPosition(position);
            }
        }

        updateVertices();

    }

    public LinkedList<Component> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Component> children) {
        this.children = children;
    }

    public Component getParent() {
        return parent;
    }

    public void setParent(Component parent) {
        this.parent = parent;
    }

    public LinkedList<Vector2> getVertices() {
        return vertices;
    }

    public LinkedList<Vector2> getLocalVertices() {
        return localVertices;
    }

    public void setLocalVertices(LinkedList<Vector2> localVertices) {
        this.localVertices = localVertices;
    }

    public void setVertices(LinkedList<Vector2> vertices) {
        this.vertices = vertices;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public void updateVertices(){
        LinkedList<Vector2> ver = new LinkedList<>();
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
        children.add(component);
    }

    public Component getFirstParent(){
        if(getParent()!=null){
            return getParent().getFirstParent();
        }
        return this;
    }
    public void updateMili(){

    }
    /**
     * This method updates all the values to the component
     */
    public void update(){
        checkMouse();

        for(Component child : children){
            child.update();
        }
    }
    /**
     * @return polygon based on components vertices
     */
    public Polygon getPolygon(){
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
        Component prev = Main.getSelectedScene().hasA;
        if(getPolygon().contains(p) && (prev == null || (prev == this) || getLayer() > prev.getLayer() )  ){
            if(isMouseInside()){
                mouseInside();
            }
            else{
                mouseEntered();
            }
            Main.getSelectedScene().hasA = this;

        }
        else if(isMouseInside()){
            setMouseInside(false);
            mouseLeft();
            Main.getSelectedScene().hasA = null;
        }
    }
    public LinkedList<Component> getChildren(Component type){
        LinkedList<Component> children = new LinkedList<>();
        for (Component child : this.children){
            if(child.getClass() == type.getClass()){
                children.add(child);
            }
        }
        return children;
    }
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
    public Component getChild(Component type) {

        for (Component child : this.children){
            if(child.getClass() == type.getClass()){
                return child;
            }
        }
        return null;
    }
    public Vector2 getBodyPosition(){
        return new Vector2(getPolygon().getBounds().x, getPolygon().getBounds().y);
    }
    public void onCollisionEnter(CollisionEvent event){
        for(Component c : children){
            c.onCollisionEnter(event);
        }

    }
    public void rotateTo(float angle, Vector2 pivot){
        rotate(angle-this.angle,pivot);
    }
    /**
     * rotates the local vertices to by the angle
     * @param angle the angle to rate the vertices with
     */
    public void rotate(float angle){

        this.angle += angle * JavaGameEngine.deltaTime;

        double radians = Math.toRadians(angle * JavaGameEngine.deltaTime); // turns to radians from angle
        LinkedList<Vector2> vertices1 = new LinkedList<>(); // new vertices
        for (int i = 0; i <localVertices.size();i++){
            Vector2 vertex = localVertices.get(i);
            // matrix rotation
            float[] matrix = {
            (float) (vertex.getX() * Math.cos(radians) - vertex.getY() * Math.sin(radians)),
            (float) (vertex.getX() * Math.sin(radians) + vertex.getY() * Math.cos(radians)) };

            vertices1.add(new Vector2(matrix[0],matrix[1]));
        }
        // set the localvertice to our new rotated matrix


        for(Component child : children){
            child.rotate((float) (angle*JavaGameEngine.deltaTime),child.parentOffset.multiply(-1));
        }

        this.localVertices = vertices1;
    }
    Vector2 rotOffset = Vector2.zero;
    /**
     * rotates the localc vertices to by the angle
     * @param angle the angle to rate the vertice with
     */
    public void rotate(float angle,Vector2 pivot){
        //Vector2 pivot1 = new Vector2(-50,0);
        this.angle += angle;


        double radians = Math.toRadians(angle); // turns to radians from angle
        LinkedList<Vector2> vertices1 = new LinkedList<>(); // new vertices
        for (int i = 0; i <localVertices.size();i++){
            Vector2 vertex = localVertices.get(i).subtract(pivot);
            // he wrote collide as collied
            // matrix rotation
            float[] matrix = {
                    (float) (vertex.getX() * Math.cos(radians) - vertex.getY() * Math.sin(radians)),
                    (float) (vertex.getX() * Math.sin(radians) + vertex.getY() * Math.cos(radians)) };

            vertices1.add(new Vector2(matrix[0],matrix[1]).add(pivot));
        }
        //Vector2 poly = new Vector2(getPolygon().getBounds().x, getPolygon().getBounds().y);

      //  rotOffset = poly.subtract(parent.getPosition()).subtract(pivot);
        /*

        float x = (float) Math.cos(Math.toRadians(this.angle));
        float y = (float) Math.sin(Math.toRadians(this.angle));

        Vector2 pos = new Vector2(
                pivot.getX()*x - pivot.getY()*y,
                pivot.getX()*y + pivot.getY()*x);
        setParentOffset(pos);


*/
        // set the localvertice to our new rotated matrix

        rotateChildren(pivot);

        this.localVertices = vertices1;
    }
    public void rotateChildren(Vector2 pivot){
        for(Component child : children){
            child.rotate(angle,pivot);
        }

    }
    public boolean isMouseInside() {
        return mouseInside;
    }

    public void setMouseInside(boolean mouseInside) {
        this.mouseInside = mouseInside;
    }

    public void mouseInside(){
    }
    public void mouseEntered(){
        this.mouseInside = true;
    }
    public void mouseLeft(){
    }
    public void destroy(){
        if(getParent()==null)
            JavaGameEngine.getSelectedScene().destroy(this);
        else{
            getParent().children.remove(this);
        }
    }
    /**
     * Renders the component
     * @param g what graphics to render to
     */
    public void render(Graphics2D g){
        List<Component> list = getChildren();

        for (Component child : list){
            child.render(g);
        }

        if(JavaGameEngine.getSelectedScene().isDebugMode() && JavaGameEngine.getSelectedScene().getSelectedComponent() == this){
            Color color = g.getColor();
            g.setColor(Color.GREEN);
            g.setStroke(new BasicStroke(10));
            g.drawPolygon(getPolygon());
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
        return "{position : "+position.toString()+",\n" +
                "scale:"+getScale().toString()+",\n" +
                "children: ["+getChildren()+"]}";
    }
}