package javagameengine.backend;

import Testing.Main;
import javagameengine.JavaGameEngine;
import javagameengine.backend.input.Input;
import javagameengine.components.Camera;
import javagameengine.components.Component;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
    The scene object is a scene or a level in a game
    Only one scene are displayed and updated at the same time
    To change the scene run the JavaGameEngine.setSelectedScene() or setSelectedScene if you have extended the JavaGameEngine
 */

public class Scene extends JPanel{
    /**
     * This is a list of the components that has chaneged thier layer.
     * Then in the render we sort so the components with a higher layer gets render on the top.
     */
    public static LinkedList<Component> layerList = new LinkedList<>();
    /**
     * A list of all objects inside the scene
     */
    public LinkedList<Component> components = new LinkedList<>();

    public static String fps = "0";

    public int id=120313;
    private Camera camera = new Camera();

    private boolean active = false;

    public Scene(){
        this.setLayout(null);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    /**
        Start the scene. Sets som default values
     */
    public void start(){

        setActive(true);
        setBackground(new Color(44, 157, 228));
        getCamera().setPosition(new Vector2(0,0));

    }
    public void starts(){
        for(Component a : components){
            a.start();
        }

    }

    public void add(Component component) {
        components.add(component);
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
     * This method is run every update cycle
     * updates thie origin point
     */
    public void update(){
        JavaGameEngine.origin = JavaGameEngine.getWindowSize().devide(2);
    }

    public void setCamera(Camera c){
        this.camera = c;
    }
    public Camera getCamera(){
        return camera;
    }

    /**
     * Here is the main drawing function
     * */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
    }

    private void drawComponents(Graphics g){
        g.drawString(fps,10,20);

        //g.drawString(Input.getMouseWorldPosition().toString(), (int) Input.getMousePosition().getX(), (int) Input.getMousePosition().getY());

        Graphics2D g1 = (Graphics2D) g;
        Vector2 scale = getCamera().getScale();
        //scale = scale.devide(JavaGameEngine.getWindowSize());
        g1.scale(scale.getX(),scale.getY());

        float width = g1.getClip().getBounds().width/2;
        float percentW = 1-scale.getX();
        float height = g1.getClip().getBounds().height/2;
        float percentH = 1-scale.getY();

        g1.translate(width*percentW,height*percentH);

        List<Component> list = components;
        Collections.sort(list, new Comparator<Component>() {
            @Override
            public int compare(Component o1, Component o2) {
                return o1.getLayer() - o2.getLayer();
            }
        });
        int i = 0;
        for(Component c : list){
           // c.setLayer(i);
            (c).draw(g1);
            i++;
        }
    }
}
