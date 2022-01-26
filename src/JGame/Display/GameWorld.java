package JGame.Display;

import JGame.Main;
import JGame.Msc.Input.Input;
import JGame.Msc.ObjectHandler;
import JGame.Msc.Vector2;
import JGame.Objects.Components.*;
import JGame.Objects.Components.Collision.CircleCollider;
import JGame.Objects.Components.Collision.Collider;
import JGame.Objects.Components.Collision.SquareCollider;
import JGame.Objects.Components.Component;
import JGame.Objects.Components.Visual.Shape_s;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class GameWorld extends JPanel {

    public static LinkedList<GameObject> newObjects = new LinkedList<>();
    public static LinkedList<GameObject> delObjects = new LinkedList<>();

    private static ArrayList<JComponent> jComponents = new ArrayList<>();
    JGame.Objects.Components.Visual.Shape s = new  JGame.Objects.Components.Visual.Shape();
    public GameWorld() {


        //s.setPosition(new Vector2(300,300));
        s.setPosition(new Vector2(200,200));
        s.Scale(2);

        for(JComponent c : jComponents) {
            add(c);
        }
        setBackground(Color.GREEN);
        MouseAdapter mouseAdapter =  new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Input.mouseButtonDown(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Input.mouseButtonUp(e);
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Input.setMousePosition(new Vector2((float) e.getPoint().getX(), (float) e.getPoint().getY()));
            }
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                Input.setMousePosition(new Vector2((float) e.getPoint().getX(), (float) e.getPoint().getY()));
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                Input.keyDown(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Input.keyUp(e);
            }
        });
    }
    private void checkCollisions() {
        for(Component ob1 : ObjectHandler.getObjects()) {
            for(Component ob2 : ObjectHandler.getObjects()) {
                if(ob1!=(ob2)) {
                    for(Collider c1 : ob1.getChildren(new SquareCollider())) {
                        for(Collider c2 : ob2.getChildren(new SquareCollider())) {
                            c1.collided(c2);
                        }
                    }
                }
            }
        }
    }
    /**Check if a Jcomponent has been removed in the Jcomponents list and if so removes it in the panel**/
    public void UpdateSwingComponents() {
        for(java.awt.Component c : getComponents())
        {
            if(!jComponents.contains(c))
            {
                remove(c);
            }
        }
    }
    public void Update() {
        UpdateSwingComponents();

        repaint();
        Toolkit.getDefaultToolkit().sync();
        long start = System.nanoTime();
        long end = System.nanoTime();
       // System.out.println((end-start)/100000);
    }
    float r = 0;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();

           // s.setPosition(s.origin.add(Vector2.right));
        s.Scale(20);
         g.fillPolygon(s.getPolygon());
        //g.drawRect((int) Component.square.center.getX(), (int) Component.square.center.getY(),2,2);
        if(true)
        {
            for(Component compnent : ObjectHandler.getObjects())
            {
                DrawComponents(g,compnent);
            }

            DrawColliders(g);
        }
    }

    private void DrawComponents(Graphics g,Component parent) {

        GameObject ob = (GameObject) parent;
        if (ob.Display() != null) {
            g.drawImage((Image) ob.Display(), (int) ob.getSpritePosition().getX(), (int) ob.getSpritePosition().getY(), null);
        } else {
            //   g.fillPolygon(ob.getShape());
            g.fillRect((int) ob.getSpritePosition().getX(), (int) ob.getSpritePosition().getY(), (int) ob.getScale().getX(),(int) ob.getScale().getY());
        }

        for (Component cob : parent.getChildren(new Component())) {
            if(cob.getClass().equals(GameObject.class))
            {
                ob = (GameObject) cob;
                if (ob.Display() != null) {
                    g.drawImage((Image) ob.Display(), (int) ob.getSpritePosition().getX(), (int) ob.getSpritePosition().getY(), null);
                } else {
                    //   g.fillPolygon(ob.getShape());
                    g.fillRect((int) ob.getSpritePosition().getX(), (int) ob.getSpritePosition().getY(), (int) ob.getScale().getX(),(int) ob.getScale().getY());
                }
            }
        }
    }

    private void DrawColliders(Graphics g)
    {

        Iterator<Component> iterator = ObjectHandler.getObjects().iterator();

        while (iterator.hasNext()){
            GameObject ob = (GameObject) iterator.next();
            class Point{
                float x;
                float y;
            }


            for(Collider c : ob.getChildren(new SquareCollider())) {
                   g.setColor(Color.GREEN);

                if(c instanceof CircleCollider &&c.isVisible())
                {
                    g.drawOval((int) (c.getPosition().getX()-(c.getScale().getX()/2)), (int) (c.getPosition().getY()-(c.getScale().getY()/2)), (int) c.getScale().getX(), (int) c.getScale().getY());
                }
                else if(c instanceof SquareCollider &&c.isVisible())
                {
                    g.drawRect((int) (c.getPosition().getX()-(c.getScale().getX()/2)), (int) (c.getPosition().getY()-(c.getScale().getY()/2)), (int) c.getScale().getX(), (int) c.getScale().getY());
                }
                g.setColor(Color.black);
            }
        }
    }

}
