package com.javagamemaker.javagameengine;

import com.javagamemaker.javagameengine.components.Camera;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.components.*;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Scene is the level where the game is playing out. Scenes can be changed by calling JavaGameEngine.setSelectedScene(new Scene())
 * <h1>to add components to the scene</h1>
 * (before start) run myscene.add(new Component())
 * (after start) myscene.instantiate(new Component())
 * <h1>remove component</h1>
 * (after start) myscene.destroy(new Component())
 */

public class Scene extends JPanel {
    public LinkedList<Component> layerList = new LinkedList<>();
    private ArrayList<Component> components = new ArrayList<>();
    private LinkedList<Component> newComponents = new LinkedList<>();
    private LinkedList<Component> remove = new LinkedList<>();
    private LinkedList<java.awt.Component> uiElements = new LinkedList<>();

    Camera camera = new Camera();
    private Component selectedComponent;
    public Component childSelected; // selects when selecgedn and pressing c
    boolean debugMode = true;
    public Vector2 gridSnapping = new Vector2(10,10);
    public Vector2 scaleGridSnapping = new Vector2(10,10);

    public Scene(){
        setBackground(new Color(40,125,255));
    }

    public void reload(){
        start();
    }

    public void instantiate(Component component){
        component.start();
        newComponents.add(component);
    }

    public void startScene(){
        camera.setScale(new Vector2(1,1));
        camera.setPosition(new Vector2(0,0));
    }

    public void start(){
        if(debugMode){
            camera.add(new CameraMovement());
        }
        camera.start();
        for(Component c : getComponents1()){
            c.start();
        }
    }

    /**
     *
     * @return list of all ui in the scene
     */
    public LinkedList<java.awt.Component> getUiElements() {
        return uiElements;
    }

    /**
     * sets the ui list with the param
     * @param uiElements list to set to
     */
    public void setUiElements(LinkedList<java.awt.Component> uiElements) {
        this.uiElements = uiElements;
    }

    /**
     * Adds a new component to the scene
     * DONT USE THIS WHILE THE GAME IS RUNNING USE instantiate INSTEAD
     *
     * @param component to be added
     */
    public void add(Component component) {
        components.add(component);
    }

    /**
     * For ui adding. This will add ui to the gameworld
     * @param comp   the component to be added
     * @return       the arbument
     */
    @Override
    public java.awt.Component add(java.awt.Component comp) {
       // JavaGameEngine.gameWorld.add(comp);
        uiElements.add(comp);
        return comp;
    }

    /**
     * for ui removal
     * @param comp the component to be removed
     */
    @Override
    public void remove(java.awt.Component comp) {
      //  JavaGameEngine.gameWorld.remove(comp);
        uiElements.remove(comp);
    }

    /**
     *
     * @return list of all components in the scene
     */
    public ArrayList<Component> getComponents1() {
        return components;
    }
    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    /**
     *
     * @return the scene camera
     */
    public Component getSelectedComponent() {
        return selectedComponent;
    }

    public void setSelectedComponent(Component selectedComponent) {
        this.selectedComponent = selectedComponent;
    }

    public Camera getCamera() {
        return camera;
    }

    public boolean isDebugMode() {
        return debugMode;
    }
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    private float time = 0;
    private int lastSec = 0;
    private int lastMili = 0;
    public Component hasA = null;
    public void update(){

        time += JavaGameEngine.deltaTime;
        if((int) time/100 > lastSec){
            lastSec = (int) (time/100);
            for(Component component : components) {
                component.updateSecond();
            }
        }
        if((int) time/10 > lastMili){
            lastMili = (int) (time/10);
            for(Component component : components) {
                component.updateMili();
            }
        }
        for(Component component : components){
            if(!debugMode) {
                component.update();
             }else{
                 component.debugUpdate();
             }
        }

        camera.update();
        if (newComponents.size() > 0) {
            components.addAll(newComponents);
            newComponents.clear();
        }
        if (remove.size() > 0) {
            components.removeAll(remove);
            remove.clear();
        }

        if(debugMode)
            debugUpdate();
        Input.setMousePressed(1000);
    }

    /**
     * removes component from the scene
     *
     * @param c the <code>Component</code> object to protect
     */
    public void destroy(Component c) {
        remove.add(c);
    }

    public boolean inside(Component component) {
        //Debug.log(component.getPosition().getDistance(JavaGameEngine.getSelectedScene().getCamera().getPosition()));
        //return (component.getPosition().getDistance(JavaGameEngine.getSelectedScene().getCamera().getPosition()) < 1500);
        //Debug.log(String.valueOf(JavaGameEngine.getSelectedScene().getCamera().getPosition().add(component.getPosition()).getMagnitude()<1000));
        return true;
        //return JavaGameEngine.getSelectedScene().getCamera().getPosition().add(component.getPosition()).getMagnitude()<JavaGameEngine.getWindowSize().getMagnitude();
    }

