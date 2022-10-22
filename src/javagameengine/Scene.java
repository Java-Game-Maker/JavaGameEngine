package javagameengine;

import javagameengine.components.Camera;
import javagameengine.components.CameraMovement;
import javagameengine.components.Component;
import javagameengine.components.GameObject;
import javagameengine.input.Input;
import javagameengine.input.Keys;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.List;

/**
 * TODO
 * We must have a selected object where we can change properties
 * this should be selected by clicking on it
 * then can we change pos, rotation size
 *
 */


public class Scene extends JPanel {
    public LinkedList<Component> layerList = new LinkedList<>();
    private LinkedList<Component> components = new LinkedList<>();
    private LinkedList<Component> newComponents = new LinkedList<>();
    private LinkedList<Component> remove = new LinkedList<>();
    Camera camera = new Camera();
    private Component selectedComponent;
    public Component childSelected; // selects when selecgedn and pressing c
    boolean debugMode = true;

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

    public Component getSelectedComponent() {
        return selectedComponent;
    }

    public void setSelectedComponent(Component selectedComponent) {
        this.selectedComponent = selectedComponent;
    }

    public void add(Component component){
        components.add(component);
    }
    public LinkedList<Component> getComponents1() {
        return components;
    }
    public void setComponents(LinkedList<Component> components) {
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
    public Component hasA = null;
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
        debugUpdate();
        Input.setMousePressed(1000);

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

        if(selectedComponent!=null)
            graphics2D.drawString(selectedComponent.toString(),100,100);



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
        try{
            for(Component c : list){
                if(inside(c)) {
                    (c).render(graphics2D);
                }

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void load(){
        FileInputStream fos;
        try {
            fos = new FileInputStream("filename");
            ObjectInputStream oos = new ObjectInputStream(fos);
            components = (LinkedList<Component>) oos.readObject();
            fos.close();
        }
        catch (Exception e){

        }
    }
}
