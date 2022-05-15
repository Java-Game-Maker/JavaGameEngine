package JavaGameEngine.Backend;

import JavaGameEngine.Backend.Input.Input;
import JavaGameEngine.Components.Component;
import JavaGameEngine.Components.GameObject;
import JavaGameEngine.Components.Ui.UiComponent;
import JavaGameEngine.msc.Debug;
import JavaGameEngine.msc.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class GameWorld extends JPanel{
    /*
        This is where the main game world where we draw everything
        we also gets all the inpus from here
     */
    public static String fps = "0";
    public GameWorld() {
        /*
          Key keyboard inputs
         */

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
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
                for(Component comp : ComponentHandler.getObjects()){

                    if(insideComp(comp)){
                        comp.onMousePressed();
                    }
                }
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
                for(Component comp : ComponentHandler.getObjects()){
                    if(insideComp(comp)){
                        if(!comp.isMouseInside()){
                            comp.onMouseEntered();
                            comp.setMouseInside(true);
                        }
                    }
                    else if (comp.isMouseInside()){
                        comp.onMouseExit();
                        comp.setMouseInside(false);
                    }
                }
                Input.setMousePosition(new Vector2((float) e.getPoint().getX(), (float) e.getPoint().getY()));
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    private boolean insideComp(Component comp){
        float width = comp.getScale().getX()/2;
        float height = comp.getScale().getY()/2;

        float xMin = comp.getPosition().getX()-width;
        float xMax = comp.getPosition().getX()+width;

        float yMin = comp.getPosition().getY()-height;
        float yMax = comp.getPosition().getY()+height;

        float mx = Input.getMousePosition().getX();
        float my = Input.getMousePosition().getY();

        if(mx>xMin&&mx<xMax&&my>yMin&&my<yMax){
            return true;
        }
        return false;
    }

    /**
     * Here is the main drawing function
     * */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
    }
    private void drawUi(Graphics g){

    }
    private void drawComponents(Graphics g){
        for(Component c : ComponentHandler.getObjects()){
            (c).draw(g);
            g.drawString(fps,0,10);
        }
    }
}
