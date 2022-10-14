package javagameengine;

import javagameengine.components.Camera;
import javagameengine.components.CameraMovement;
import javagameengine.components.Component;
import javagameengine.input.Input;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Scene extends JPanel {
    public LinkedList<Component> layerList = new LinkedList<>();
    private ArrayList<Component> components = new ArrayList<>();
    private LinkedList<Component> newComponents = new LinkedList<>();
    private LinkedList<Component> remove = new LinkedList<>();
    Camera camera = new Camera();
    boolean debugMode = true;

    public Scene(){
        setBackground(new Color(40,125,255));
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

    public void add(Component component){
        components.add(component);
    }
    public ArrayList<Component> getComponents1() {
        return components;
    }
    public void setComponents(ArrayList<Component> components) {
        this.components = components;
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
    Component hasA = null;
    public void update(){
        time += JavaGameEngine.deltaTime;
        if((int) time/100 > lastSec){
            lastSec = (int) (time/100);
            for(Component component : components) {
                component.updateSecund();
            }
        }
        if((int) time/10 > lastMili){
            lastMili = (int) (time/10);
            for(Component component : components) {
                component.updateMili();
            }
        }
        for(Component component : components){
            if(inside(component)) {

                Point p = new Point((int) Input.getMousePosition().getX(), (int) Input.getMousePosition().getY());
                /*
                    if mouse is inside, and we have not been we call mouse entered and we say it is entered
                    if mouse is inside, and we have been we don't call mouse entered
                    if mouse is not inside, and we are previously we call mouse left
                 */
                if(component.getPolygon().contains(p) && (hasA == null || hasA == component)){
                    if(component.isMouseInside()){
                        component.mouseInside();
                        hasA = component;
                    }
                    else{
                        component.mouseEntered();
                    }
                }
                else if(component.isMouseInside()){
                    component.setMouseInside(false);
                    component.mouseLeft();
                    hasA = null;
                }

                if(!debugMode) {
                    component.update();
                }else{
                    component.debugUpdate();
                }
            }
        }

        camera.update();

        if(newComponents.size()>0){
            components.addAll(newComponents);
            newComponents.clear();
        }
        if(remove.size()>0){
            components.removeAll(remove);
            remove.clear();
        }
    }
    public void destroy(Component c){
        remove.add(c);
    }
    public boolean inside(Component component){
        //Debug.log(component.getPosition().getDistance(JavaGameEngine.getSelectedScene().getCamera().getPosition()));
        //return (component.getPosition().getDistance(JavaGameEngine.getSelectedScene().getCamera().getPosition()) < 1500);
        //Debug.log(String.valueOf(JavaGameEngine.getSelectedScene().getCamera().getPosition().add(component.getPosition()).getMagnitude()<1000));
        return JavaGameEngine.getSelectedScene().getCamera().getPosition().add(component.getPosition()).getMagnitude()<JavaGameEngine.getWindowSize().getMagnitude();
    }
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

        Input.setMousePosition(new Vector2(x,y));
        List<Component> list = components;
        /*Collections.sort(list, new Comparator<Component>() {
            @Override
            public int compare(Component o1, Component o2) {
                return o1.getLayer() - o2.getLayer();
            }
        });*/
        try{
            for(Component c : list){
                if(inside(c)) {
                    (c).render(graphics2D);
                }

            }
        }catch (Exception e){
        
        }
    }
}
