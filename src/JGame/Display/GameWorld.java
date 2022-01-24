package JGame.Display;

import JGame.Main;
import JGame.Msc.Input.Input;
import JGame.Msc.ObjectHandler;
import JGame.Msc.Vector2;
import JGame.Objects.Components.Collision.CircleCollider;
import JGame.Objects.Components.Collision.Collider;
import JGame.Objects.Components.Collision.SquareCollider;
import JGame.Objects.GameObject;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class GameWorld extends JPanel {

    public static LinkedList<GameObject> newObjects = new LinkedList<>();
    public static LinkedList<GameObject> delObjects = new LinkedList<>();

    private static ArrayList<JComponent> jComponents = new ArrayList<>();

    public GameWorld() {

        for(JComponent c : jComponents)
        {
            add(c);

        }
        setBackground(Color.GREEN);
        MouseAdapter mouseAdapter =  new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getButton());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Input.mouseButtonDown(e);
                //Input.setMousePosition(new Vector2((float) MouseInfo.getPointerInfo().getLocation().getX(), (float) MouseInfo.getPointerInfo().getLocation().getY()));
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
                System.out.println(e.getX());
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
    int x = 0;

    public static void addJComponent(JComponent jComponent)
    {
        jComponents.add(jComponent);
    }
    public static void removeJComponent(JComponent jComponent)
    {
        jComponents.remove(jComponent);

    }

    public static ArrayList<JComponent> getJComponents() {
        return jComponents;
    }

    private void UpdateObjects()
    {
        //long start = System.nanoTime();
        if(GameWorld.newObjects.size()>0) {
            for (GameObject o : GameWorld.newObjects) {
                ObjectHandler.addObject(o);
            }

        }
        if(GameWorld.delObjects.size()>0) {
            for (GameObject o : GameWorld.delObjects) {
                ObjectHandler.removeObject(o);

            }
        }

        GameWorld.newObjects.clear();
        GameWorld.delObjects.clear();
        //long end = System.nanoTime();
        //System.out.println((end-start));
        for(GameObject obj:ObjectHandler.getObjects())
        {
            obj.Update();
        }
    }
    private void checkCollisions()
    {

        for(GameObject ob1 : ObjectHandler.getObjects())
        {
            for(GameObject ob2 : ObjectHandler.getObjects())
            {
                if(ob1!=(ob2))
                {
                    for(Collider c1 : ob1.getComponents(new SquareCollider()))
                    {
                        for(Collider c2 : ob2.getComponents(new SquareCollider()))
                        {
                            c1.collided(c2);
                        }
                    }
                }
            }
        }

    }
    /**Check if a Jcomponent has been removed in the Jcomponents list and if so removes it in the panel**/
    public void UpdateSwingComponents()
    {
        for(Component c : getComponents())
        {
            if(!jComponents.contains(c))
            {
                remove(c);
            }
        }
    }
    public void Update()
    {

        UpdateObjects();
        checkCollisions();
        UpdateSwingComponents();


        repaint();
        Toolkit.getDefaultToolkit().sync();
        long start = System.nanoTime();
        long end = System.nanoTime();
       // System.out.println((end-start)/100000);
    }

    Polygon p= new Polygon(new int[]{205,125,225,260},new int[]{160,272,366,274},4);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(Main.isPlaying)
        {
            Iterator<GameObject> iterator = ObjectHandler.getObjects().iterator();

            while (iterator.hasNext()){
                GameObject ob = iterator.next();
                if(ob.Display()!=null)
                    g.drawImage((Image) ob.Display(), (int) ob.getSpritePosition().getX(), (int) ob.getSpritePosition().getY(),null);
                else
                    g.fillRect((int) ob.getSpritePosition().getX(), (int) ob.getSpritePosition().getY(), (int) ob.getScale().getX(), (int) ob.getScale().getY());

            }
            DrawColliders(g);
        }
    }
    private void DrawColliders(Graphics g)
    {

        Iterator<GameObject> iterator = ObjectHandler.getObjects().iterator();

        while (iterator.hasNext()){
            GameObject ob = iterator.next();
            class Point{
                float x;
                float y;
            }


            for(Collider c : ob.getComponents(new SquareCollider()))
            {
                /*
                Point ob11 = new Point();
                ob11.x = c.getPosition().getX()+c.getScale().devide(2).getX();
                ob11.y = c.getPosition().getY()-c.getScale().devide(2).getY();

                Point ob12 = new Point();
                ob12.x = c.getPosition().getX()-c.getScale().devide(2).getX();
                ob12.y = c.getPosition().getY()-c.getScale().devide(2).getY();

                Point ob13 = new Point();
                ob13.x = c.getPosition().getX()-c.getScale().devide(2).getX();
                //ob.getPosition().subtract((ob.getScale().devide(2))).getX();
                ob13.y = c.getPosition().getY()+c.getScale().devide(2).getY();

                Point ob14 = new Point();
                ob14.x = c.getPosition().getX()+c.getScale().devide(2).getX();
                //ob.getPosition().subtract((ob.getScale().devide(2))).getX();
                ob14.y = c.getPosition().getY()+c.getScale().devide(2).getY();
                g.drawOval((int) ob11.x, (int) ob11.y,5,5);
                g.drawOval((int) ob12.x, (int) ob12.y,5,5);
                g.drawOval((int) ob13.x, (int) ob13.y,5,5);
                g.drawOval((int) ob14.x, (int) ob14.y,5,5);
                Polygon p= new Polygon(new int[]{205,125,225,260},new int[]{160,272,366,274},4);
                p.translate(x,10);
                x++;
*/
                   g.setColor(Color.GREEN);

                if(c instanceof CircleCollider&&c.isVisible())
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
