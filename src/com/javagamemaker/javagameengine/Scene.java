package com.javagamemaker.javagameengine;

import com.javagamemaker.javagameengine.components.Camera;
import com.javagamemaker.javagameengine.components.Component;
import com.javagamemaker.javagameengine.components.lights.LightManager;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.msc.Debug;
import com.javagamemaker.javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Scene is the level where the game is playing out. Scenes can be changed by calling JavaGameEngine.setSelectedScene(new Scene())
 * <h1>to add components to the scene</h1>
 * (before start) run myscene.add(new Component())
 * (after start) myscene.instantiate(new Component())
 * <h1>remove component</h1>
 * (after start) myscene.destroy(new Component())
 */
public class Scene extends JPanel {
    public ArrayList<Component> layerList = new ArrayList<>();
    private ArrayList<Component> components = new ArrayList<>();
    private final ArrayList<Component> newComponents = new ArrayList<>();
    private final ArrayList<Component> remove = new ArrayList<>();
    private ArrayList<java.awt.Component> uiElements = new ArrayList<>();
    Camera camera = new Camera();
    /**
     * With this true the scene will become dark (set darkness LightManager.opacity)
     * To add a light add the Light component to the scene or a parent
     */
    public boolean useLight = false;

    public Scene() {
        setBackground(new Color(40, 125, 255));
    }

    /**
     * @param component the new component to be added to the scene
     */
    public void instantiate(Component component) {
        component.start();
        newComponents.add(component);
        validate();
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
    public ArrayList<java.awt.Component> getUiElements() {
        return uiElements;
    }

    /**
     * sets the ui list with the param
     * @param uiElements list to set to
     */
    public void setUiElements(ArrayList<java.awt.Component> uiElements) {
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
        JavaGameEngine.gameWorld.add(comp);
        JavaGameEngine.gameWorld.validate();
        JavaGameEngine.gameWorld.repaint();

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
        JavaGameEngine.gameWorld.remove(comp);
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

    /**
     * Updates all the components and calculates delta time and fps
     */
    public void update() {
        if (newComponents.size() > 0) {
            components.addAll(newComponents);
            newComponents.clear();
        }
        if (remove.size() > 0) {
            components.removeAll(remove);
            remove.clear();
        }
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

        int lsize = components.size();
        for(int i = 0; i < lsize;i++){

            Component c = components.get(i);
            for(Component component : c.addedChildren) {
                component.setParent(c);
                component.setPosition(c.getPosition());
                c.getChildren().add(component);
            }
            c.addedChildren.clear();
            c.update();
        }

        camera.update();

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
        return true;//screen.contains(component.getShape().getBounds()) || component.getShape().contains(screen);
    }
    public static void playSound(String path){
        playSound(path,1);
    }
    /**
     * Plays a sound from path
     * @param path path
     */
    public static void playSound(String path, float inputVolume) {
       // new Thread(new Runnable() {
       //     @Override
       //     public void run() {
       //         try {
       //             class AudioListener implements LineListener {
       //                 private boolean done = false;

       //                 @Override
       //                 public synchronized void update(LineEvent event) {
       //                     LineEvent.Type eventType = event.getType();
       //                     if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
       //                         done = true;
       //                         notifyAll();
       //                     }
       //                 }

       //                 public synchronized void waitUntilDone() throws InterruptedException {
       //                     while (!done) {
       //                         wait();
       //                     }
       //                 }
       //             }

       //             AudioListener listener = new AudioListener();
       //             AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(JavaGameEngine.class.getResourceAsStream(path));
       //             try {
       //                 float volume = inputVolume*JavaGameEngine.masterVolume;
       //                 Clip clip = AudioSystem.getClip();
       //                 if (volume < 0f || volume > 1f) throw new IllegalArgumentException("Volume not valid: " + volume);


       //                 clip.addLineListener(listener);
       //                 clip.open(audioInputStream);
       //                 try {
       //                     FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
       //                     gainControl.setValue(20f * (float) Math.log10(volume));
       //                     clip.start();
       //                     listener.waitUntilDone();
       //                 } finally {
       //                     clip.close();
       //                 }
       //             } finally {
       //                 audioInputStream.close();
       //             }
       //         }catch (Exception e){ e.printStackTrace(); }
       //     }
       // }).start();
    }
    public Rectangle screen = new Rectangle();
    /**
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        //graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Vector2 scale = camera.getScale();
        //scale = scale.devide(JavaGameEngine.getWindowSize());

        graphics2D.scale(scale.getX(),scale.getY());

        float width = graphics2D.getClip().getBounds().width >> 1;
        float percentW = 1-scale.getX();
        float height = graphics2D.getClip().getBounds().height >> 1;
        float percentH = 1-scale.getY();

        graphics2D.translate(width*percentW,height*percentH);
        graphics2D.translate(camera.getPosition().getX(),camera.getPosition().getY());

        if(!screen.equals(graphics2D.getClip().getBounds())){
            screen = new Rectangle(graphics2D.getClip().getBounds().x-200,graphics2D.getClip().getBounds().y-200,graphics2D.getClip().getBounds().width+500,graphics2D.getClip().getBounds().height+500);
        }

        int x = (int) ((int) (Input.getMousePositionOnCanvas().getX() / getCamera().getScale().getX()) + graphics2D.getClip().getBounds().getX());
        int y = (int) ((int) (Input.getMousePositionOnCanvas().getY() / getCamera().getScale().getX()) + graphics2D.getClip().getBounds().getY() );
        Input.setMousePosition(new Vector2(x,y));
        ArrayList<Component> renderList = (ArrayList<Component>) components.clone();
        Collections.sort(renderList, new Comparator<Component>(){
           public int compare(Component o1, Component o2){
              return o1.getLayer() - o2.getLayer();
           }
        });

        try{
            //center graphics
            if(camera.parallax)  graphics2D.translate(JavaGameEngine.getWindowSize().getX()/2, JavaGameEngine.getWindowSize().getY()/2);

            int lsize = components.size();
            for(int i = 0; i < lsize;i++){
                Component c = renderList.get(i);
                if(inside(c) || true) {
                    //int layer = c.getLayer()==0?1:c.getLayer();
                    float layer = c.getPosition().getZ()==0?1:c.getPosition().getZ();
                    Camera camera = JavaGameEngine.getSelectedScene().getCamera();
                    if(camera.parallax){
                        Debug.log(camera.getPosition().getY());
                        float parx = camera.getPosition().getX()*layer/100;
                        float pary = camera.getPosition().getY()*layer/100;
                        graphics2D.translate(parx,pary);
                    }
                    (c).render(graphics2D);
                    if(camera.parallax){
                        graphics2D.translate(-camera.getPosition().getX()*layer/100,-camera.getPosition().getY()*layer/100);
                    }
                    //if(!c.isVisible()){
                    //    c.setVisible(true);
                    //    c.onCameraEnter();
                    //}
                }
                //else if(c.isVisible()){
                //    c.setVisible(false);
                //    c.onCameraLeft();
                //}
            }
            //graphics2D.translate(width*percentW,height*percentH);
            //graphics2D.scale(1/scale.getX(),1/scale.getY());

            if(useLight){
                //graphics2D.translate(-camera.getPosition().getX(),-camera.getPosition().getY());
                LightManager.render(graphics2D);
                //graphics2D.translate(camera.getPosition().getX(),camera.getPosition().getY());
            }
            //graphics2D.dispose();
        }catch (Exception e){}
    }
}
