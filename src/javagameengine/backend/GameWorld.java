package javagameengine.backend;

import javagameengine.backend.input.Input;
import javagameengine.backend.input.Keys;
import javagameengine.msc.Debug;
import javagameengine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GameWorld is the container all other scenes are displayed in.
 * It is also here we get all our inputs from the mouse and keyboard.
 * You should not have to interact with this in your game making.
 */
public class GameWorld extends JPanel {

    private Scene currentScene = new Scene();

    /**
     *
     */
    public GameWorld(){
        setLayout(new GridLayout(0, 1));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()== Keys.ESCAPE){
                    UpdateThread.running = !UpdateThread.running;
                }
                Input.addKey(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Input.removeKey(e);
            }
        });

        /*
          mouse input
         */
        MouseAdapter mouseAdapter = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Input.addMouseButton(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Input.removeMouseButton(e);

            }
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Input.setMousePosition(new Vector2((float) e.getPoint().getX(), (float) e.getPoint().getY()));
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                Input.setMousePosition(new Vector2((float) e.getPoint().getX(), (float) e.getPoint().getY()));

            }
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                if (e.isControlDown())
                {
                    if (e.getWheelRotation() < 0)
                    {
                        System.out.println("mouse wheel Up");
                    }
                    else
                    {
                        System.out.println("mouse wheel Down");
                    }
                }
                else
                {
                    getParent().dispatchEvent(e);
                }
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    /**
     *
     * @param currentScene the scene to be in use
     */
    public void setCurrentScene(Scene currentScene) {
        this.remove(getCurrentScene());
        getCurrentScene().setActive(false);
        this.add(currentScene);
        this.currentScene = currentScene;
    }

    /**
     *
     * @return the current scene in use
     */
    public Scene getCurrentScene() {
        return currentScene;
    }

}
