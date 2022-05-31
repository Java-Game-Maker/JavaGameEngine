package JavaGameEngine.Backend;

import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Backend.Input.Keys;
import JavaGameEngine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWorld extends JPanel {

    Scene currentScene = new Scene();
    public GameWorld(){
        setLayout(new GridLayout(0, 1));
        setBackground(Color.green);
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
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public void setCurrentScene(Scene currentScene) {
        this.remove(getCurrentScene());
        getCurrentScene().setActive(false);
        this.add(currentScene);
        this.currentScene = currentScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}