    private void debugUpdate(){
        if(Input.isMousePressed(Keys.RIGHTCLICK)){
            GameObject g = new GameObject();
            g.setPosition(Input.getMousePosition());
            instantiate(g);
        }

        if(Input.isKeyDown(Keys.DEL) && selectedComponent!=null){
            if(selectedComponent.getParent() == null)
                components.remove(selectedComponent);
            else
                selectedComponent.destroy();

            selectedComponent = null;
            hasA = null;
        }
        if(selectedComponent !=null && childSelected !=null && Input.isKeyPressed(Keys.SPACE)){
            if(childSelected.getParent() == null)
                components.remove(childSelected);
            else
                childSelected.destroy();
            if(selectedComponent.getParent()==null)
                childSelected.setParentOffset(childSelected.getPosition().subtract(selectedComponent.getPosition()));
            else
                childSelected.setParentOffset(childSelected.getPosition().subtract(selectedComponent.getPosition()));

            selectedComponent.add(childSelected);
        }

        if(Input.isKeyDown(Keys.CTRL) && Input.isKeyPressed(Keys.S)) {
            save();
        }
        if(Input.isKeyDown(Keys.CTRL) && Input.isKeyDown(Keys.X)) {
            Debug.log("copied");
            copyComp = selectedComponent;
        }
        if(Input.isKeyDown(Keys.CTRL) && Input.isKeyPressed(Keys.V)) {
            Component c = copyComp.clone();
            c.setPosition(Input.getMousePosition());
            components.add(c);
        }
    }
    private Component copyComp = null;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        //graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Vector2 scale = camera.getScale();
        //scale = scale.devide(JavaGameEngine.getWindowSize());

        graphics2D.scale(scale.getX(),scale.getY());

        float width = graphics2D.getClip().getBounds().width/2;
        float percentW = 1-scale.getX();
        float height = graphics2D.getClip().getBounds().height/2;
        float percentH = 1-scale.getY();

        graphics2D.translate(width*percentW,height*percentH);
        graphics2D.translate(camera.getPosition().getX(),camera.getPosition().getY());


        int x = (int) ((int) (Input.getMousePositionOnCanvas().getX() / getCamera().getScale().getX()) + graphics2D.getClip().getBounds().getX());
        int y = (int) ((int) (Input.getMousePositionOnCanvas().getY() / getCamera().getScale().getX()) + graphics2D.getClip().getBounds().getY() );

        if(debugMode){

            //lines through zero

            int x1 = 0;
            int x2 = 0;
            int y1 = (int) ((int) (camera.getPosition().getY()+JavaGameEngine.getWindowSize().getY())/getCamera().getScale().getY());
            int y2 = -(int) ((int) (camera.getPosition().getY()+JavaGameEngine.getWindowSize().getY())/getCamera().getScale().getY());

            g.drawLine(x1,y1,x2,y2);

            int x11 = (int) ((int) (camera.getPosition().getX()+JavaGameEngine.getWindowSize().getX())/getCamera().getScale().getX());
            int x21 = -(int) ((int) (camera.getPosition().getX()+JavaGameEngine.getWindowSize().getX())/getCamera().getScale().getX());
            int y11 = 0;
            int y21 = 0;

            g.drawLine(x11,y11,x21,y21);

        }

        Input.setMousePosition(new Vector2(x,y));
        List<Component> list = components;
        /*Collections.sort(list, new Comparator<Component>() {
            @Override
            public int compare(Component o1, Component o2) {
                return o1.getLayer() - o2.getLayer();
            }
        });*/
        int i = 0;
        try{
            for(Component c : getComponents1()){
                (c).render(graphics2D);
            }
        }catch (Exception e){

        }
    }

    public void save() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("filename",false);
            out = new ObjectOutputStream(fos);
            out.writeObject(components);
            out.close();
            Debug.log("Saved");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void load(){
        load(false);
    }

    /**
     * This function will load objects from a save file
     * This can be used to save game state. Remember it will save all the objects in the scene
     * so the file can become larger than needed for your application.
     * <h1>Scene editing</h1>
     * If you want to declare your objects with code then change them with the scene editor it is recommended
     * to first start scene editing without load then when you have saved (ctr+S) you close and add load(true).
     * this will override the additions you have made privous and only load the saved components.
     *
     * @param clear = true => will clear all previous added components.
     */
    public void load(boolean clear){
        FileInputStream fos;
        try {
            fos = new FileInputStream("filename");
            ObjectInputStream oos = new ObjectInputStream(fos);
            if(clear){
                components.clear();
            }
            for(Component c : (ArrayList<Component>) oos.readObject()){
                if(c.getClass() == Sprite.class){
                    LinkedList<Rectangle[]> oldTiles = ((Sprite)c).tiles;
                    ((Sprite) c).tiles = new LinkedList<>();
                    ((Sprite) c).animations = new ArrayList<>();
                    ((Sprite) c).animations1 = new ArrayList<>();

                    for(Rectangle[] animation : oldTiles){
                        ((Sprite) c).loadAnimation(animation,((Sprite)c).spriteSheetString);
                    }
                }
                components.add(c);
            }
            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
