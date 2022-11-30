package com.javagamemaker.javagameengine;

import com.javagamemaker.javagameengine.components.Camera;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.lights.Light;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
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
    private final LinkedList<Component> newComponents = new LinkedList<>();
    private final LinkedList<Component> remove = new LinkedList<>();
    private LinkedList<java.awt.Component> uiElements = new LinkedList<>();

    private ArrayList<Light> lights = new ArrayList<>();

    Camera camera = new Camera();

    public Scene() {
        setBackground(new Color(40, 125, 255));
    }

    /**
     * @param component the new component to be added to the scene
     */
    public void instantiate(Component component) {
        component.start();
        newComponents.add(component);
    }

    public void startScene() {
        camera.setScale(new Vector2(1, 1));
        camera.setPosition(new Vector2(0, 0));
    }

    public void start() {
        camera.start();
        for (Component c : getComponents1()) {
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
    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    private float time = 0;
    private int lastSec = 0;
    private int lastMili = 0;
    public Component hasA = null;

    public ArrayList<Light> getLights() {
        return lights;
    }

    public void setLights(ArrayList<Light> lights) {
        this.lights = lights;
    }

    /**
     * Updates all the components and calculates delta time and fps
     */
    public void update() {
        time += JavaGameEngine.deltaTime;
        if ((int) time / 100 > lastSec) {
            lastSec = (int) (time / 100);
            for (Component component : components) {
                component.updateSecond();
            }
        }
        if ((int) time / 10 > lastMili) {
            lastMili = (int) (time / 10);
            for (Component component : components) {
                component.updateMili();
            }
        }
        for (Component component : components) {
            if (inside(component)) {
                component.update();
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

    /**
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics before = g;
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
        }catch (Exception e){}

        Camera c = getCamera();
        Debug.log(c.getPosition());
        Vector2 gameWindowSize = JavaGameEngine.getWindowSize();
        Rectangle cameraView = new Rectangle(
                (int) (0),
                (int) 0,
                (int) gameWindowSize.getX(),
                (int) gameWindowSize.getY());

        Area screen = new Area(cameraView);

        Area lightAreas = new Area();
        for(Light light : lights){
            Area lightArea = new Area(light.getPolygon());

            lightAreas.add(lightArea);
        }

        screen.subtract(lightAreas);

        Color color[] = new Color[12];
        float fraction[] = new float[12];

        color[0] = new Color(0,0,0,0.1f);
        color[1] = new Color(0,0,0,0.42f);
        color[2] = new Color(0,0,0,0.52f);
        color[3] = new Color(0,0,0,0.61f);
        color[4] = new Color(0,0,0,0.69f);
        color[5] = new Color(0,0,0,0.76f);
        color[6] = new Color(0,0,0,0.82f);
        color[7] = new Color(0,0,0,0.87f);
        color[8] = new Color(0,0,0,0.91f);
        color[9] = new Color(0,0,0,0.94f);
        color[10] = new Color(0,0,0,0.96f);
        color[11] = new Color(0,0,0,0.98f);

        fraction[0] = 0f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;

        RadialGradientPaint gPaint = new RadialGradientPaint(50,50 ,(50), fraction, color);



        graphics2D.translate(-width*percentW,-height*percentH);
        graphics2D.translate(-camera.getPosition().getX(),-camera.getPosition().getY());
        graphics2D.scale(1/scale.getX(),1/scale.getY());

        graphics2D.setPaint(gPaint);

        graphics2D.fill(lightAreas);

        graphics2D.setColor(new Color(0,0,0,1f));

        graphics2D.fill(screen);

    }
}
