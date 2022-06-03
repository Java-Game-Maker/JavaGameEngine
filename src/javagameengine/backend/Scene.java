package javagameengine.backend;

import javagameengine.components.Component;
import javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
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

        for(Component a : components){
            a.start();
        }
        UpdateThread.camera.setPosition(new Vector2(0,0));

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
        List<Component> list = components;
        Collections.sort(list, new Comparator<Component>() {
            @Override
            public int compare(Component o1, Component o2) {
                return o1.getLayer() - o2.getLayer();
            }
        });

        for(Component c : list){
            (c).draw(g);
            g.drawString(fps,10,20);
        }
    }
}
